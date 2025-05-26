package com.ecommerce.user.exceptions;



import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistException extends RuntimeException{
    private String message;
    public EmailAlreadyExistException(String message){
        super(message);
    }

}
