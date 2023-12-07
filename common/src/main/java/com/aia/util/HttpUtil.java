package com.aia.util;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author yangxy
 * @version 创建时间：2023年9月13日 上午9:23:25
 */
@Component
public class HttpUtil {
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * 发送get请求
	 * 
	 * @author yangxy
	 * @version 创建时间：2023年9月13日 上午9:26:25
	 * @param url请求地址
	 * @param headerMap 请求头
	 * @return
	 */
	public ResponseEntity<String> doGet(String url,Map<String,String> headerMap) {
		HttpHeaders headers = new HttpHeaders();
		if(!headerMap.isEmpty()) {
			headers.setAll(headerMap);
		}
		HttpEntity entity = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		return response;
	}
	
	/**
	 * 发送json参数post请求
	* @author yangxy
	* @version 创建时间：2023年9月13日 上午9:29:27 
	* @param url 请求地址
	* @param jsonStr json格式字符串请求参数
	* @param headeMap 请求头参数
	* @return
	 */
	public ResponseEntity<String> doJsonPost(String url, String jsonStr,Map<String,String> headerMap) {
		// 请求头中设备传递的数据类型未json
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		if(!headerMap.isEmpty()) {
			headers.setAll(headerMap);
		}

		// 将json数据及请求头封装到请求体中
		HttpEntity<String> formEntity = new HttpEntity<String>(jsonStr, headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, formEntity, String.class);
		return response;
	}
}
