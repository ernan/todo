package com.fodala.mapper;

import com.fodala.pojo.UploadedFile;

import java.util.List;

public interface UploadedFileMapper {
    List<UploadedFile> findAll();
    UploadedFile findOne(Long id);
    long count();
    int insert(UploadedFile row);
    void update(UploadedFile user);
    void delete(Long id);
}