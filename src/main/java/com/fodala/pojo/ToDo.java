package com.fodala.pojo;

public class ToDo {
    private Integer id;
    private String title;
    private String status;
    private String description;
    private Integer completed;
    private String expires;
    private String schedule;
    private String username;
    private Integer important;
    private String created;

    public ToDo() {
    }

    public ToDo(Integer id, String title, String status, String description, Integer completed, String expires, String schedule, String username, Integer important, String created) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.description = description;
        this.completed = completed;
        this.expires = expires;
        this.schedule = schedule;
        this.username = username;
        this.important = important;
        this.created = created;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", completed=" + completed +
                ", title='" + title + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", expires='" + expires + '\'' +
                ", schedule='" + schedule + '\'' +
                ", username='" + username + '\'' +
                ", important=" + important +
                ", created='" + created + '\'' +
                '}';
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

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
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
