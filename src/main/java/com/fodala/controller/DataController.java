package com.fodala.controller;

import com.fodala.pojo.ToDo;
import com.fodala.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class DataController {

    @Autowired
    private ToDoService toDoService;

    @GetMapping("/list/names")
    List<Map<String, Object>> listNames() {
        return toDoService.listNames();
    }

    @GetMapping("/todos")
    List<ToDo> todos() {
        return toDoService.all();
    }

    @GetMapping("/notifications")
    List<Map<String, Object>> notifications() {
        return toDoService.notifications();
    }
}
