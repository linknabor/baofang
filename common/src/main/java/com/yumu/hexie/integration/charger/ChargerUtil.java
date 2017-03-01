package com.yumu.hexie.integration.charger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.yumu.hexie.integration.wuye.WuyeUtil;
import com.yumu.hexie.integration.wuye.resp.BaseResult;

public class ChargerUtil {

	private static String REQUEST_BACKMNG_ADDRESS = "http://www.e-shequ.com/backmng/charger/";
	
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
	}
	
	private static final String MARGER_USE_URL = "addMargerUseSDO.do?phone=%s&user_id=%s&sect_id=%s&sect_name=%s&no=%s";
	
	// 1.云充用户创建
	public static BaseResult<String> saveMarger(String phone, long user_id, long sect_id, String sect_name, String public_no){
		
		String url = REQUEST_BACKMNG_ADDRESS + String.format(MARGER_USE_URL, phone, user_id, sect_id, sect_name, public_no);
		return (BaseResult<String>)WuyeUtil.httpGet(url,String.class);
		
	}
	
}
