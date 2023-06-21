package com.fodala.controller;

import com.fodala.service.JSONService;
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

    @Autowired
    private JSONService jsonService;

    @GetMapping("/calendar")
    public String calendar(Model model) {
        model.addAttribute("currentTab", "calendar");
        model.addAttribute("todos", jsonService.toJSON(toDoService.all()));
        model.addAttribute("count", toDoService.count());
        return "calendar/index";
    }
}
