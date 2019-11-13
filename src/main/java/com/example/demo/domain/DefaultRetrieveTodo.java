package com.example.demo.domain;

import com.example.demo.domain.entities.Todo;
import com.example.demo.domain.gateways.FindTodoByIdGateway;
import com.example.demo.domain.gateways.MapProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Flow;
import java.util.function.Function;

@RequiredArgsConstructor
@Component
public class DefaultRetrieveTodo implements RetrieveTodo {

    private final FindTodoByIdGateway gateway;

    @Override
    public Flow.Publisher<ResponseModel> execute(final int id) {

        return MapProcessor.from(gateway.find(id))
                .map(mapToResponse);

    }

    private Function<Todo, ResponseModel> mapToResponse = (item) -> ResponseModel.builder()
            .id(item.getId())
            .userId(item.getUserId())
            .title(item.getTitle())
            .completed(item.getCompleted())
            .build();


}
