package com.yumu.hexie.integration.charger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Properties;

import javax.net.ssl.SSLContext;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
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
		return (BaseResult<String>)httpsRequest(url,String.class);
	}
	
	private static BaseResult httpsRequest(String requestUrl, Class c){
		HttpClient httpClient = null;
		Log.error("REQ:" + requestUrl);
		try
		{
			httpClient = createSSLInsecureClient();
			HttpGet request = new HttpGet(requestUrl);
			HttpResponse response = httpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			{
				String result = EntityUtils.toString(response.getEntity(), "UTF-8");
				Log.error("RESP:" + result);
				return WuyeUtil.jsonToBeanResult(result, c);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			Log.error("err msg :" + e.getMessage());
		}
		BaseResult r= new BaseResult();
		r.setResult("99");
		return r;
	}
	
	public static CloseableHttpClient createSSLInsecureClient()
	{
		SSLConnectionSocketFactory sslsf = null;
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] chain,String authType) throws CertificateException {
                    return true;
                }
            }).build();
		    sslsf = new SSLConnectionSocketFactory(sslContext);
		    return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
			e.printStackTrace();
		}
		return HttpClients.custom().setSSLSocketFactory(sslsf).build();
	}
}