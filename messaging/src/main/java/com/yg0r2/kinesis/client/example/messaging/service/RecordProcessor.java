package com.yg0r2.kinesis.client.example.messaging.service;

import com.yg0r2.kinesis.client.example.messaging.domain.MessageRecord;

public interface RecordProcessor {

    void processRecord(MessageRecord messageRecord);

}
