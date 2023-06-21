package com.fodala.controller;

import com.fodala.service.JSONService;
import com.fodala.service.ToDoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
public class NotificationsController {
    private static final Logger logger = LoggerFactory.getLogger(NotificationsController.class);

    @Autowired
    private ToDoService toDoService;

    @Autowired
    private JSONService jsonService;

    @RequestMapping(value = "/notifications", method = RequestMethod.GET)
    public String notifications(Model model) {
        logger.info("Getting notifications");
        model.addAttribute("currentTab", "notifications");
        List<Map<String, Object>> res = toDoService.notifications();
        String json = jsonService.listToJSON(res);
        model.addAttribute("notifications", json);
        model.addAttribute("count", toDoService.count());
        return "notifications/index";
    }
}
