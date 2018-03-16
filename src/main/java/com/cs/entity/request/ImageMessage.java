package com.cs.entity.request;

/**
 * 图片消息
 * 
 * @author Administrator
 *
 */
public class ImageMessage extends BaseReqMessage {
	// 图片链接
	private String PicUrl;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
}
