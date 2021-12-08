package com.nezab.MedicalApp.services;

import com.nezab.MedicalApp.repositories.SmsSender;
import com.nezab.MedicalApp.utils.SmsRequest;
import com.nezab.MedicalApp.utils.TwilioConfiguration;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("twilio")
public class TwilioSmsSender implements SmsSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSender.class);

    private final TwilioConfiguration twilioConfiguration;

    @Autowired
    public TwilioSmsSender(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    @Override
    public void sendSms(SmsRequest smsRequest) {
        if (isPhoneNumberValid(smsRequest.getPhoneNumber())) {
            PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
            String message = smsRequest.getMessage();
            MessageCreator messageCreator = Message.creator(
                    to,
                    from,
                    message
            );
            messageCreator.create();
            LOGGER.info("Send sms {}" + smsRequest);
        } else {
            throw new IllegalArgumentException("Phone number invalid");
        }

    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        return true;
    }
}
