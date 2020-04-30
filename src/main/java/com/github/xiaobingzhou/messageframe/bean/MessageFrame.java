package com.github.xiaobingzhou.messageframe.bean;

import com.github.xiaobingzhou.messageframe.IMessageFrame;

import java.util.LinkedHashMap;

/**
 * 报文帧格式
 * @author bell.zhouxiaobing
 *
 */
public class MessageFrame implements IMessageFrame {

	//  帧头	 协议版本号 报文序列号	地址信息	指令码	帧源     消息体	CRC校验	 帧尾
	// 1byte	2byte	2byte	12byte	2byte	1byte	0~nbyte	2byte	1byte
	private String head;// 帧头
	private String protocolVer;// 协议版本号
	private String messageSN;// 报文序列号
	private String imei;// 地址信息IMEI号
	private String commandCode;// 指令码
	private String from;// 帧源
	private String body;// 消息体
	private LinkedHashMap<String, String> bodyList;
	private String crc;// CRC校验
	private String tail;// 帧尾 
	
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getProtocolVer() {
		return protocolVer;
	}
	public void setProtocolVer(String protocolVer) {
		this.protocolVer = protocolVer;
	}
	public String getMessageSN() {
		return messageSN;
	}
	public void setMessageSN(String messageSN) {
		this.messageSN = messageSN;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getCommandCode() {
		return commandCode;
	}
	public void setCommandCode(String commandCode) {
		this.commandCode = commandCode;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getCrc() {
		return crc;
	}
	public void setCrc(String crc) {
		this.crc = crc;
	}
	public String getTail() {
		return tail;
	}
	public void setTail(String tail) {
		this.tail = tail;
	}
	@Override
	public String toString() {
		return "MessageFrame [head=" + head + ", protocolVer=" + protocolVer + ", messageSN=" + messageSN + ", imei="
				+ imei + ", commandCode=" + commandCode + ", from=" + from + ", body=" + body + ", crc=" + crc
				+ ", tail=" + tail + "]";
	}
}
