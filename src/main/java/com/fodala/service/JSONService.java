package com.fodala.service;

import com.fodala.pojo.ToDo;

import java.util.List;
import java.util.Map;

public interface JSONService {
    String toJSON(List<ToDo> todos);

    String listToJSON(List<Map<String, Object>> list);
}
