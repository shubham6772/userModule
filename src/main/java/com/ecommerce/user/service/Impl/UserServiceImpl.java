package com.ecommerce.user.service.Impl;

import com.ecommerce.user.dto.ClientCreateDto;
import com.ecommerce.user.dto.ClientResponseDto;
import com.ecommerce.user.entity.User;
import com.ecommerce.user.exceptions.EmailAlreadyExistException;
import com.ecommerce.user.exceptions.UserNotFoundException;
import com.ecommerce.user.mapper.UserMapper;
import com.ecommerce.user.repository.UserRepository;
import com.ecommerce.user.service.RedisService;
import com.ecommerce.user.service.UserService;
import com.ecommerce.user.util.UniqueIDGenerator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RedisService redisService;

    @Override
    public ClientResponseDto createUser(ClientCreateDto user) {

            Optional<User> existingUser = userRepository.findByEmailIgnoreCase(user.getEmail().trim().toLowerCase());
            String generatedId = UniqueIDGenerator.generateUniqueId();
            System.out.println("ExistingUser" + existingUser.isPresent());
            if(existingUser.isPresent()){
                throw new EmailAlreadyExistException("user already exist with this email");
            }

            User mappedUser = UserMapper.mapClientCreateToUserDto(user);
            mappedUser.setId(generatedId);
            redisService.saveUser(generatedId, mappedUser);
            mappedUser.setSessionData(mappedUser.toString());
            User savedUser = userRepository.save(mappedUser);
            return UserMapper.mapToClientDto(savedUser);
    }

    @Override
    public List<ClientResponseDto> getAllUser() {
        List<User> savedUsers =  userRepository.findAll();
        return savedUsers.stream().map(UserMapper::mapToClientDto).collect(Collectors.toList());
    }

    @Override
    public ClientResponseDto updateUser(ClientResponseDto user) {
        Optional<User> existingUser = userRepository.findById(user.getId());
        if(existingUser.isEmpty()){
            throw new UserNotFoundException("user not exist with this id");
        }

        User savedUser = existingUser.get();

        savedUser.setEmail(user.getEmail());
        savedUser.setName(user.getName());

        User updatedUser = userRepository.save(savedUser);

        return UserMapper.mapToClientDto(updatedUser);
    }

    @Override
    public ClientResponseDto getUser(ClientCreateDto user) {
        Optional<User> existingUser = userRepository.findByEmailIgnoreCase(user.getEmail().trim().toLowerCase());

        if(existingUser.isEmpty()){
            throw new UserNotFoundException("user not exist with this email");
        }

        User savedUser = existingUser.get();
        return UserMapper.mapToClientDto(savedUser);
    }
}
