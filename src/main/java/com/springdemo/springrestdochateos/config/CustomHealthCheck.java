package com.springdemo.springrestdochateos.config;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthCheck extends AbstractHealthIndicator {


    public boolean checkCachingLayerErrors(){
        return true;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        if(checkCachingLayerErrors()){
            builder.down().withDetail("Caching layer message","It is down for some reason");
        }
    }
}
