package com.yg0r2.kinesis.client.example.bes.kinesis.record.producer;

import java.nio.ByteBuffer;

import com.amazonaws.services.kinesis.producer.KinesisProducer;
import com.amazonaws.services.kinesis.producer.UserRecord;
import com.yg0r2.kinesis.client.example.bes.domain.BookingEmailRequest;
import com.yg0r2.kinesis.client.example.bes.kinesis.record.serialization.KinesisRecordSerializer;
import com.yg0r2.kinesis.client.example.messaging.domain.MessageRecord;
import com.yg0r2.kinesis.client.example.messaging.service.RecordProducer;

public class BookingEmailRequestProducer extends AbstractKinesisRecordProducer<BookingEmailRequest> implements RecordProducer<BookingEmailRequest> {

    private final String streamName;
    private final KinesisRecordSerializer kinesisRecordSerializer;

    public BookingEmailRequestProducer(KinesisProducer kinesisProducer, String streamName, KinesisRecordSerializer kinesisRecordSerializer) {
        super(streamName, kinesisProducer);

        this.streamName = streamName;
        this.kinesisRecordSerializer = kinesisRecordSerializer;
    }

    @Override
    protected UserRecord createUserRecord(BookingEmailRequest bookingEmailRequest) {
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
