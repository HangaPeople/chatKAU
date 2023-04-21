package com.chatkau.dto.request;

import lombok.Data;

@Data
public class UserJoinRequest {
    private String userName;
    private String userId;
    private String password;
}
