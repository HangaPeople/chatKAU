package com.chatkau.dto;

import com.chatkau.entity.Message;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class ChoiceDto implements Serializable {

    private Integer index;
    private Message message;
    @JsonProperty("finish_reason")
    private String finishReason;

    @Builder
    public ChoiceDto(Integer index, Message message, String finishReason) {
        this.message = message;
        this.index = index;
        this.finishReason = finishReason;
    }
}
