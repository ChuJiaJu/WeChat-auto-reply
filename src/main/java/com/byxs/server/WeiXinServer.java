package com.byxs.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * @author 布衣小生
 * 链接并验证服务器
 *
 */
public class WeiXinServer extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String token="snsgj"; //使用时把这里换成自己的token
		
		 // 微信加密签名
        String signature = request.getParameter("signature");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        
        String[] str={token,timestamp,nonce};
        
        Arrays.sort(str);
        
        String bigStr=str[0]+str[1]+str[2];
        
        String digest=new SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase();
        String result="";
        if(digest.equals(signature)){
        	//获取微信发来的xml信息
        	System.out.println("请求来自微信!");
        	BufferedReader buf=new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
        	StringBuilder sb=new StringBuilder();
        	String strs=null;
        	while((strs=buf.readLine())!=null){
        		sb.append(strs);
        	}
        	String xml=sb.toString();
        	//封装回复的消息
        	result=TestXML.setXML(TestXML.getXMLText(xml));
        }else{
        	response.sendError(404);
        }
        
        try{
        	OutputStream out=response.getOutputStream();
        	//回复消息
        	out.write(result.getBytes("UTF-8"));
        	out.close();
        	out.flush();
        }catch(Exception EX){
        	EX.printStackTrace();
        }
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
