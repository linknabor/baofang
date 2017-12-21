package com.yumu.hexie.model;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.yumu.hexie.model.redis.Keys;
import com.yumu.hexie.model.system.SystemConfig;
import com.yumu.hexie.service.RefreshTokenService;

@Component(value = "multipleRepository")
public class MultipleRepository {

    private static final Logger SCHEDULE_LOG = LoggerFactory.getLogger("com.yumu.hexie.schedule");

    @Inject
    @Named("mainRedisTemplate")
    private RedisTemplate<String, SystemConfig> mainRedisTemplate;
    
    @Inject
    @Named("baofangRedisTemplate")
    private RedisTemplate<String, SystemConfig> baofangRedisTemplate;
    
    @Inject
    @Named("chunhuiRedisTemplate")
    private RedisTemplate<String, SystemConfig> chunhuiRedisTemplate;
    
    @Inject
    @Named("liangyouRedisTemplate")
    private RedisTemplate<String, SystemConfig> liangyouRedisTemplate;
    
    @Inject
    @Named("weifaRedisTemplate")
    private RedisTemplate<String, SystemConfig> weifaRedisTemplate;
   
    public void setSystemConfig(String key,SystemConfig value) {

        SCHEDULE_LOG.warn("update cache:" + key + "["+value+"]");
        
        String sysKey = Keys.systemConfigKey(key);
        
        mainRedisTemplate.opsForValue().set(sysKey, value, 120, TimeUnit.MINUTES);
        SystemConfig c = mainRedisTemplate.opsForValue().get(sysKey);
        if(c != null) {
            SCHEDULE_LOG.warn("get mainRedis cache:"+c.getSysKey() + "["+c.getSysValue()+"]");
        }
        
        baofangRedisTemplate.opsForValue().set(sysKey, value, 120, TimeUnit.MINUTES);
        c = baofangRedisTemplate.opsForValue().get(sysKey);
        if(c != null) {
            SCHEDULE_LOG.warn("get baofangRedis cache:"+c.getSysKey() + "["+c.getSysValue()+"]");
        }
        
        chunhuiRedisTemplate.opsForValue().set(sysKey, value, 120, TimeUnit.MINUTES);
        c = chunhuiRedisTemplate.opsForValue().get(sysKey);
        if(c != null) {
            SCHEDULE_LOG.warn("get chunhuiRedis cache:"+c.getSysKey() + "["+c.getSysValue()+"]");
        }
        
        liangyouRedisTemplate.opsForValue().set(sysKey, value, 120, TimeUnit.MINUTES);
        c = liangyouRedisTemplate.opsForValue().get(sysKey);
        if(c != null) {
            SCHEDULE_LOG.warn("get liangyouRedis cache:"+c.getSysKey() + "["+c.getSysValue()+"]");
        }
        
        weifaRedisTemplate.opsForValue().set(sysKey, value, 120, TimeUnit.MINUTES);
        c = weifaRedisTemplate.opsForValue().get(sysKey);
        if(c != null) {
            SCHEDULE_LOG.warn("get weifaRedis cache:"+c.getSysKey() + "["+c.getSysValue()+"]");
        }
        
        SCHEDULE_LOG.warn("END update cache:" + key + "["+value+"]");
    }
    
    /**
     * 为其他公众号SET ACCESS_TOKEN,除了合协以外的
     */
    public void setTokenBySysName(String sysName, String key, SystemConfig value){
    	
        SCHEDULE_LOG.warn("BEGIN set other cache:" + "sysName:" + sysName +",key :" + key + ", value ["+value+"]");
        
        String sysKey = Keys.systemConfigKey(key);
        
        if (RefreshTokenService.SYS_NAME_HEXIE.equals(sysName)) {
        	//合协的这里不做设置
		}else if (RefreshTokenService.SYS_NAME_BAOFANG.equals(sysName)) {
			baofangRedisTemplate.opsForValue().set(sysKey, value, 120, TimeUnit.MINUTES);
		}else if (RefreshTokenService.SYS_NAME_CHUNHUI.equals(sysName)) {
			baofangRedisTemplate.opsForValue().set(sysKey, value, 120, TimeUnit.MINUTES);	//在宝房的redis中也存上
			chunhuiRedisTemplate.opsForValue().set(sysKey, value, 120, TimeUnit.MINUTES);
		}else if (RefreshTokenService.SYS_NAME_LIANGYOU.equals(sysName)) {
			baofangRedisTemplate.opsForValue().set(sysKey, value, 120, TimeUnit.MINUTES);
			liangyouRedisTemplate.opsForValue().set(sysKey, value, 120, TimeUnit.MINUTES);
		}else if (RefreshTokenService.SYS_NAME_WEIFA.equals(sysName)) {
			baofangRedisTemplate.opsForValue().set(sysKey, value, 120, TimeUnit.MINUTES);
			weifaRedisTemplate.opsForValue().set(sysKey, value, 120, TimeUnit.MINUTES);
		}else if (RefreshTokenService.SYS_NAME_DHZJ1.equals(sysName)) {
			baofangRedisTemplate.opsForValue().set(sysKey, value, 120, TimeUnit.MINUTES);	//宝房也存上
			liangyouRedisTemplate.opsForValue().set(sysKey, value, 120, TimeUnit.MINUTES);	//dhzj开头的都存到良友的redis中，只是前缀不同
		}else if (RefreshTokenService.SYS_NAME_DHZJ2.equals(sysName)) {
			baofangRedisTemplate.opsForValue().set(sysKey, value, 120, TimeUnit.MINUTES);
			liangyouRedisTemplate.opsForValue().set(sysKey, value, 120, TimeUnit.MINUTES);
		}else if (RefreshTokenService.SYS_NAME_DHZJ3.equals(sysName)) {
			baofangRedisTemplate.opsForValue().set(sysKey, value, 120, TimeUnit.MINUTES);
			liangyouRedisTemplate.opsForValue().set(sysKey, value, 120, TimeUnit.MINUTES);
		}else if (RefreshTokenService.SYS_NAME_DHZJ4.equals(sysName)) {
			baofangRedisTemplate.opsForValue().set(sysKey, value, 120, TimeUnit.MINUTES);
			liangyouRedisTemplate.opsForValue().set(sysKey, value, 120, TimeUnit.MINUTES);
		}else if (RefreshTokenService.SYS_NAME_YOUYI.equals(sysName)) {
			baofangRedisTemplate.opsForValue().set(sysKey, value, 120, TimeUnit.MINUTES);
			liangyouRedisTemplate.opsForValue().set(sysKey, value, 120, TimeUnit.MINUTES);
		}else if (RefreshTokenService.SYS_NAME_GUANGMING.equals(sysName)) {
			baofangRedisTemplate.opsForValue().set(sysKey, value, 120, TimeUnit.MINUTES);
			liangyouRedisTemplate.opsForValue().set(sysKey, value, 120, TimeUnit.MINUTES);
		}else if (RefreshTokenService.SYS_NAME_ZHONGXIN.equals(sysName)) {
			baofangRedisTemplate.opsForValue().set(sysKey, value, 120, TimeUnit.MINUTES);
			liangyouRedisTemplate.opsForValue().set(sysKey, value, 120, TimeUnit.MINUTES);
		}
        SCHEDULE_LOG.warn("END set other cache:" + key + "["+value+"]");
    	
    }
    
    /**
     * 根据key从redis中取value
     * @param sysName
     * @param key
     */
	public SystemConfig getValueBySysKey(String sysName, String key){
    	
    	SystemConfig systemconfig = null; 
    	
    	String sysKey = Keys.systemConfigKey(key);
    	
    	if (RefreshTokenService.SYS_NAME_HEXIE.equals(sysName)) {
    		systemconfig = mainRedisTemplate.opsForValue().get(sysKey);
		}else if (RefreshTokenService.SYS_NAME_BAOFANG.equals(sysName)) {
			systemconfig = baofangRedisTemplate.opsForValue().get(sysKey);
		}else if (RefreshTokenService.SYS_NAME_CHUNHUI.equals(sysName)) {
			systemconfig = chunhuiRedisTemplate.opsForValue().get(sysKey);
		}else if (RefreshTokenService.SYS_NAME_LIANGYOU.equals(sysName)) {
			systemconfig = liangyouRedisTemplate.opsForValue().get(sysKey);
		}else if (RefreshTokenService.SYS_NAME_WEIFA.equals(sysName)) {
			systemconfig = weifaRedisTemplate.opsForValue().get(sysKey);
		}else if (RefreshTokenService.SYS_NAME_DHZJ1.equals(sysName)) {
			systemconfig = liangyouRedisTemplate.opsForValue().get(sysKey);
		}else if (RefreshTokenService.SYS_NAME_DHZJ2.equals(sysName)) {
			systemconfig = liangyouRedisTemplate.opsForValue().get(sysKey);
		}else if (RefreshTokenService.SYS_NAME_DHZJ3.equals(sysName)) {
			systemconfig = liangyouRedisTemplate.opsForValue().get(sysKey);
		}else if (RefreshTokenService.SYS_NAME_DHZJ4.equals(sysName)) {
			systemconfig = liangyouRedisTemplate.opsForValue().get(sysKey);
		}else if (RefreshTokenService.SYS_NAME_YOUYI.equals(sysName)) {
			systemconfig = liangyouRedisTemplate.opsForValue().get(sysKey);
		}else if (RefreshTokenService.SYS_NAME_LIANGYOU.equals(sysName)) {
			systemconfig = liangyouRedisTemplate.opsForValue().get(sysKey);
		}else if (RefreshTokenService.SYS_NAME_ZHONGXIN.equals(sysName)) {
			systemconfig = liangyouRedisTemplate.opsForValue().get(sysKey);
		}
    	return systemconfig;
    }

}
