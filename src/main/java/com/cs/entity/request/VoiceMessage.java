package com.cs.entity.request;

/**
 * 语音消息
 * 
 * @author Administrator
 *
 */
public class VoiceMessage extends BaseReqMessage {
	// 媒体 ID
	private String MediaId;
	// 语音格式
	private String Format;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}
}
