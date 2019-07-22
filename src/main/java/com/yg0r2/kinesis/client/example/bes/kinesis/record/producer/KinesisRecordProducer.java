package com.yg0r2.kinesis.client.example.bes.kinesis.record.producer;

import com.amazonaws.services.kinesis.producer.KinesisProducer;
import com.amazonaws.services.kinesis.producer.UserRecord;
import com.yg0r2.kinesis.client.example.bes.kinesis.record.serialization.KinesisRecordSerializer;
import com.yg0r2.kinesis.client.example.messaging.domain.MessageRecord;

public class KinesisRecordProducer extends AbstractKinesisRecordProducer<MessageRecord> {

    private final String streamName;
    private final KinesisRecordSerializer kinesisRecordSerializer;

    public KinesisRecordProducer(KinesisProducer kinesisProducer, String streamName, KinesisRecordSerializer kinesisRecordSerializer) {
        super(streamName, kinesisProducer);

        this.streamName = streamName;
        this.kinesisRecordSerializer = kinesisRecordSerializer;
    }

    @Override
    protected UserRecord createUserRecord(MessageRecord messageRecord) {
        return new UserRecord(streamName, messageRecord.getBookingEmailRequest().toString(), kinesisRecordSerializer.serialize(messageRecord));
    }

}
