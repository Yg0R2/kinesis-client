package com.yg0r2.kinesis.client.example.berps.kinesis.record.serialization;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yg0r2.kinesis.client.example.messaging.domain.MessageRecord;
import com.yg0r2.kinesis.client.example.messaging.serialization.MessageRecordDeserializer;

import software.amazon.kinesis.retrieval.KinesisClientRecord;

@Component
public class KinesisRecordDeserializer implements MessageRecordDeserializer<KinesisClientRecord> {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public MessageRecord deserialize(KinesisClientRecord kinesisClientRecord) {
        try {
            return objectMapper.readValue(getBytes(kinesisClientRecord), MessageRecord.class);
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
