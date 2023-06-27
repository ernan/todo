package com.fodala.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fodala.pojo.ToDo;

import java.io.IOException;

public class ToDoSerializer extends StdSerializer<ToDo> {
    private static final long serialVersionUID = -725452707948867695L;

    public ToDoSerializer(Class<ToDo> t) {
        super(t);
    }

    @Override
    public void serialize(ToDo toDo, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", toDo.getId());
        gen.writeStringField("title", toDo.getTitle());
        gen.writeNumberField("completed", toDo.getCompleted());
        gen.writeNumberField("important", toDo.getImportant());
        if (toDo.getStart() != null) {
            gen.writeStringField("start", toDo.getStart());
        }
        if (toDo.getEnd() != null) {
            gen.writeStringField("end", toDo.getEnd());
        }
        gen.writeEndObject();
    }
}
