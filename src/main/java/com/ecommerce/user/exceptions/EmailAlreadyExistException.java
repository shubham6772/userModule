package com.ecommerce.user.exceptions;



import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistException extends RuntimeException{
    private String message;
    @Getter
    private String code;
    public EmailAlreadyExistException(String message, String code){
        super(message);
        this.code = code;
    }

}
