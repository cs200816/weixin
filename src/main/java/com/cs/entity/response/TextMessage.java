package com.cs.entity.response;

/**
 * 文本消息消息体
 * @author Administrator
 *
 */
public class TextMessage extends BaseResMessage {
	// 回复的消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
