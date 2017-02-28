package com.yumu.hexie.integration.charger;

import com.yumu.hexie.integration.wuye.WuyeUtil;
import com.yumu.hexie.integration.wuye.resp.BaseResult;

public class ChargerUtil {

	private static String REQUEST_ADDRESS = "http://www.e-shequ.com/mobileInterface/charger/";
	
	private static final String MARGER_USE_URL = "addMargerUseSDO.do?phone=%s&user_id=%s&sect_id=%s&sect_name=%s&no=%s";
	
	// 1.云充用户创建
	public static BaseResult<String> saveMarger(String phone, long user_id, long sect_id, String sect_name, String public_no){
		
		String url = REQUEST_ADDRESS + String.format(MARGER_USE_URL, phone, user_id, sect_id, sect_name, public_no);
		return (BaseResult<String>)WuyeUtil.httpGet(url,String.class);
		
	}
	
}
