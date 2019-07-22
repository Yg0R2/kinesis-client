package com.yg0r2.kinesis.client.example.berps.kinesis.record.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.kinesis.producer.KinesisProducer;
import com.amazonaws.services.kinesis.producer.UserRecord;
import com.yg0r2.kinesis.client.example.berps.kinesis.record.serialization.KinesisRecordSerializer;
import com.yg0r2.kinesis.client.example.messaging.domain.MessageRecord;

public class KinesisRecordProducer extends AbstractKinesisRecordProducer<MessageRecord> {

    private static final Logger LOGGER = LoggerFactory.getLogger(KinesisRecordProducer.class);

    private final String streamName;
    private final KinesisRecordSerializer kinesisRecordSerializer;

    public KinesisRecordProducer(KinesisProducer kinesisProducer, String streamName, KinesisRecordSerializer kinesisRecordSerializer) {
        super(kinesisProducer);

        this.streamName = streamName;
        this.kinesisRecordSerializer = kinesisRecordSerializer;
    }

    @Override
    protected UserRecord createUserRecord(MessageRecord messageRecord) {
        LOGGER.info("Creating UserRecord from MessageRecord.");

        return new UserRecord(streamName, messageRecord.getBookingEmailRequest().toString(), kinesisRecordSerializer.serialize(messageRecord));
    }

}
