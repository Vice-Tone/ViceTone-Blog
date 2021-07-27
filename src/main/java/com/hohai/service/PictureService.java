package com.hohai.service;

import com.hohai.entity.Picture;

import java.util.List;

/**
 * @author ：jin
 * @date ：Created in 2020/9/28 10:00 上午
 */
public interface PictureService {
    List<Picture> getAll();

    int addPicture(Picture picture);

    void deleteById(Long id);

    Picture findPictureById(Long id);

    int update(Picture picture);
}
