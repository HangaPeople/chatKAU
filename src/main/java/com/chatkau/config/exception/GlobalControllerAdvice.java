package com.chatkau.config.exception;

import com.chatkau.config.BaseResponse;
import com.chatkau.config.BaseResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(BaseException.class)
    public BaseResponse<?> errorHandler(BaseException e) {
        BaseResponseStatus status = e.getStatus();
        return new BaseResponse<>(status);
    }
}
