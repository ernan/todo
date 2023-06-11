package com.fodala.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class LogController {
    @GetMapping("/logs")
    public ResponseEntity<String> getLogs() throws IOException {
        Path logFile = Paths.get(System.getProperty("user.home"), "todo/log/todo.log");
        String logs = new String(Files.readAllBytes(logFile));
        return ResponseEntity.ok(logs);
    }
}
