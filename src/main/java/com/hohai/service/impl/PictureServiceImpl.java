package com.hohai.service.impl;

import com.hohai.dao.PictureDao;
import com.hohai.entity.Picture;
import com.hohai.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：jin
 * @date ：Created in 2020/9/28 10:00 上午
 */
@Service
public class PictureServiceImpl implements PictureService {
    
    @Autowired
    private PictureDao pictureDao;
    
    @Override
    public List<Picture> getAll() {
        return pictureDao.getAll();
    }

    @Override
    public int addPicture(Picture picture) {
        return pictureDao.addPicture(picture);
    }

    @Override
    public void deleteById(Long id) {
        pictureDao.deleteById(id);
    }

    @Override
    public Picture findPictureById(Long id) {
        return pictureDao.findPictureById(id);
    }

    @Override
    public int update(Picture picture) {
        return pictureDao.update(picture);
    }
}
