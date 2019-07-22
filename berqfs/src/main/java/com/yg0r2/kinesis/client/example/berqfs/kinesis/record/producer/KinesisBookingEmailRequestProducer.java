package com.yg0r2.kinesis.client.example.berqfs.kinesis.record.producer;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.kinesis.producer.KinesisProducer;
import com.amazonaws.services.kinesis.producer.UserRecord;
import com.yg0r2.kinesis.client.example.berps.kinesis.record.producer.AbstractKinesisRecordProducer;
import com.yg0r2.kinesis.client.example.berps.kinesis.record.serialization.KinesisRecordSerializer;
import com.yg0r2.kinesis.client.example.bes.domain.BookingEmailRequest;
import com.yg0r2.kinesis.client.example.messaging.domain.MessageRecord;
import com.yg0r2.kinesis.client.example.messaging.service.RecordProducer;

public class KinesisBookingEmailRequestProducer extends AbstractKinesisRecordProducer<BookingEmailRequest> implements RecordProducer<BookingEmailRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(KinesisBookingEmailRequestProducer.class);

    private final String streamName;
    private final KinesisRecordSerializer kinesisRecordSerializer;

    public KinesisBookingEmailRequestProducer(KinesisProducer kinesisProducer, String streamName, KinesisRecordSerializer kinesisRecordSerializer) {
        super(kinesisProducer);

        this.streamName = streamName;
        this.kinesisRecordSerializer = kinesisRecordSerializer;
    }

    @Override
    protected UserRecord createUserRecord(BookingEmailRequest bookingEmailRequest) {
        LOGGER.info("Creating UserRecord from BookingEmailRequest.");

        return new UserRecord(streamName, bookingEmailRequest.toString(), createByteBuffer(bookingEmailRequest));
    }

    private ByteBuffer createByteBuffer(BookingEmailRequest bookingEmailRequest) {
        return kinesisRecordSerializer.serialize(createMessageRecord(bookingEmailRequest));
    }

    private MessageRecord createMessageRecord(BookingEmailRequest bookingEmailRequest) {
        return MessageRecord.builder()
            .withBookingEmailRequest(bookingEmailRequest)
            .build();
    }

}
