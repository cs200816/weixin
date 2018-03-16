package com.cs.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.cs.entity.menu.Menu;

public class CommonUtil {

	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

	// 获取access_token的接口地址（GET） 限200（次/天）
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	// 菜单创建（POST） 限100（次/天）
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	/**
	 * 创建菜单
	 * 
	 * @param menu
	 *            菜单实例
	 * @param accessToken
	 *            有效的access_token
	 * @return 0表示成功，其他值表示失败
	 */
	public static int createMenu(Menu menu, String accessToken) {
		int result = 0;
		// 拼装创建菜单的url
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.toJSONString(menu);
		// 调用接口创建菜单

		try {
			String rs = HttpUtils.sendPostBuffer(url, jsonMenu);
			if (null != rs) {
				JSONObject jsonObject = JSONObject.parseObject(rs);
				if (0 != jsonObject.getInteger("errcode")) {
					result = jsonObject.getInteger("errcode");
					log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInteger("errcode"),jsonObject.getString("errmsg"));
				}
			}
		} catch (IOException e) {
			System.out.println("请求错误！");
			e.printStackTrace();
		}

		return result;
	}
	
}
