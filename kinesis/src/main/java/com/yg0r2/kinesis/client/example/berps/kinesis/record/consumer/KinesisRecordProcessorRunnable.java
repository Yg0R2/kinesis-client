package com.yg0r2.kinesis.client.example.berps.kinesis.record.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yg0r2.kinesis.client.example.messaging.domain.MessageRecord;
import com.yg0r2.kinesis.client.example.messaging.service.RecordProcessor;

public class KinesisRecordProcessorRunnable implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(KinesisRecordProcessorRunnable.class);

    private final MessageRecord messageRecord;
    private final RecordProcessor recordProcessor;

    public KinesisRecordProcessorRunnable(MessageRecord messageRecord, RecordProcessor recordProcessor) {
        this.messageRecord = messageRecord;
        this.recordProcessor = recordProcessor;
    }

    @Override
    public void run() {
        LOGGER.info("Consuming record: {}", messageRecord);

        recordProcessor.processRecord(messageRecord);

        LOGGER.info("Consumed record: {}", messageRecord);
    }

}
