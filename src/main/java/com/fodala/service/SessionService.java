package com.fodala.service;

import com.fodala.pojo.Session;
import com.fodala.pojo.SessionHistory;

import java.util.List;

public interface SessionService {

    List<Session> all();

    Session findById(Integer id);

    void insert(Session Session);

    void update(Session Session);

    void delete(Integer id);

    List<SessionHistory> history(Integer id);

    Session createEmpty();
}
