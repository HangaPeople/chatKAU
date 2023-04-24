package com.chatkau.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "항공대 챗봇",
                description = "항공대 관련정보를 학습시킨 챗봇을 만들자",
                version = "v1")
)
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi user() {
        String[] paths = {"/user/**"};

        return GroupedOpenApi.builder()
                .group("유저 서비스")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi course() {
        String[] paths = {"/course/**"};

        return GroupedOpenApi.builder()
                .group("과목 서비스")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi curr() {
        String[] paths = {"/curr/**"};

        return GroupedOpenApi.builder()
                .group("커리큘럼 서비스")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi currCourse() {
        String[] paths = {"/curr-course/**"};

        return GroupedOpenApi.builder()
                .group("커리큘럼 내 과목 서비스")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi chatGPT() {
        String[] paths = {"/chat-gpt/**"};

        return GroupedOpenApi.builder()
                .group("chatGPT 서비스")
                .pathsToMatch(paths)
                .build();
    }
}