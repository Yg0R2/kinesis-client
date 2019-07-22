package com.yg0r2.kinesis.client.example.bes.kinesis.record.serialization;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yg0r2.kinesis.client.example.bes.domain.BookingEmailRequest;
import com.yg0r2.kinesis.client.example.messaging.domain.MessageRecord;
import com.yg0r2.kinesis.client.example.messaging.record.serialization.MessageRecordDeserializer;

import software.amazon.kinesis.retrieval.KinesisClientRecord;

@Component
public class BookingEmailRequestDeserializer implements MessageRecordDeserializer<KinesisClientRecord> {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public MessageRecord deserialize(KinesisClientRecord kinesisClientRecord) {
        return MessageRecord.builder()
            .withBookingEmailRequest(getBookingEmailRequest(kinesisClientRecord))
            .build();
    }

    private BookingEmailRequest getBookingEmailRequest(KinesisClientRecord kinesisClientRecord) {
        try {
            return objectMapper.readValue(getBytes(kinesisClientRecord), BookingEmailRequest.class);
        }
        catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private byte[] getBytes(KinesisClientRecord record) {
        byte[] recordDataBuffer = new byte[record.data().remaining()];

        record.data().get(recordDataBuffer);

        return recordDataBuffer;
    }

}
