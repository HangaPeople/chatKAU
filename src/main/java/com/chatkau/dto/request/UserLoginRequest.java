package com.chatkau.dto.request;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String userId;
    private String password;
}
