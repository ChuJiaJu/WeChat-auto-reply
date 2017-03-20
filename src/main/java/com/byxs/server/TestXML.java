package com.byxs.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.byxs.entity.Message;
import com.byxs.tools.Tool;


/**
 * 
 * @author 布衣小生
 * 封装xml
 *
 */
public class TestXML {
	
	/**
	 * 解析微信发来的消息XML
	 * @param Xml
	 * @return
	 */
	public static Message getXMLText(String Xml){
		Message mes=new Message();
		Map<String, String> map=new HashMap<String,String>();
		try {
			Document docu=DocumentHelper.parseText(Xml);
			Element root=docu.getRootElement();
			for(Iterator it=root.elementIterator();it.hasNext();){
				Element elment=(Element) it.next();
				map.put(elment.getName(), elment.getText());
			}
			mes.setToUserName(map.get("ToUserName"));
			mes.setFromUserName(map.get("FromUserName"));
			mes.setCreateTime(map.get("CreateTime"));
			mes.setMsgType(map.get("MsgType"));
			mes.setContent(map.get("Content"));
			mes.setMsgId(map.get("MsgId"));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("消息解析成功!"+mes.getContent());
		return mes;
	}
	
		//http://mobilecdn.kugou.com/api/v3/search/song?format=json&keyword=%E4%BC%A4%E7%97%95&page=1&pageSize=1
	/**
	 * 封装消息
	 * @param mes
	 * @return
	 */
	public static String setXML(Message mes){
		if(mes.getContent().indexOf("深夜咖啡馆")!=-1||mes.getContent().indexOf("电台")!=-1||mes.getContent().indexOf("木马八音盒")!=-1){
			return setNewsXML(mes);
		}else if(mes.getContent().indexOf("佛秀")!=-1||mes.getContent().indexOf("五蕴六毒")!=-1){
			return setNewsXMLF(mes);
		}else{
			return setTuLingXML(mes);
		}
	}
	
	
	/**
	 * 文本类型
	 * @param mes
	 * @return
	 */
	public static String setTextXML(Message mes){
		
		StringBuilder str=new StringBuilder();
		str.append("<xml><ToUserName><![CDATA[");
		str.append(mes.getFromUserName());
		str.append("]]></ToUserName>");
		str.append("<FromUserName><![CDATA[");
		str.append(mes.getToUserName());
		str.append("]]></FromUserName>");
		str.append("<CreateTime>");
		str.append(new Date().getTime());
		str.append("</CreateTime>");
		str.append("<MsgType><![CDATA[");
		str.append(mes.getMsgType());
		str.append("]]></MsgType>");
		str.append("<Content><![CDATA[");
		str.append(mes.getContent());
		str.append("]]></Content></xml>");
		return str.toString();
		
	}
	
	/**
	 * 自定义文本类型
	 * @param mes
	 * @return
	 */
	public static String setTextXMLS(Message mes,String content){
		
		StringBuilder str=new StringBuilder();
		str.append("<xml><ToUserName><![CDATA[");
		str.append(mes.getFromUserName());
		str.append("]]></ToUserName>");
		str.append("<FromUserName><![CDATA[");
		str.append(mes.getToUserName());
		str.append("]]></FromUserName>");
		str.append("<CreateTime>");
		str.append(new Date().getTime());
		str.append("</CreateTime>");
		str.append("<MsgType><![CDATA[");
		str.append(mes.getMsgType());
		str.append("]]></MsgType>");
		str.append("<Content><![CDATA[");
		str.append(content);
		str.append("]]></Content></xml>");
		return str.toString();
	}
	
	//图文类型
	public static String setNewsXML(Message mes){
		StringBuilder str=new StringBuilder();
		str.append("<xml><ToUserName><![CDATA[");
		str.append(mes.getFromUserName());
		str.append("]]></ToUserName><FromUserName><![CDATA[");
		str.append(mes.getToUserName());
		str.append("]]></FromUserName><CreateTime>");
		str.append(new Date().getTime());
		str.append("</CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount>1");
		str.append("</ArticleCount><Articles><item><Title><![CDATA[");
		str.append("木马八音盒-深夜咖啡馆");
		str.append("]]></Title> <Description><![CDATA[");
		str.append("总有一句话能温暖你颓废的思绪， 每夜一次心理对话，深夜的咖啡馆，我来与你聊聊心事，做你远方不见面的知心人。");
		str.append("]]></Description><PicUrl><![CDATA[");
		str.append("http://fdfs.xmcdn.com/group4/M07/83/57/wKgDtFRI013jZuL8AAKqDpUiShE345_mobile_large.jpg");
		str.append("]]></PicUrl><Url><![CDATA[");
		str.append("http://m.ximalaya.com/9157823/album/258810");
		str.append("]]></Url></item></Articles></xml>");
		return str.toString();
	}
	
	//图文类型
		public static String setNewsXMLF(Message mes){
			StringBuilder str=new StringBuilder();
			str.append("<xml><ToUserName><![CDATA[");
			str.append(mes.getFromUserName());
			str.append("]]></ToUserName><FromUserName><![CDATA[");
			str.append(mes.getToUserName());
			str.append("]]></FromUserName><CreateTime>");
			str.append(new Date().getTime());
			str.append("</CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount>1");
			str.append("</ArticleCount><Articles><item><Title><![CDATA[");
			str.append("佛秀-我眼中的画世界");
			str.append("]]></Title> <Description><![CDATA[");
			str.append("佛说五蕴六毒是妄,把因果都念作业障。");
			str.append("]]></Description><PicUrl><![CDATA[");
			str.append("http://s8.sinaimg.cn/mw690/004fYOo0zy78YB9o46zd7");
			str.append("]]></PicUrl><Url><![CDATA[");
			str.append("http://blog.sina.com.cn/s/blog_e87f7f280102x4o8.html");
			str.append("]]></Url></item></Articles></xml>");
			return str.toString();
		}
	
	/**
	 * 音乐类型
	 * @param mes
	 * @return
	 */
	public static String setMusicXML(Message mes){
		StringBuilder str=new StringBuilder();
		
		return str.toString();
	}
	
	/**
	 * 图灵回复
	 * @param mes
	 * @return
	 */
	public static String setTuLingXML(Message mes){
		StringBuilder str=new StringBuilder();
		try {
			Tool.init();//加载配置文件
			URL url=new URL(Tool.t.get("url").toString()+Tool.t.get("key").toString()+"&info="+new String(mes.getContent().getBytes("UTF-8")));
			HttpURLConnection con=(HttpURLConnection) url.openConnection();
			BufferedReader buf=new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String strs=null;
			String content="";
			while((strs=buf.readLine())!=null){
				content+=strs;
			}
			//接收到图灵返回的消息时,判断是不是菜谱之类的信息,如果是，就返回菜谱,如果不是就返回文本消息
			if(content.indexOf("list")!=-1){
				//判断如果是菜谱则进入if 如果是新闻就进入else
				if(content.indexOf("name")!=-1){
					String contents="";
					str.append("<xml><ToUserName><![CDATA[");
					str.append(mes.getFromUserName());
					str.append("]]></ToUserName><FromUserName><![CDATA[");
					str.append(mes.getToUserName());
					str.append("]]></FromUserName><CreateTime>");
					str.append(new Date().getTime());
					str.append("</CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount>1");
					str.append("</ArticleCount><Articles><item><Title><![CDATA[");
					contents=content.substring(content.indexOf("name")+7);
					str.append(contents.substring(0,contents.indexOf("\"")));
					contents=content.substring(content.indexOf("info")+7);
					str.append("]]></Title> <Description><![CDATA[");
					str.append(contents.substring(0,contents.indexOf("\"")));
					contents=content.substring(content.indexOf("detailurl")+12);
					str.append("]]></Description><Url><![CDATA[");
					str.append(contents.substring(0,contents.indexOf("\"")));
					str.append("]]></Url></item></Articles></xml>");
				}else{
					String contents="";
					str.append("<xml><ToUserName><![CDATA[");
					str.append(mes.getFromUserName());
					str.append("]]></ToUserName><FromUserName><![CDATA[");
					str.append(mes.getToUserName());
					str.append("]]></FromUserName><CreateTime>");
					str.append(new Date().getTime());
					str.append("</CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount>1");
					str.append("</ArticleCount><Articles><item><Title><![CDATA[");
					contents=content.substring(content.indexOf("article")+10);
					str.append(contents.substring(0,contents.indexOf("\"")));
					contents=content.substring(content.indexOf("source")+9);
					str.append("]]></Title> <Description><![CDATA[");
					str.append(contents.substring(0,contents.indexOf("\"")));
					contents=content.substring(content.indexOf("detailurl")+12);
					str.append("]]></Description><PicUrl><![CDATA[");
					contents=content.substring(content.indexOf("icon")+7);
					str.append(contents.substring(0,contents.indexOf("\"")));
					str.append("]]></PicUrl><Url><![CDATA[");
					contents=content.substring(content.indexOf("detailurl")+12);
					str.append(contents.substring(0,contents.indexOf("\"")));
					str.append("]]></Url></item></Articles></xml>");
				}
			}else{
				//如果是普通聊天消息则返回文本   反之则返回超链接文本
				if(content.indexOf("url")!=-1){
					str.append("<xml><ToUserName><![CDATA[");
					str.append(mes.getFromUserName());
					str.append("]]></ToUserName>");
					str.append("<FromUserName><![CDATA[");
					str.append(mes.getToUserName());
					str.append("]]></FromUserName>");
					str.append("<CreateTime>");
					str.append(new Date().getTime());
					str.append("</CreateTime>");
					str.append("<MsgType><![CDATA[");
					str.append(mes.getMsgType());
					str.append("]]></MsgType>");
					str.append("<Content><![CDATA[");
					String contents=content.substring(content.indexOf("text")+7);
					String text=contents.substring(0, contents.indexOf("\""));
					contents=content.substring(content.indexOf("url")+6);
					String urls=contents.substring(0,contents.indexOf("\""));
					str.append("<a href='"+urls+"'>"+text+"</a>");
					str.append("]]></Content></xml>");
				}else{
					str.append("<xml><ToUserName><![CDATA[");
					str.append(mes.getFromUserName());
					str.append("]]></ToUserName>");
					str.append("<FromUserName><![CDATA[");
					str.append(mes.getToUserName());
					str.append("]]></FromUserName>");
					str.append("<CreateTime>");
					str.append(new Date().getTime());
					str.append("</CreateTime>");
					str.append("<MsgType><![CDATA[");
					str.append(mes.getMsgType());
					str.append("]]></MsgType>");
					str.append("<Content><![CDATA[");
					String contents=content.substring(content.indexOf("text")+7);
					str.append(contents.substring(0, contents.lastIndexOf("\"")));
					str.append("]]></Content></xml>");
				}
			}
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str.toString();
	}
	
}
