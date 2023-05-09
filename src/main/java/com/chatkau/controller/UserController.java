package com.chatkau.controller;

import com.chatkau.config.BaseResponse;
import com.chatkau.dto.UserDto;
import com.chatkau.dto.request.UserLoginRequest;
import com.chatkau.dto.response.UserLoginResponse;
import com.chatkau.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
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

    @Operation(summary = "회원가입", description = "userDto를 받아 회원가입을 한다.")
    @PostMapping("/signup")
    public BaseResponse<UserDto> signup(@RequestBody UserDto userDto) {
        UserDto user = userService.join(userDto);

        return new BaseResponse<>(user);
    }

    @Operation(summary = "로그인", description = "userLoginRequest를 받아 로그인을 한다.")
    @PostMapping("/signin")
    public BaseResponse<UserLoginResponse> login(@RequestBody UserLoginRequest request) {
        String token = userService.login(request.getUserId(), request.getPassword());

        return new BaseResponse<>(new UserLoginResponse(request.getUserId(), request.getPassword(), token));
    }
}
