package com.fodala.pojo;

public class ToDoHistory {
    private Integer id;
    private String title;
    private String status;
    private String description;
    private Integer completed;
    private String start;
    private String end;
    private String schedule;
    private String username;
    private String important;
    private String created;
    private String updated;

    public ToDoHistory() {
    }

    public ToDoHistory(Integer id, String title, String status, String description, Integer completed, String start, String end, String schedule, String username, String important, String created, String updated) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.description = description;
        this.completed = completed;
        this.start = start;
        this.end = end;
        this.schedule = schedule;
        this.username = username;
        this.important = important;
        this.created = created;
        this.updated = updated;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImportant() {
        return important;
    }

    public void setImportant(String important) {
        this.important = important;
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
}
