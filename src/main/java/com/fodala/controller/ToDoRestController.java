package com.fodala.controller;

import com.fodala.pojo.ToDo;
import com.fodala.service.ToDoService;
import com.fodala.validation.ToDoValidationError;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
public class ToDoRestController {
    private static final Logger logger = LoggerFactory.getLogger(ToDoRestController.class);

    @Autowired
    private ToDoService toDoService;

    @GetMapping("/list/names")
    List<Map<String, Object>> listNames() {
        return toDoService.listNames();
    }

    @GetMapping("/list/{id}")
    List<ToDo> listItems(@PathVariable("id") Integer id) {
        return toDoService.listItems(id);
    }


    @GetMapping("/notifications")
    List<Map<String, Object>> notifications() {
        return toDoService.notifications();
    }

    @GetMapping(value = {"/todo"})
    public List<ToDo> getTodos(@RequestHeader Map<String, String> headers) {
        List<String> keys = List.of("tab", "filter", "page", "list_id");
        Map<String, String> filter = headers.entrySet()
                .stream()
                .filter(entry -> keys.contains(entry.getKey()))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
        filter.forEach((key, value) -> {
            logger.info("Filter Name: {} Filter Value: {}", key, value);
        });
        return toDoService.getToDos(filter);
    }

    @GetMapping("/todo/completed/{id}")
    public ToDo completed(@PathVariable Integer id) {
        toDoService.completed(id);
        return toDoService.findById(id);
    }

    @GetMapping("/todo/important/{id}")
    public ToDo important(@PathVariable Integer id) {
        toDoService.important(id);
        return toDoService.findById(id);
    }

    @RequestMapping(value = "/todo", method = {
            RequestMethod.POST,
            RequestMethod.PUT})
    public ResponseEntity<?> createToDo(@Valid @RequestBody ToDo toDo,
                                        Errors errors) {
        Integer id = toDoService.insert(toDo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}")
                .buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/todo/delete/{id}")
    public ResponseEntity<ToDo> deleteToDo(@PathVariable Integer id) {
        toDoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ToDoValidationError handleException(Exception exception) {
        return new ToDoValidationError(exception.getMessage());
    }
}
