package com.springdemo.springrestdochateos.repositories;

import com.google.common.util.concurrent.RateLimiter;

import java.time.ZonedDateTime;
import java.util.stream.IntStream;

public class GuavaTest {
    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(100);

        // when
        long startTime = ZonedDateTime.now().getSecond();
        IntStream.range(0, 1000).forEach(i -> {
            rateLimiter.acquire();
            System.out.println("Do Something "+i);
        });
        long elapsedTimeSeconds = ZonedDateTime.now().getSecond() - startTime;
        System.out.println(elapsedTimeSeconds);
    }
}
