/**
 * Yumu.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.yumu.hexie.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.json.JSONException;
import org.springframework.stereotype.Service;

import com.yumu.hexie.common.util.JacksonJsonUtil;
import com.yumu.hexie.integration.wechat.entity.AccessToken;
import com.yumu.hexie.model.MultipleRepository;
import com.yumu.hexie.model.system.SystemConfig;
import com.yumu.hexie.model.system.SystemConfigRepository;
import com.yumu.hexie.service.SharedSysConfigService;

/**
 * <pre>
 * 
 * </pre>
 *
 * @author tongqian.ni
 * @version $Id: SharedSysConfigServiceImpl.java, v 0.1 2016年5月9日 下午9:42:55  Exp $
 */
@Service("sharedSysConfigService")
public class SharedSysConfigServiceImpl implements SharedSysConfigService {
	
	private static final String JS_TOKEN = "JS_TOKEN";
	private static final String ACC_TOKEN = "ACCESS_TOKEN";
    public static final String APP_ACC_TOKEN = "APP_TOKEN_%s";

    @Inject
    private SystemConfigRepository systemConfigRepository;
    @Inject
    private MultipleRepository multipleRepository;
    
    public void saveAccessTokenInfo(AccessToken at) {
        try {
            SystemConfig config = null;
            List<SystemConfig> configs = systemConfigRepository
                    .findAllBySysKey(ACC_TOKEN);
            if (configs.size() > 0) {
                config = configs.get(0);
                config.setSysValue(JacksonJsonUtil.beanToJson(at));
            } else {
                config = new SystemConfig(ACC_TOKEN, JacksonJsonUtil.beanToJson(at));
            }
            multipleRepository.setSystemConfig(ACC_TOKEN,config);
            systemConfigRepository.save(config);
        } catch (JSONException e) {
        }
    }
    
    public void saveJsToken(String jsToken) {
        SystemConfig config = null;
        List<SystemConfig> configs = systemConfigRepository
                .findAllBySysKey(JS_TOKEN);
        if (configs.size() > 0) {
            config = configs.get(0);
            config.setSysValue(jsToken);
        } else {
            config = new SystemConfig(JS_TOKEN, jsToken);
        }
        multipleRepository.setSystemConfig(JS_TOKEN,config);
        systemConfigRepository.save(config);
    }

	public void saveOtherAccessTokenInfo(String appId, AccessToken at) {
		
		if (at!=null) {
			try {
				SystemConfig config = null;
				List<SystemConfig> configs = systemConfigRepository.findAllBySysKey(String.format(APP_ACC_TOKEN, appId));
				    if (configs.size() > 0) {
				        config = configs.get(0);
				        config.setSysValue(JacksonJsonUtil.beanToJson(at));
				    } else {
				        config = new SystemConfig(String.format(APP_ACC_TOKEN, appId),
				            JacksonJsonUtil.beanToJson(at));
				    }
				
				multipleRepository.setOtherAccessToken(String.format(APP_ACC_TOKEN, appId), config);
				
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

	}
}
