package com.fodala.pojo;

public class Session {
    private Integer id;
    private String section;
    private String name;
    private String value;
    private String description;
    private String seq;
    private String username;
    private String expiry;
    private String created;

    public Session() {
    }

    public Session(Integer id, String section, String name, String value, String description, String seq, String username, String expiry, String created) {
        this.id = id;
        this.section = section;
        this.name = name;
        this.value = value;
        this.description = description;
        this.seq = seq;
        this.username = username;
        this.expiry = expiry;
        this.created = created;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
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
