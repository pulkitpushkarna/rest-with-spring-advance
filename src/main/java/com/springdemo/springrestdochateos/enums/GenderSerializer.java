package com.springdemo.springrestdochateos.enums;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class GenderSerializer extends StdSerializer<Gender> {

    protected GenderSerializer(Class<Gender> t) {
        super(t);
    }

    protected GenderSerializer() {
        super(Gender.class);
    }

    @Override
    public void serialize(Gender value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeFieldName("name");
        gen.writeObject(value.name());
        gen.writeFieldName("displayName");
        gen.writeObject(value.getDisplayName());
        gen.writeEndObject();
    }
}
