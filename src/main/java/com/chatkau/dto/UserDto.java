package com.chatkau.dto;

import com.chatkau.entity.UserDetail;
import com.chatkau.entity.UserLogin;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private String username;
    private String password;
    private String nickname;
    private Long studentNumber;
    private Long grade;

    public UserDetail toUserDetail() {
        return UserDetail.builder()
                .nickname(nickname)
                .username(username)
                .studentNumber(studentNumber)
                .grade(grade)
                .build();
    }

    public UserLogin toUserLogin(UserDetail userDetail, String encryptedPassword) {
        return UserLogin.builder()
                .userDetail(userDetail)
                .password(encryptedPassword)
                .username(username)
                .build();
    }
}
