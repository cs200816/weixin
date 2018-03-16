package com.cs.dispatcher;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.cs.entity.response.Article;
import com.cs.entity.response.Image;
import com.cs.entity.response.ImageMessage;
import com.cs.entity.response.NewsMessage;
import com.cs.entity.response.TextMessage;
import com.cs.util.HttpPostUploadUtil;
import com.cs.util.MessageUtil;

public class MsgDispatcher {
	public static String processMessage(Map<String, String> map) {
		String openid = map.get("FromUserName"); // 用户 openid
		String mpid = map.get("ToUserName"); // 公众号原始 ID
		TextMessage txtmsg = new TextMessage();
		txtmsg.setToUserName(openid);
		txtmsg.setFromUserName(mpid);
		txtmsg.setCreateTime(new Date().getTime());
		txtmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		
		ImageMessage imgmsg = new ImageMessage();
		imgmsg.setToUserName(openid);
		imgmsg.setFromUserName(mpid);
		imgmsg.setCreateTime(new Date().getTime());
		imgmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_Image);
		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { // 文本消息
			System.out.println("==============这是文本消息！");
			// 普通文本消息
			if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { // 文本消息
				String content=map.get("Content");
				switch (content) {
				case "1":
					txtmsg.setContent("1");
					break;
				case "2":
					txtmsg.setContent("2");
					break;
				case "3":
					Image img = new Image();
					HttpPostUploadUtil util=new HttpPostUploadUtil();
				    String filepath="D:\\face.jpg";  
				    Map<String, String> textMap = new HashMap<String, String>();  
				    textMap.put("name", "testname");  
				    Map<String, String> fileMap = new HashMap<String, String>();  
				    fileMap.put("userfile", filepath); 
				    String mediaidrs = util.formUpload(textMap, fileMap);
				    System.out.println(mediaidrs);
				    String mediaid=JSONObject.parseObject(mediaidrs).getString("media_id");
				    img.setMediaId(mediaid);
				    imgmsg.setImage(img);
				    return MessageUtil.imageMessageToXml(imgmsg);
				default:
					txtmsg.setContent("你好，这里是你双哥的个人账号！");
					break;
				}
				
				
				return MessageUtil.textMessageToXml(txtmsg);
			}
		}
		NewsMessage newmsg = new NewsMessage();
		newmsg.setToUserName(openid);
		newmsg.setFromUserName(mpid);
		newmsg.setCreateTime(new Date().getTime());
		newmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) { // 图片消息

			System.out.println("==============这是图片消息！");
			Article article = new Article();
			article.setDescription("19岁双胞胎姐妹"); // 图文消息的描述
			article.setPicUrl("http://d.ifengimg.com/mw978_mh598/p2.ifengimg.com/a/2018_11/cca518264f4fc3c_size67_w750_h668.jpg"); // 图文消息图片地址
			article.setTitle("19岁双胞胎姐妹"); // 图文消息标题
			article.setUrl("http://news.ifeng.com/a/20180315/56746686_0.shtml?_zbs_baidu_news#p=1"); // 图文 url 链接
			List<Article> list = new ArrayList<Article>();
			list.add(article); // 这里发送的是单图文，如果需要发送多图文则在这里 list 中加入多个 Article 即可！
			newmsg.setArticleCount(list.size());
			newmsg.setArticles(list);
			return MessageUtil.newsMessageToXml(newmsg);
		}

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) { // 链接消息
			System.out.println("==============这是链接消息！");
		}

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) { // 位置消息
			System.out.println("==============这是位置消息！");
		}

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) { // 视频消息
			System.out.println("==============这是视频消息！");
		}

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) { // 语音消息
			System.out.println("==============这是语音消息！");
		}

		return null;
	}
}
