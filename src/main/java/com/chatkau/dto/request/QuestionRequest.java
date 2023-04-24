package com.chatkau.dto.request;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class QuestionRequest implements Serializable {
    private String question;
}
