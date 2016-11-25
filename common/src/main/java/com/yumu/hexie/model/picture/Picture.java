package com.yumu.hexie.model.picture;

import com.yumu.hexie.model.BaseModel;
import javax.persistence.Entity;

/**   Twitter : @taylorwang789 
 * Creat time : Sep 8, 2016    4:04:32 PM
 */
@Entity
public class Picture extends BaseModel {
    
     private String code ;
     private String describe;
     private String url;

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getDescribe() {
        return describe;
    }
    public void setDescribe(String describe) {
        this.describe = describe;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
     


}
