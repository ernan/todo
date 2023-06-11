package com.fodala.mapper;

import com.fodala.pojo.Setting;
import com.fodala.pojo.SettingHistory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettingsMapper {

    List<Setting> all();

    Setting findById(@Param("id") Integer id);

    void insert(@Param("setting") Setting Setting);

    void update(@Param("setting") Setting Setting);

    void delete(@Param("id") Integer id);

    List<SettingHistory> history(@Param("id") Integer id);
}
