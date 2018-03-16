package com.cs.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.alibaba.fastjson.JSONObject;
import com.cs.util.HttpUtils;

public class TokenTask {
	/**
	 * @Description: 任务执行体,获取token需定时刷新获取，可保存到缓存中
	 * @param @throws
	 *            Exception
	 * @author cs
	 */
	public void getToken_getTicket() throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		ResourceBundle config = ResourceBundle.getBundle("weixin");
		params.put("grant_type", "client_credential");
		params.put("appid", config.getString("weixin.test.appid"));
		params.put("secret", config.getString("weixin.test.appSecret"));
		String jstoken = HttpUtils.sendGet(config.getString("tokenUrl"), params);
		System.out.println(jstoken);
		String access_token = JSONObject.parseObject(jstoken).getString("access_token"); // 获取到 token 并赋值保存
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
				+ "token 为==============================" + access_token);
	}
	
	public static void main(String[] args) throws Exception {
		TokenTask task=new TokenTask();
		task.getToken_getTicket();
	}
}
