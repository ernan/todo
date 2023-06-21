package com.fodala.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fodala.pojo.ToDo;

import java.io.IOException;

public class ToDoSerializer extends StdSerializer<ToDo> {
    public ToDoSerializer(Class<ToDo> t) {
        super(t);
    }

    @Override
    public void serialize(ToDo toDo, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("title", toDo.getTitle());
        gen.writeNumberField("id", toDo.getId());
        if (toDo.getStart() != null) {
            gen.writeStringField("start", toDo.getStart());
        }
        if (toDo.getEnd() != null) {
            gen.writeStringField("end", toDo.getEnd());
        }
        gen.writeEndObject();
    }
}
