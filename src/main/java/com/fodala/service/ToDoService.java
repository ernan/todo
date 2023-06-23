package com.fodala.service;

import com.fodala.pojo.ToDo;
import com.fodala.pojo.ToDoHistory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ToDoService {
    List<ToDo> all();

    ToDo findById(Integer id);

    void important(Integer id);

    void completed(Integer id);

    Integer insert(ToDo ToDo);

    void update(ToDo ToDo);

    void delete(Integer id);

    List<ToDoHistory> history(Integer id);

    ToDo createEmpty();

    Map<String, Object> count();

    List<ToDo> findByDate(LocalDateTime start, LocalDateTime end);

    List<ToDo> filter(Map<String, Object> map);

    List<ToDo> search(String search);

    List<Map<String, Object>> notifications();

    List<Map<String, Object>> listNames();

    List<ToDo> list(String name);

    void createList(String list);
}
