package com.cs.dispatcher;

import java.util.Date;
import java.util.Map;

import com.cs.entity.response.Image;
import com.cs.entity.response.ImageMessage;
import com.cs.entity.response.TextMessage;
import com.cs.util.MessageUtil;

public class EventDispatcher {
	String media_id1 = "uagZVTTg6vPKNAcbbRGcZ4ST29npsRACqA6_Yf04_ecUcu8X0RyEMYfnOW-otIT3";
	static String media_id2 = "KKp1yTNrbxl-DINbmdqL9GWj4Lfyk7_i1cAle9YFAXzDDwQms6XgVK9YnUex_Sir";

	public static String processEvent(Map<String, String> map) {
		String openid = map.get("FromUserName"); // 用户 openid
		String mpid = map.get("ToUserName"); // 公众号原始 ID
		ImageMessage imgmsg = new ImageMessage();
		imgmsg.setToUserName(openid);
		imgmsg.setFromUserName(mpid);
		imgmsg.setCreateTime(new Date().getTime());
		imgmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_Image);
		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) { // 关注事件
			System.out.println("==============这是关注事件！");
			Image img = new Image();
//			HttpPostUploadUtil util = new HttpPostUploadUtil();
//			String filepath = "D:\\face.jpg";
//			Map<String, String> textMap = new HashMap<String, String>();
//			textMap.put("name", "testname");
//			Map<String, String> fileMap = new HashMap<String, String>();
//			fileMap.put("userfile", filepath);
//			String mediaidrs = util.formUpload(textMap, fileMap);
//			System.out.println(mediaidrs);
//			String mediaid = JSONObject.parseObject(mediaidrs).getString("media_id");
			img.setMediaId(media_id2);
			imgmsg.setImage(img);
			return MessageUtil.imageMessageToXml(imgmsg);
		}

		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) { // 取消关注事件
			System.out.println("==============这是取消关注事件！");
		}

		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SCAN)) { // 扫描二维码事件
			System.out.println("==============这是扫描二维码事件！");
		}

		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_LOCATION)) { // 位置上报事件
			System.out.println("==============这是位置上报事件！");
		}

		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_CLICK)) { // 自定义菜单点击事件
			System.out.println("==============这是自定义菜单点击事件！");
			String eventKey = map.get("EventKey");
			if ("image".equals(eventKey)) {
				System.out.println("==============eventKey====image！");
				Image img = new Image();
				img.setMediaId("uagZVTTg6vPKNAcbbRGcZ4ST29npsRACqA6_Yf04_ecUcu8X0RyEMYfnOW-otIT3");
				imgmsg.setImage(img);
				return MessageUtil.imageMessageToXml(imgmsg);
			} else if ("c1".equals(eventKey)) {
				System.out.println("==============eventKey====c1！");
				TextMessage txtmsg = new TextMessage();
				txtmsg.setToUserName(openid);
				txtmsg.setFromUserName(mpid);
				txtmsg.setCreateTime(new Date().getTime());
				txtmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
				txtmsg.setContent("点击事件");
				return MessageUtil.textMessageToXml(txtmsg);
			} else {
				System.out.println("==============eventKey====默认！");
				TextMessage txtmsg = new TextMessage();
				txtmsg.setToUserName(openid);
				txtmsg.setFromUserName(mpid);
				txtmsg.setCreateTime(new Date().getTime());
				txtmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
				txtmsg.setContent("默认回复点击事件");
				return MessageUtil.textMessageToXml(txtmsg);
			}

		}

		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_VIEW)) { // 自定义菜单
																	// View 事件
			System.out.println("==============这是自定义菜单 View 事件！");
		}

		return null;
	}
}
