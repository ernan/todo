package com.fodala.controller;

import com.fodala.pojo.Event;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventController {

    @PostMapping("/events")
    public void handleEvent(@RequestBody Event event) {
        // Do something with the event
        System.out.println("Received event: " + event);
    }
}
