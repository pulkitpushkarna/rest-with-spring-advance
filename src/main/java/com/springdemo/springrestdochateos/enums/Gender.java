package com.springdemo.springrestdochateos.enums;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenderSerializer.class)
public enum  Gender {
    MALE("male"),FEMALE("female");

    private String displayName;

    Gender(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
