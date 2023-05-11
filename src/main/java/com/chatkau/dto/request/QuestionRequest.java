package com.chatkau.dto.request;

import com.chatkau.dto.MessageDto;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
public class QuestionRequest implements Serializable {
    private List<MessageDto> messages;
}
