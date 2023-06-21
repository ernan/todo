package com.fodala.mapper;

import com.fodala.pojo.ToDo;
import com.fodala.pojo.ToDoHistory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ToDoMapper {
    List<ToDo> all();

    ToDo findById(@Param("id") Integer id);

    void important(@Param("id") Integer id);

    void completed(@Param("id") Integer id);

    Integer insert(@Param("toDo") ToDo toDo);

    void update(@Param("toDo") ToDo toDo);

    void delete(@Param("id") Integer id);

    List<ToDoHistory> history(@Param("id") Integer id);

    List<ToDo> findByDate(@Param("start") String start, @Param("end") String end);

    List<ToDo> filter(@Param("map") Map<String, Object> map);

    Map<String, Object> count();

    List<Map<String, Object>> notifications();
}
