package com.ecommerce.user.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity {

    private LocalDateTime createAt;

    public BaseEntity() {
        this.createAt = LocalDateTime.now();
    }
}

