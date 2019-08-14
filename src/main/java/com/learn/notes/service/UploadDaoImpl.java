package com.learn.notes.service;

import com.learn.notes.dao.UploadDao;
import com.learn.notes.entity.Picture;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "uploadDaoImpl")
public class UploadDaoImpl implements UploadDao {
    @Resource
    private UploadDao uploadDao;

    @Override
    public List<Picture> getAllPicture() {
        return uploadDao.getAllPicture();
    }

    @Override
    public String addPicName(String finalName) {
        return uploadDao.addPicName(finalName);
    }
}
