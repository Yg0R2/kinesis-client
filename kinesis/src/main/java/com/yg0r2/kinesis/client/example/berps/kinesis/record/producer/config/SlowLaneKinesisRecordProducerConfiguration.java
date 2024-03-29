package com.yg0r2.kinesis.client.example.berps.kinesis.record.producer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.kinesis.producer.KinesisProducer;
import com.yg0r2.kinesis.client.example.berps.kinesis.record.producer.KinesisRecordProducer;
import com.yg0r2.kinesis.client.example.berps.kinesis.record.serialization.KinesisRecordSerializer;
import com.yg0r2.kinesis.client.example.messaging.domain.MessageRecord;
import com.yg0r2.kinesis.client.example.messaging.service.RecordProducer;

@Configuration
public class SlowLaneKinesisRecordProducerConfiguration {

    @Value("${aws.kinesis.stream.slowLane.name}")
    private String streamName;

    @Autowired
    private KinesisProducer slowLaneKinesisProducer;
    @Autowired
    private KinesisRecordSerializer kinesisRecordSerializer;

    @Bean
    public RecordProducer<MessageRecord> slowLaneRecordProducer() {
        return new KinesisRecordProducer(slowLaneKinesisProducer, streamName, kinesisRecordSerializer);
    }

}
