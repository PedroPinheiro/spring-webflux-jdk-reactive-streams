package com.example.demo.domain;

import lombok.Builder;
import lombok.Data;

import java.util.concurrent.Flow;

public interface RetrieveTodo {

    Flow.Publisher<ResponseModel> execute(final int id);

    @Data
    @Builder
    class ResponseModel {
        private Integer userId;
        private Integer id;
        private String title;
        private Boolean completed;
    }

}
