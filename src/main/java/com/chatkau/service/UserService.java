package com.chatkau.service;

import com.chatkau.config.BaseResponseStatus;
import com.chatkau.config.exception.BaseException;
import com.chatkau.dto.UserDto;
import com.chatkau.entity.UserDetail;
import com.chatkau.entity.UserLogin;
import com.chatkau.repository.UserDetailRepository;
import com.chatkau.repository.UserLoginRepository;
import com.chatkau.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDetailRepository userDetailRepository;
    private final UserLoginRepository userLoginRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public UserDto join(UserDto userDto) {
        if(userDetailRepository.findByUsername(userDto.getUsername()).isPresent())
            throw new BaseException(BaseResponseStatus.DUPLICATED_USERID);

        UserDetail userDetail = userDetailRepository.save(userDto.toUserDetail());

        String encryptedPassword = encoder.encode(userDto.getPassword());

        userLoginRepository.save(userDto.toUserLogin(userDetail, encryptedPassword));

        return userDto;
    }
}
