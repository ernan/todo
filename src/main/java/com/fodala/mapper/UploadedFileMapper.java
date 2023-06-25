package com.fodala.mapper;

import com.fodala.pojo.UploadedFile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UploadedFileMapper {
    List<UploadedFile> all();

    UploadedFile findOne(Long id);

    long count();

    int insert(UploadedFile row);

    void update(UploadedFile user);

    void delete(Long id);
}