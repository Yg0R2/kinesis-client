package com.yg0r2.kinesis.client.example.messaging.record.serialization;

import com.yg0r2.kinesis.client.example.messaging.domain.MessageRecord;

public interface MessageRecordSerializer<T> {

    T serialize(MessageRecord messageRecord);

}
