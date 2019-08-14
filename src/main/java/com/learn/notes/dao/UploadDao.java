package com.learn.notes.dao;

import com.learn.notes.entity.Picture;

import java.util.List;

public interface UploadDao {
    List<Picture> getAllPicture();

    String addPicName(String finalName);
}
