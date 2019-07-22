package com.yg0r2.kinesis.client.example.berps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yg0r2.kinesis.client.example.messaging.domain.MessageRecord;
import com.yg0r2.kinesis.client.example.messaging.service.RecordProcessor;

@Component
public class DefaultRecordProcessor implements RecordProcessor {

    @Autowired
    private BookingEmailRequestProcessorService bookingEmailRequestProcessorService;

    public void processRecord(MessageRecord messageRecord) {
        bookingEmailRequestProcessorService.processRequest(messageRecord.getBookingEmailRequest());
    }

}
