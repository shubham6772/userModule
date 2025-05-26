package com.ecommerce.user.mapper;

import com.ecommerce.user.dto.ClientDto;
import com.ecommerce.user.entity.User;

public class UserMapper {

    public static ClientDto mapToClientDto(User user){
          return new ClientDto(
                  user.getId(),
                  user.getName(),
                  user.getEmail()
          );
    }

    public static User mapTOUserDto(ClientDto client){
        return new User(
                client.getId(),
                client.getName(),
                client.getEmail().trim().toLowerCase()
        );
    }

}
