package com.fodala.mapper;

import com.fodala.pojo.Session;
import com.fodala.pojo.SessionHistory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionMapper {

    List<Session> all();

    Session findById(@Param("id") Integer id);

    void insert(@Param("session") Session session);

    void update(@Param("session") Session session);

    void delete(@Param("id") Integer id);

    List<SessionHistory> history(@Param("id") Integer id);
}
