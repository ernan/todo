package com.fodala.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fodala.json.ToDoSerializer;
import com.fodala.pojo.ToDo;
import com.fodala.service.JSONService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JSONServiceImpl implements JSONService {
    private static final Logger logger = LoggerFactory.getLogger(JSONServiceImpl.class);

    @Override
    public String toJSON(List<ToDo> todos) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
                new SimpleModule("ToDoSerializer", new Version(1, 0, 0, null, null, null));
        module.addSerializer(ToDo.class, new ToDoSerializer(ToDo.class));
        mapper.registerModule(module);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        StringBuilder builder = new StringBuilder("[");
        for (ToDo toDo : todos) {
            try {
                String json = ow.writeValueAsString(toDo);
                if (builder.length() > 2 && json.length() > 0) {
                    builder.append(",");
                }
                builder.append(json);
            } catch (JsonProcessingException e) {
                logger.error(e.getMessage(), e);
            }
        }
        builder.append("]");
        return builder.toString();
    }

    @Override
    public String listToJSON(List<Map<String, Object>> list) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(list);
            logger.info(json);
            return json;
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }


    @Override
    public String stringListToJSON(List<String> list) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(list);
            logger.info(json);
            return json;
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

}
