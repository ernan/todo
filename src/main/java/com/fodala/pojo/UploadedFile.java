package com.fodala.pojo;


import java.util.Date;

public class UploadedFile {
    private Integer id;
    private String name;
    private String uploadedPath;
    private Integer fileSize;
    private Date uploadedDate;
    private String userName;
    private String fileType;
    private String md5;

    public UploadedFile() {
    }

    public UploadedFile(Integer id, String name, String uploadedPath, Integer fileSize, Date uploadedDate, String userName, String fileType, String md5) {
        this.id = id;
        this.name = name;
        this.uploadedPath = uploadedPath;
        this.fileSize = fileSize;
        this.uploadedDate = uploadedDate;
        this.userName = userName;
        this.fileType = fileType;
        this.md5 = md5;
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

    public String getUploadedPath() {
        return uploadedPath;
    }

    public void setUploadedPath(String uploadedPath) {
        this.uploadedPath = uploadedPath;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public Date getUploadedDate() {
        return uploadedDate;
    }

    public void setUploadedDate(Date uploadedDate) {
        this.uploadedDate = uploadedDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}