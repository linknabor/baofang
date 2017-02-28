package com.yumu.hexie.service.picture.impl;

import com.yumu.hexie.model.picture.Picture;
import com.yumu.hexie.model.picture.PictureRepository;

import javax.inject.Inject;
import com.yumu.hexie.service.picture.PictureService;
import org.springframework.stereotype.Service;

/**   Twitter : @taylorwang789 
 * Creat time : Sep 8, 2016    4:14:54 PM
 */
@Service("pictureService")
public class PictureServiceImpl implements PictureService{

    @Inject 
    private PictureRepository pictureRepository; 

    public Picture findByCode(String code){
        return pictureRepository.findByCode(code);
    }
}
