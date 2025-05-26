package com.ecommerce.user.service;

import com.ecommerce.user.dto.ClientDto;

import java.util.List;

public interface UserService {
    ClientDto createUser(ClientDto user);
    List<ClientDto> getAllUser();
    ClientDto updateUser(ClientDto user);
}
