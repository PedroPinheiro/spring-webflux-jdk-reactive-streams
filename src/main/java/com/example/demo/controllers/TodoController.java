package com.example.demo.controllers;

import com.example.demo.domain.RetrieveTodo;
import com.example.demo.MapProcessor;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Flow.Publisher;
import java.util.function.Function;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final RetrieveTodo retrieveTodo;

    @GetMapping(value = "/todos/{id}")
    public Publisher<Output> todo(@PathVariable int id) {

        return MapProcessor
                .from(retrieveTodo.execute(id))
                .map(mapToOutput);
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Output {
        private Integer userId;
        private Integer id;
        private String title;
        private Boolean completed;
    }

    private Function<RetrieveTodo.ResponseModel, Output> mapToOutput = (responseModel) ->
            Output.builder()
                    .userId(responseModel.getUserId())
                    .id(responseModel.getId())
                    .title(responseModel.getTitle())
                    .completed(responseModel.getCompleted())
                    .build();

}
