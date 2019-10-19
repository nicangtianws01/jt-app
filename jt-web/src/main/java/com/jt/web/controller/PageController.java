package com.jt.web.controller;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/")
@Controller
public class PageController {
	@GetMapping("index")
	public String index() {
		return "index";
	}
	@GetMapping("getData")
	@ResponseBody
	public String  getData() throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		String url = "https://www.baidu.com";
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse response = client.execute(get);
		String data = "null";
		if(response.getStatusLine().getStatusCode() == 200) {
			 data = EntityUtils.toString(response.getEntity(),"utf-8");
		}
		return data;
	}
}
