package com.chatkau.controller;

import com.chatkau.config.BaseResponse;
import com.chatkau.dto.UserDto;
import com.chatkau.dto.request.UserLoginRequest;
import com.chatkau.dto.response.UserLoginResponse;
import com.chatkau.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String hello() {
        return "<h1> 안녕~ <h1>";
    }

    @GetMapping("/admin")
    public String admin() {
        return "<h1> 이건 관리자만 볼 수 있는거 <h1>";
    }

    @GetMapping("/user")
    public String user() {
        return "<h1> 이건 누구나 볼 수 있는거 <h1>";
    }

    @PostMapping("auth/signup")
    public ResponseEntity<?> signup(@RequestBody UserDto userDto) {
        UserDto user = userService.join(userDto);

        return ResponseEntity.ok(userDto);
    }
}
