package com.jt.web.util;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class HttpClientService {
	
	@Autowired
	private CloseableHttpClient httpClient;
	@Autowired
	private RequestConfig requestConfig;
	
	/**
	 * @param url 必须
	 * @param params 默认为空
	 * @param charset 默认为utf8
	 * @return 返回结果字符串
	 */
	public String doGet(String url,Map<String, String> params,String charset) {
		
		if(StringUtils.isEmpty(charset)) {
			charset = "UTF-8";
		}
		
		if(params != null) {
			Iterator<Entry<String, String>> it = params.entrySet().iterator();
			StringBuilder sb = new StringBuilder();
			sb.append("?");
			while (it.hasNext()) {
				Entry<String, String> e = it.next();
				String key = e.getKey();
				String value = e.getValue();
				sb.append(key + "=" + value + "&");
			}
			url = url + sb.substring(0, sb.length()-1);
		}
		System.out.println(url);
		HttpGet get = new HttpGet(url);
		get.setConfig(requestConfig);
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(get);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		String data = "null";
		if(response.getStatusLine().getStatusCode() == 200) {
			 try {
				data = EntityUtils.toString(response.getEntity(),"utf-8");
			} catch (ParseException | IOException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	
	public String doGet(String url) {
		return doGet(url,null,null);
	}
	
	public String doGet(String url,Map<String, String> params) {
		return doGet(url,params,null);
	}
}
