package com.fodala.pojo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ToDo {
    private Integer id;
    private String title;
    private Integer completed;
    private String status;
    private String description;
    private String start;
    private String end;
    private String schedule;
    private String username;
    private Integer important;
    private String created;

    public ToDo() {
    }

    public ToDo(Integer id, String title, String status, String description, Integer completed, String start, String end, String schedule, String username, Integer important, String created) {
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

    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", schedule='" + schedule + '\'' +
                ", username='" + username + '\'' +
                ", important=" + important +
                ", created='" + created + '\'' +
                '}';
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
        if (completed == 1) {
            end = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        } else {
            end = "";
        }
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

    public Integer getImportant() {
        return important;
    }

    public void setImportant(Integer important) {
        this.important = important;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
