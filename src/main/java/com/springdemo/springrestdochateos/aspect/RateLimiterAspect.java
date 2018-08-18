package com.springdemo.springrestdochateos.aspect;

import com.google.common.util.concurrent.RateLimiter;
import com.springdemo.springrestdochateos.annotation.RateLimit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class RateLimiterAspect {

    private final Map<String, RateLimiter> limiters = new ConcurrentHashMap<>();

    @Before("@annotation(limit)")
    public void rateLimit(JoinPoint jp, RateLimit limit){
        System.out.println("limit.value()>>>>>"+limit.value());
        String key = getOrCreateKey(jp, limit);
        System.out.println("----------"+key);
        RateLimiter limiter = limiters.computeIfAbsent(
                key, (name -> RateLimiter.create(limit.value())));
        System.out.println(limiter);
        System.out.println("limiter.acquire()-------"+limiter.acquire());
    }

    private String getOrCreateKey(JoinPoint jp, RateLimit limit) {
        if( limit.key() == null ){
            return limit.key();
        }
        return JoinPointToStringHelper.toString(jp);
    }
}
