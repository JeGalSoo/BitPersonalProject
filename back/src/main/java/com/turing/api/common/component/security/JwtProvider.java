package com.turing.api.common.component.security;

import com.turing.api.user.model.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Component
@Slf4j
public class JwtProvider {
    @Value("${jwt.iss}")
    private String issuer;

    private final SecretKey secretkey;

    public enum TokenType{REFRESH,ACCESS}

    public TokenVo makeToken(UserDto userDto){
        return TokenVo.builder()
                .email(userDto.getUsername())
                .role(userDto.getJob())
                .refreshToken(createToken(userDto,Arrays.asList(userDto.getJob()),TokenType.REFRESH))
                .accessToken(createToken(userDto, Arrays.asList(userDto.getJob()),TokenType.ACCESS))
                .build();
    }

    public JwtProvider (@Value("${jwt.secret}") String secretKey){
        this.secretkey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));

    }

    public String createToken(UserDto userDto, List<String> roles, TokenType tokenType) {
        String token = Jwts.builder()
                .issuer(issuer)
                .signWith(secretkey)
                .expiration(Date.from((tokenType.equals(TokenType.ACCESS))?Instant.now().plus(1, ChronoUnit.DAYS):Instant.now().plus(1, ChronoUnit.MINUTES)))
                .subject("james")
                .claim("username", userDto.getUsername())
                .claim("role", roles)
                .claim("id", userDto.getId())
                .compact();

        log.info("로그인성공으로 발급된 토큰 " + token);
        return token;
    }

    public String extractTokenFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        return bearerToken != null && bearerToken.startsWith("Bearer ")?bearerToken.substring(7):"undefined";
    }

    public Claims getPayload(String accessToken){
        return Jwts.parser().verifyWith(secretkey).build().parseSignedClaims(accessToken).getPayload();
    }

    public String printPayload(String accessToken){
        String[] chunks = accessToken.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));

        log.info("JWT 프로바이더 Access Token Header : "+header);
        log.info("JWT 프로바이더 Access Token Payload : "+payload);
        return payload;
    }

    public String getId(String accessToken){
        accessToken = accessToken != null && accessToken.startsWith("Bearer ")?accessToken.substring(7):"undefined";
        log.info("여기는 jwtprovicer입니다. : "+accessToken);
        String[] accessToken1 = printPayload(accessToken).split(",");
        for (int i=0; i<accessToken1.length; i++){
            log.info("반복문 : "+accessToken1[i]);
        }
        accessToken1 = accessToken1[5].split(":");
        log.info((accessToken1[1].substring(0,accessToken1[1].length()-1)));
        return (accessToken1[1].substring(0,accessToken1[1].length()-1));
    }

//    public JWTdecoder getAuthentication(String token){
//        Claims claims = (Claims) parse(token);
//        return null;
//    }

}
