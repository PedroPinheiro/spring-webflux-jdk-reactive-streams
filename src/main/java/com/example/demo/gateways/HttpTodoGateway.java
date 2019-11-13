package com.example.demo.gateways;

import com.example.demo.domain.entities.Todo;
import com.example.demo.domain.gateways.FindTodoByIdGateway;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.adapter.JdkFlowAdapter;
import reactor.core.publisher.Mono;

import java.util.concurrent.Flow;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class HttpTodoGateway implements FindTodoByIdGateway {

    private final WebClient client;

    public HttpTodoGateway() {
        client = WebClient.create("https://jsonplaceholder.typicode.com/");
    }

    @Override
    public Flow.Publisher<Todo> find(int id) {

        Mono<Todo> todo = client.get()
                .uri("/todos/{id}", id)
                .accept(APPLICATION_JSON)
                .exchange()
                .flatMap(response -> response.bodyToMono(Todo.class));


        return JdkFlowAdapter.publisherToFlowPublisher(todo);
    }
}
