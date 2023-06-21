package com.fodala.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fodala.json.ToDoSerializer;
import com.fodala.pojo.ToDo;
import com.fodala.service.ToDoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CalendarController {
    private static final Logger logger = LoggerFactory.getLogger(CalendarController.class);

    @Autowired
    private ToDoService toDoService;

    @GetMapping("/calendar")
    public String calendar(Model model) {
        model.addAttribute("currentTab", "calendar");
        model.addAttribute("todos", toJSON(toDoService.all()));
        return "calendar/index";
    }

    String toJSON(List<ToDo> list) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
                new SimpleModule("ToDoSerializer", new Version(1, 0, 0, null, null, null));
        module.addSerializer(ToDo.class, new ToDoSerializer(ToDo.class));
        mapper.registerModule(module);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        StringBuilder builder = new StringBuilder("[");
        for (ToDo toDo : list) {
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

//        JsonFactory jsonFactory = new JsonFactory();
//        // Create a JsonGenerator instance
//        try (JsonGenerator jsonGenerator = jsonFactory.createGenerator(os)) {
//            // Configure the JsonGenerator to pretty print the output
//            jsonGenerator.useDefaultPrettyPrinter();
//            // Write the start array token
//            jsonGenerator.writeStartArray();
//            // Iterate over the contacts and write each contact as a JSON object
//            for (Contact contact : contacts) {
//                writeContact(jsonGenerator, contact);
//            }
//            // Write the end array token
//            jsonGenerator.writeEndArray();
//        }


        String result = builder.toString();
        logger.info(result);
        return builder.toString();
    }
}
