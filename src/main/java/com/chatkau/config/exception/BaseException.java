package com.chatkau.config.exception;

import com.chatkau.config.BaseResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {

    private BaseResponseStatus status;
}
