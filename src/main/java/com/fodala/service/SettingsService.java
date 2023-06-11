package com.fodala.service;

import com.fodala.pojo.Setting;
import com.fodala.pojo.SettingHistory;

import java.util.List;

public interface SettingsService {

    List<Setting> all();

    Setting findById(Integer id);

    void insert(Setting Setting);

    void update(Setting Setting);

    void delete(Integer id);

    List<SettingHistory> history(Integer id);

    Setting createEmpty();
}
