package com.fodala.service.impl;

import com.fodala.controller.ToDoController;
import com.fodala.mapper.ToDoMapper;
import com.fodala.pojo.ToDo;
import com.fodala.pojo.ToDoHistory;
import com.fodala.service.ToDoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class ToDoServiceImpl implements ToDoService {
    private static final Logger logger = LoggerFactory.getLogger(ToDoServiceImpl.class);

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    @Autowired
    ToDoMapper mapper;

    @Override
    public List<ToDo> all() {
        return mapper.all();
    }

    @Override
    public ToDo findById(Integer id) {
        return mapper.findById(id);
    }

    @Override
    public void important(Integer id) {
        mapper.important(id);
    }

    @Override
    public void completed(Integer id) {
        mapper.completed(id);
    }

    @Override
    public Integer insert(ToDo toDo) {
        mapper.insert(toDo);
        return mapper.lastInsert();
    }

    @Override
    public void update(ToDo toDo) {
        mapper.update(toDo);
    }

    @Override
    public void delete(Integer id) {
        mapper.delete(id);
    }

    @Override
    public List<ToDoHistory> history(Integer id) {
        return mapper.history(id);
    }

    @Override
    public ToDo createEmpty() {
        return new ToDo();
    }

    @Override
    public Map<String, Object> count() {
        return mapper.count();
    }


    @Override
    public List<ToDo> findByDate(LocalDateTime start, LocalDateTime end) {
        return mapper.findByDate(start.format(formatter), end.format(formatter));
    }

    @Override
    public List<ToDo> filter(Map<String, Object> map) {
        return mapper.filter(map);
    }

    @Override
    public List<ToDo> search(String search) {
        return mapper.search(search);
    }

    @Override
    public List<Map<String, Object>> notifications() {
        return mapper.notifications();
    }

    @Override
    public List<Map<String, Object>> listNames() {
        return mapper.listNames();
    }

    @Override
    public List<ToDo> list(String name) {
        return mapper.list(name);
    }

    @Override
    public void createList(String list) {
        mapper.createList(list);
    }

    @Override
    public List<ToDo> listItems(Integer id) {
        return mapper.listItems(id);
    }

    @Override
    public void insertListItem(Integer toDoId, Integer listId) {
        mapper.insertListItem(toDoId, listId);
    }

    @Override
    public List<ToDo> getToDos(Map<String, String> params) {
        ToDoController.Filter filter = ToDoController.Filter.parse(params.get("filter"));
        ToDoController.Tab tab = ToDoController.Tab.parse(params.get("tab"));
        List<ToDo> result;
        switch (tab) {
            case PLANNED -> {
                LocalDateTime start = LocalDateTime.now();
                LocalDateTime end = LocalDateTime.now().plusYears(100L);
                result = findByDate(start, end);
            }
            case MY_DAY -> {
                LocalDateTime start = LocalDateTime.now();
                LocalDateTime end = LocalDateTime.now().plusDays(1L);
                result = findByDate(start, end);
            }
            case IMPORTANT -> {
                result = filter(Collections.singletonMap("important", 1));
            }
            case LIST -> {
                result = listItems(Integer.valueOf(params.get("list_id")));
            }
            default -> {
                result = all();
            }
        }
        return ToDoController.filter(filter, result);
    }
}
