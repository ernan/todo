package com.fodala.service.impl;

import com.fodala.mapper.SessionMapper;
import com.fodala.pojo.Session;
import com.fodala.pojo.SessionHistory;
import com.fodala.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionMapper sessionMapper;

    @Override
    public List<Session> all() {
        return sessionMapper.all();
    }

    @Override
    public Session findById(Integer id) {
        return sessionMapper.findById(id);
    }

    @Override
    public void insert(Session session) {
        sessionMapper.insert(session);
    }

    @Override
    public void update(Session session) {
        sessionMapper.update(session);
    }

    @Override
    public void delete(Integer id) {
        sessionMapper.delete(id);
    }

    @Override
    public List<SessionHistory> history(Integer id) {
        return sessionMapper.history(id);
    }

    @Override
    public Session createEmpty() {
        return new Session();
    }

}