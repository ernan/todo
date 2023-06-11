package com.fodala.service.impl;

import com.fodala.mapper.ToDoMapper;
import com.fodala.pojo.ToDo;
import com.fodala.pojo.ToDoHistory;
import com.fodala.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class ToDoServiceImpl implements ToDoService {
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
        return mapper.insert(toDo);
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
}
