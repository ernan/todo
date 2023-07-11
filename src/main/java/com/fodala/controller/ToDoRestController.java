package com.fodala.controller;

import com.fodala.pojo.ToDo;
import com.fodala.service.ToDoService;
import com.fodala.validation.ToDoValidationError;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class ToDoRestController {

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

    @GetMapping("/todo")
    public List<ToDo> toDos() {
        return toDoService.all();
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

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<ToDo> deleteToDo(@PathVariable Integer id) {
        toDoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/todo")
    public ResponseEntity<ToDo> deleteToDo(@RequestBody ToDo toDo) {
        toDoService.delete(toDo.getId());
        return ResponseEntity.noContent().build();
    }


    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ToDoValidationError handleException(Exception exception) {
        return new ToDoValidationError(exception.getMessage());
    }
}
