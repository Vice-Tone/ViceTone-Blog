package com.hohai.dao;

import com.hohai.entity.Picture;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：jin
 * @date ：Created in 2020/9/28 10:04 上午
 */
@Repository
public interface PictureDao {
    List<Picture> getAll();

    int addPicture(Picture picture);

    void deleteById(Long id);

    Picture findPictureById(Long id);

    int update(Picture picture);
}
