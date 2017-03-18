package com.byxs.entity;

/**
 * 
 * @author 布衣小生
 * 消息实体类
 *
 */
public class Message {
	
	//开发者微信账号
	private String ToUserName;
	//发送方账号(OpenID)
	private String FromUserName;
	//消息创建时间
	private String CreateTime;
	//text (消息种类)
	private String MsgType;
	//内容
	private String Content;
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	//消息ID 64位整型
	private String MsgId;
}
