package com.turing.api.common.exception;

import com.turing.api.common.component.security.JwtProvider;
import com.turing.api.common.component.security.TokenVo;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.HandlerInterceptor;

@RestControllerAdvice
public class JwtExceptionHandler {
    public static JwtProvider jwt;

    @GetMapping("/exception")
    @ExceptionHandler(ExpiredJwtException.class)
    public String ReAccessToken (){
        return "access token expired";
    }
}
