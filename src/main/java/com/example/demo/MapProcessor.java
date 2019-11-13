package com.example.demo;


import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;

public class MapProcessor<T> {

    private Flow.Publisher<T> publisher;

    private MapProcessor(Flow.Publisher<T> publisher) {
        this.publisher = publisher;
    }

    public static <T> MapProcessor<T> from(Flow.Publisher<T> publisher) {
        return new MapProcessor<>(publisher);
    }

    public <R> Flow.Publisher<R> map(Function<T, R> function) {

        Transform transformPublisher = new Transform(function);
        publisher.subscribe(transformPublisher);
        return transformPublisher;
    }


    public class Transform<T, R>
            extends SubmissionPublisher<R>
            implements Flow.Processor<T, R> {

        private Function<T, R> function;
        private Flow.Subscription subscription;

        private Transform(Function<T, R> function) {
            super();
            this.function = function;
        }

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            subscription.request(1);
        }

        @Override
        public void onNext(T item) {
            submit(function.apply(item));
            subscription.request(1);
        }

        @Override
        public void onError(Throwable t) {
            t.printStackTrace();
        }

        @Override
        public void onComplete() {
            close();
        }

    }


}
