package com.ecommerce.user.service.Impl;

import com.ecommerce.user.dto.ClientDto;
import com.ecommerce.user.entity.User;
import com.ecommerce.user.exceptions.EmailAlreadyExistException;
import com.ecommerce.user.exceptions.UserNotFoundException;
import com.ecommerce.user.mapper.UserMapper;
import com.ecommerce.user.repository.UserRepository;
import com.ecommerce.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public ClientDto createUser(ClientDto user) {

        Optional<User> existingUser = userRepository.findByEmailIgnoreCase(user.getEmail().trim().toLowerCase());

        System.out.println("ExistingUser" + existingUser.isPresent());
        if(existingUser.isPresent()){
            throw new EmailAlreadyExistException("user already exist with this email", "USER_ALREADY_EXIST");
        }

        User mappedUser = UserMapper.mapTOUserDto(user);
        mappedUser.setId(UUID.randomUUID().toString());

        User savedUser = userRepository.save(mappedUser);
        return UserMapper.mapToClientDto(savedUser);
    }

    @Override
    public List<ClientDto> getAllUser() {
        List<User> savedUsers =  userRepository.findAll();
        return savedUsers.stream().map(UserMapper::mapToClientDto).collect(Collectors.toList());
    }

    @Override
    public ClientDto updateUser(ClientDto user) {
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
}
