package com.yg0r2.kinesis.client.example.berps.kinesis.record.consumer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.yg0r2.kinesis.client.example.berps.kinesis.record.consumer.KinesisShardRecordProcessor;
import com.yg0r2.kinesis.client.example.berps.kinesis.record.serialization.BookingEmailRequestDeserializer;
import com.yg0r2.kinesis.client.example.messaging.service.RecordProcessor;

@Configuration
public class FastLaneKinesisShardRecordProcessorConfiguration {

    @Autowired
    private BookingEmailRequestDeserializer bookingEmailRequestDeserializer;
    @Autowired
    private RecordProcessor recordProcessor;
    @Autowired
    private ThreadPoolTaskExecutor fastLaneThreadPoolTaskExecutor;

    @Bean
    public KinesisShardRecordProcessor fastLaneKinesisShardRecordProcessor() {
        return new KinesisShardRecordProcessor(bookingEmailRequestDeserializer, recordProcessor, fastLaneThreadPoolTaskExecutor);
    }

}
