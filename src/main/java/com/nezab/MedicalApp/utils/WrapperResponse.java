package com.nezab.MedicalApp.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class WrapperResponse<T> {

    private boolean ok;
    private String message;
    private T body;

    public WrapperResponse() {

    }

    public WrapperResponse(String message, T body, boolean ok) {
        setMessage(message);
        setBody(body);
        setOk(ok);
    }

    public ResponseEntity<WrapperResponse<T>> createResponse() {
        return new ResponseEntity<>(this, HttpStatus.OK);
    }

    public ResponseEntity<WrapperResponse<T>> createResponse(HttpStatus status) {
        return new ResponseEntity<>(this, status);
    }


    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
