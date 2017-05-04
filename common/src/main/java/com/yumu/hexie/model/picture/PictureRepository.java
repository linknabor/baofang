package com.yumu.hexie.model.picture;

import org.springframework.data.jpa.repository.JpaRepository;


/**   Twitter : @taylorwang789 
 * Creat time : Sep 8, 2016    4:10:33 PM
 */
public interface PictureRepository  extends JpaRepository<Picture, Long>  {
    
    Picture findByCode(String code);

}
