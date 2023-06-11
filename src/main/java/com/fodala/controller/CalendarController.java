package com.fodala.controller;

import com.fodala.service.ToDoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalendarController {
    private static final Logger logger = LoggerFactory.getLogger(CalendarController.class);

    @Autowired
    private ToDoService toDoService;

    @GetMapping("/calendar")
    public String calendar(Model model) {
        model.addAttribute("todos", toDoService.all());
        model.addAttribute("currentTab", "calendar");
        return "calendar/index";
    }
}
