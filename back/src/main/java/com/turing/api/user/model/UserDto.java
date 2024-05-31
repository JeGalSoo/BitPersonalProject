package com.turing.api.user.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@NoArgsConstructor
@Data
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private Long addressId;
    private String job;
    private String token;

    @QueryProjection
    public UserDto(Long id, String username, String password, String name, String phone, Long addressId, String job, String token) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.addressId = addressId;
        this.job = job;
        this.token = token;
    }
}
