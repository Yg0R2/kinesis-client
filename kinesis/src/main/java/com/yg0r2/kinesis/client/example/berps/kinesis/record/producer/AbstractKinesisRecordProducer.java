package com.yg0r2.kinesis.client.example.berps.kinesis.record.producer;

import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.kinesis.producer.Attempt;
import com.amazonaws.services.kinesis.producer.KinesisProducer;
import com.amazonaws.services.kinesis.producer.UserRecord;
import com.amazonaws.services.kinesis.producer.UserRecordFailedException;
import com.amazonaws.services.kinesis.producer.UserRecordResult;
import com.yg0r2.kinesis.client.example.messaging.service.RecordProducer;

public abstract class AbstractKinesisRecordProducer<T> implements RecordProducer<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractKinesisRecordProducer.class);

    private final KinesisProducer kinesisProducer;

    public AbstractKinesisRecordProducer(KinesisProducer kinesisProducer) {
        this.kinesisProducer = kinesisProducer;
    }

    @Override
    public void produce(T record) {
        LOGGER.info("Producing record: {}", record);

        Stream.of(record)
            .map(this::createUserRecord)
            .map(this::getUserRecordResult)
            .forEach(this::handleResult);
    }

    protected abstract UserRecord createUserRecord(T record);

    private UserRecordResult getUserRecordResult(UserRecord userRecord) {
        UserRecordResult userRecordResult;

        try {
            Future<UserRecordResult> userRecordResultFuture = kinesisProducer.addUserRecord(userRecord);

            userRecordResult = userRecordResultFuture.get();
        }
        catch (InterruptedException | ExecutionException exception) {
            if (exception.getCause() instanceof UserRecordFailedException) {
                userRecordResult = ((UserRecordFailedException) exception.getCause()).getResult();
            }
            else {
                throw new RuntimeException(exception);
            }
        }

        return userRecordResult;
    }

    private void handleResult(UserRecordResult userRecordResult) {
        if (userRecordResult.isSuccessful()) {
            LOGGER.info("Record successfully put to shard: {}", userRecordResult.getShardId());
        }
        else {
            String errorMessages = userRecordResult.getAttempts().stream()
                .filter(Objects::nonNull)
                .map(Attempt::getErrorMessage)
                .collect(Collectors.joining(", "));

            LOGGER.error("Failed to put record to shard: {}, errors: {}", userRecordResult.getShardId(), errorMessages);
        }
    }

}
