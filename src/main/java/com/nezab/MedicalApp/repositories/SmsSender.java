package com.nezab.MedicalApp.repositories;

import com.nezab.MedicalApp.utils.SmsRequest;

public interface SmsSender {
    void sendSms(SmsRequest smsRequest);
}
