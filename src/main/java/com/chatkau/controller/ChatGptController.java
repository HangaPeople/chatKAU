package com.chatkau.controller;

import com.chatkau.dto.request.QuestionRequest;
import com.chatkau.dto.response.ChatGptResponse;
import com.chatkau.service.ChatGptService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat-gpt")
public class ChatGptController {

    private final ChatGptService chatGptService;

    @Operation(summary = "chat-gpt에게 질문하기", description = "QuestionRequest를 받아 chat-gpt에게 질문을 한다.")
    @PostMapping("/question")
    public ChatGptResponse sendQuestion(@RequestBody QuestionRequest request) {
        return chatGptService.askQuestion(request);
    }
}
