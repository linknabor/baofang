package com.yumu.hexie.integration.charger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yumu.hexie.integration.wuye.WuyeUtil;
import com.yumu.hexie.integration.wuye.resp.BaseResult;

public class ChargerUtil {

	private static final Logger Log = LoggerFactory.getLogger(ChargerUtil.class);
	
	private static String REQUEST_BACKMNG_ADDRESS = "http://www.e-shequ.com/backmng/charger/";
	private static String SYSTEM_NAME;
	private static Properties props = new Properties();
	
	static {
		try {
			props.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("wechat.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		REQUEST_BACKMNG_ADDRESS = props.getProperty("requestBackmngUrl");
		SYSTEM_NAME = props.getProperty("sysName");
	}
	
	private static final String MARGER_USE_URL = "addMargerUseSDO.do?phone=%s&user_id=%s&sect_id=%s&from_sys=%s&sect_name=%s";
	
	// 1.云充用户创建
	public static BaseResult<String> saveMarger(String phone, long user_id, long sect_id, String sect_name){
		String url = "";
		try {
			url = REQUEST_BACKMNG_ADDRESS + String.format(MARGER_USE_URL, phone, user_id, sect_id, SYSTEM_NAME, URLEncoder.encode(sect_name,"GBK") );
		} catch (UnsupportedEncodingException e) {
			Log.error("注册失败！！！！！！！！！！！", e);
		}
		return (BaseResult<String>)WuyeUtil.httpGet(url,String.class);
		
	}
	
}