package com.fodala.pojo;

public class Setting {
    private Integer id;
    private String section;
    private String status;
    private String name;
    private String value;
    private String description;
    private String seq;
    private String username;

    public Setting(Integer id, String section, String name, String status, String value, String description, String seq, String username) {
        this.id = id;
        this.section = section;
        this.name = name;
        this.status = status;
        this.value = value;
        this.description = description;
        this.seq = seq;
        this.username = username;
    }

    public Setting() {

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
