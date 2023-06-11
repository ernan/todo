package com.fodala.pojo;

public class SettingHistory {
    private Integer id;
    private Integer settingId;
    private String section;
    private String status;
    private String name;
    private String value;
    private String description;
    private String seq;
    private String username;
    private String created;
    private String updated;

    public SettingHistory(Integer id, Integer settingId, String section, String name, String status, String value, String description, String seq, String username, String created, String updated) {
        this.id = id;
        this.settingId = settingId;
        this.section = section;
        this.name = name;
        this.status = status;
        this.value = value;
        this.description = description;
        this.seq = seq;
        this.username = username;
        this.created = created;
        this.updated = updated;
    }

    public SettingHistory() {

    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public Integer getSettingId() {
        return settingId;
    }

    public void setSettingId(Integer settingId) {
        this.settingId = settingId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

}
