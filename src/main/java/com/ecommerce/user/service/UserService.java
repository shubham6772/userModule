package com.ecommerce.user.service;

import com.ecommerce.user.dto.ClientCreateDto;
import com.ecommerce.user.dto.ClientResponseDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface UserService {
    ClientResponseDto createUser(ClientCreateDto user);
    List<ClientResponseDto> getAllUser();
    ClientResponseDto updateUser(ClientResponseDto user);
    ClientResponseDto getUser(ClientCreateDto user);
}
