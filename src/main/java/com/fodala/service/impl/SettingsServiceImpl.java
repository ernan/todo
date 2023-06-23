package com.fodala.service.impl;

import com.fodala.mapper.SettingsMapper;
import com.fodala.pojo.Setting;
import com.fodala.pojo.SettingHistory;
import com.fodala.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingsServiceImpl implements SettingsService {

    @Autowired
    private SettingsMapper settingsMapper;

    @Override
    public List<Setting> all() {
        return settingsMapper.all();
    }

    @Override
    public Setting findById(Integer id) {
        return settingsMapper.findById(id);
    }

    @Override
    public void insert(Setting setting) {
        settingsMapper.insert(setting);
    }

    @Override
    public void update(Setting setting) {
        settingsMapper.update(setting);
    }

    @Override
    public void delete(Integer id) {
        settingsMapper.delete(id);
    }

    @Override
    public List<SettingHistory> history(Integer id) {
        return settingsMapper.history(id);
    }

    @Override
    public Setting createEmpty() {
        return new Setting();
    }
}