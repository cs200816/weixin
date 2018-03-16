package com.cs.web;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cs.dispatcher.EventDispatcher;
import com.cs.dispatcher.MsgDispatcher;
import com.cs.util.MessageUtil;
import com.cs.util.SignUtil;

@Controller
@RequestMapping("/weChat")
public class SecurityController extends BaseController {

	/**
	 * 
	 * @Description: 用于接收 get 参数，返回验证参数
	 * @param @param
	 *            request
	 * @param @param
	 *            response
	 * @param @param
	 *            signature
	 * @param @param
	 *            timestamp
	 * @param @param
	 *            nonce
	 * @param @param
	 *            echostr
	 * @author cs
	 * @date 2016 年 3 月 4 日 下午 6:20:00
	 */
	@RequestMapping(value = "/security", method = RequestMethod.GET)
	public void doGet(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "signature", required = true) String signature,
			@RequestParam(value = "timestamp", required = true) String timestamp,
			@RequestParam(value = "nonce", required = true) String nonce,
			@RequestParam(value = "echostr", required = true) String echostr) {
		try {
			if (SignUtil.checkSignature(signature, timestamp, nonce)) {
				PrintWriter out = response.getWriter();
				out.print(echostr);
				out.close();
			} else {
				logger.info("这里存在非法请求！");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 接收微信端消息处理并做分发
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/security", method = RequestMethod.POST)
	public void DoPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			Map<String, String> map = MessageUtil.parseXml(request);
			String msgtype = map.get("MsgType");
			String respXML = "";
			if (MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgtype)) {
				respXML = EventDispatcher.processEvent(map); // 进入事件处理
			} else {
				respXML = MsgDispatcher.processMessage(map); // 进入消息处理
			}
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(respXML);
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@RequestMapping(value = "/test")
	// post 方法用于接收微信服务端消息
	public String test() {
		System.out.println("这是 test 方法！");
		return "index";
	}
}
