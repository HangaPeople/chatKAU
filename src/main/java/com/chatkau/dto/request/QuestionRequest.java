package com.chatkau.dto.request;

import com.chatkau.entity.Message;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
public class QuestionRequest implements Serializable {
    private List<Message> messages;
}
