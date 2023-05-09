package com.chatkau.service;

import com.chatkau.config.BaseResponseStatus;
import com.chatkau.config.exception.BaseException;
import com.chatkau.dto.user.UserDto;
import com.chatkau.entity.UserDetail;
import com.chatkau.repository.UserDetailRepository;
import com.chatkau.repository.UserLoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDetailRepository userDetailRepository;
    private final UserLoginRepository userLoginRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public ResponseEntity<?> join(UserDto userDto) {
        if(userDetailRepository.findByUsername(userDto.getUsername()).isPresent())
            throw new BaseException(BaseResponseStatus.DUPLICATED_USERID);

        UserDetail userDetail = userDetailRepository.save(userDto.toUserDetail());

        String encryptedPassword = encoder.encode(userDto.getPassword());

        userLoginRepository.save(userDto.toUserLogin(userDetail, encryptedPassword));

        return ResponseEntity.ok("SUCCESS");
    }
}
