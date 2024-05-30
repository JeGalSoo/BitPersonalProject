package com.turing.api.user.service;

import com.turing.api.common.component.Messenger;
import com.turing.api.common.component.security.JwtProvider;
import com.turing.api.user.model.User;
import com.turing.api.user.model.UserDto;
import com.turing.api.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;


@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final JwtProvider jwtProvider;
    private final UserRepository repository;


    @Override
    public List<UserDto> findAll() {
        return repository.findAll().stream().map(i->entityToDto(i)).toList();
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        var dto = Optional.ofNullable(entityToDto(Objects.requireNonNull(repository.findById(id).orElse(null))));
        return dto;
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Messenger save(UserDto userDto) {
        log.info(String.valueOf(userDto));
        User user = dtoToEntity(userDto);
        boolean flag = repository.existsByUsername(user.getUsername());
        if (!flag) repository.save(user);
        return Messenger.builder()
                .message((flag)?"이미 있는 아이디 입니다.":"저장 성공")
                .build();
    }

    @Override
    public Messenger deleteById(Long id) {
         if(repository.existsById(id)){
             repository.deleteById(id);
            return Messenger.builder()
                    .message("SUCCESS")
                    .build();
        }else {
             return Messenger.builder()
                     .message("FAIL")
                     .build();
         }
    }
    @Transactional
    @Override
    public Messenger modify(UserDto userDto) {
        User user = repository.findById(userDto.getId()).get();
        if (userDto.getUsername() != null && !userDto.getUsername().equals("")) {
            user.setUsername(userDto.getUsername());
        }
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setAddressId(userDto.getAddressId());
        user.setPhone(userDto.getPhone());
        repository.save(user);

        return repository.findById(userDto.getId()).get().equals(user) ?
                Messenger.builder().message("SUCCESS").build() :
                Messenger.builder().message("FAILURE").build();
    }

    @Override
    public List<?> findByName(String name) {
        var dto = new ArrayList<>();
//        dto.add(re.findByUsername(name));
        return dto;
    }

    @Override
    public List<?> findByJob(String job) {
        var dto = new ArrayList<>();
        dto.add(repository.findUsersByJob(job));
        return dto;
    }

    @Transactional
    @Override
    public Messenger login(UserDto userDto) {
        var user = repository.findByUsername(userDto.getUsername());
        var token = jwtProvider.makeToken(entityToDto(user));
        var flag = user.getPassword().equals(userDto.getPassword());
        repository.modifyTokenById(token.getRefreshToken(),user.getId());
        jwtProvider.printPayload(token.getRefreshToken());
        jwtProvider.printPayload(token.getAccessToken());
        return Messenger.builder()
                .message(flag? "SUCCESS":"FAIL")
                .accessToken(flag?token.getAccessToken():"None")
                .refreshToken(flag?token.getRefreshToken():"None")
                .build();
    }



    @Override
    public Messenger findByUsername(String username) {
        return null;
    }

    @Override
    public Messenger existsByUsername(String username) {
        log.info(username);
        return Messenger.builder()
                .message(repository.existsByUsername(username)?"true":"false")
                .build();
    }

    @Transactional
    @Override
    public Boolean logout(String accessToken) {
        log.info(accessToken);
        String data = jwtProvider.getId(accessToken);
        System.out.println(data);
        Long id = Long.valueOf(data);
        String deletedToken = null;
        repository.modifyTokenById(deletedToken,id);
        return findById(id).get().getToken() == null;
    }
}