package com.yg0r2.kinesis.client.example.messaging.service;

public interface RecordProducer<T> {

    void produce(T record);

}
