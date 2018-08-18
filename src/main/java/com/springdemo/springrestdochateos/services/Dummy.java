package com.springdemo.springrestdochateos.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class Dummy {

    @Async
    public void longAsyncOperation() {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("long asyn operation");
    }

}
