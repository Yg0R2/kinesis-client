package com.yg0r2.kinesis.client.example.messaging.retry;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.yg0r2.kinesis.client.example.messaging.domain.RetryContext;

@Component
public class DummyRetryPolicy implements RetryPolicy {

    private static final Random RND = new Random();

    @Value("${messaging.minimumIterations}")
    private int minimumIterations;

    @Override
    public boolean canExecuteRetry(RetryContext retryContext) {
        return true;
    }

    @Override
    public boolean canRescheduleFailedRetry(RetryContext retryContext) {
        return (retryContext.getRetryCount() < minimumIterations) ? true : RND.nextBoolean();
    }

    @Override
    public LocalDateTime getNextRetryDateTime(RetryContext retryContext) {
        return null;
    }

}
