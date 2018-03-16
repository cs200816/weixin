package com.cs.entity.response;

/**
 * 音乐消息
 * 
 * @author Administrator
 *
 */
public class MusicMessage extends BaseResMessage {

	// 音乐
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
}
