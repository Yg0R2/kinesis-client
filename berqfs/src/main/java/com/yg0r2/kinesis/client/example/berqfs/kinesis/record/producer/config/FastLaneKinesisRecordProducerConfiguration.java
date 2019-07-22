package com.yg0r2.kinesis.client.example.berqfs.kinesis.record.producer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.kinesis.producer.KinesisProducer;
import com.yg0r2.kinesis.client.example.berps.kinesis.record.serialization.KinesisRecordSerializer;
import com.yg0r2.kinesis.client.example.berqfs.kinesis.record.producer.KinesisBookingEmailRequestProducer;
import com.yg0r2.kinesis.client.example.bes.domain.BookingEmailRequest;
import com.yg0r2.kinesis.client.example.messaging.service.RecordProducer;

@Configuration
public class FastLaneKinesisRecordProducerConfiguration {

    @Value("${aws.kinesis.stream.fastLane.name}")
    private String streamName;

    @Autowired
    private KinesisProducer fastLaneKinesisProducer;
    @Autowired
    private KinesisRecordSerializer kinesisRecordSerializer;

    @Bean
    public RecordProducer<BookingEmailRequest> fastLaneRecordProducer() {
        return new KinesisBookingEmailRequestProducer(fastLaneKinesisProducer, streamName, kinesisRecordSerializer);
    }

}
