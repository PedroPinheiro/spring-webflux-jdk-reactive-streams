package com.example.demo.domain.gateways;

import com.example.demo.domain.entities.Todo;

import java.util.concurrent.Flow;

public interface FindTodoByIdGateway {

    Flow.Publisher<Todo> find(final int id);
}
