package com.ecommerce.user.mapper;

import com.ecommerce.user.dto.ClientCreateDto;
import com.ecommerce.user.dto.ClientResponseDto;
import com.ecommerce.user.entity.User;

import java.lang.reflect.Field;

public class UserMapper {

    public static ClientResponseDto mapToClientDto(User user){
         return UserMapper.map(user, ClientResponseDto.class);
    }


    public static User mapTOUserDto(ClientResponseDto client){
        return UserMapper.map(client, User.class);
    }

    public static User mapClientCreateToUserDto(ClientCreateDto client){
        return UserMapper.map(client, User.class);

    }

    private static <T, U> U map(T source, Class<U> targetClass) {
        try {
            U target = targetClass.getDeclaredConstructor().newInstance();

            Field[] sourceFields = source.getClass().getDeclaredFields();
            Field[] targetFields = targetClass.getDeclaredFields();

            for (Field sourceField : sourceFields) {
                sourceField.setAccessible(true);
                String fieldName = sourceField.getName();
                Object value = sourceField.get(source);

                for (Field targetField : targetFields) {
                    if (targetField.getName().equals(fieldName) &&
                            targetField.getType().isAssignableFrom(sourceField.getType())) {
                        targetField.setAccessible(true);
                        targetField.set(target, value);
                        break;
                    }
                }
            }

            return target;
        } catch (Exception e) {
            throw new RuntimeException("DTO mapping failed", e);
        }
    }

}
