package com.chatkau.service;

import com.chatkau.config.BaseResponseStatus;
import com.chatkau.config.exception.BaseException;
import com.chatkau.dto.UserDto;
import com.chatkau.entity.User;
import com.chatkau.repository.UserRepository;
import com.chatkau.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder encoder;

    public UserDto join(UserDto userDto) {
        userRepository.findByUserId(userDto.getUserId()).ifPresent(it -> {
            throw new BaseException(BaseResponseStatus.DUPLICATED_USERID);
        });

        userDto.setPassword(encoder.encode(userDto.getPassword()));
        userRepository.save(User.of(userDto));
        return userDto;
    }

    public String login(String userId, String password) {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> {
            throw new BaseException(BaseResponseStatus.USERID_ERROR);
        });

        if (!encoder.matches(password, user.getPassword())) {
            throw new BaseException(BaseResponseStatus.PASSWORD_ERROR);
        }

        return jwtTokenProvider.createToken(userId, user.getRoles());
    }
}
