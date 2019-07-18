package com.net.start.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Servlet implementation class HelloWeChat
 */

public class WeChat extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WeChat() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO 为了简单起见,先不对消息来源进行校验
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        String echo = request.getParameter("echostr");
        echo = new String(echo.getBytes("ISO-8859-1"),"UTF-8");
        pw.println(echo);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        String wxMsgXml = IOUtils.toString(request.getInputStream(),"utf-8");
        WeChatTextMessage textMsg = null;
        try {
            textMsg = getWeChatTextMessage(wxMsgXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuffer replyMsg = new StringBuffer();
        if(textMsg != null){
            //增加你所需要的处理逻辑，这里只是简单重复消息
            replyMsg.append("您给我的消息是：");
            replyMsg.append(textMsg.getContent());
        }
        else{
            replyMsg.append(":)不是文本的消息，我暂时看不懂");
        }
        String returnXml = getReplyTextMessage(replyMsg.toString(), textMsg.getFromUserName());
        pw.println(returnXml);
    }
    
    private WeChatTextMessage getWeChatTextMessage(String xml){
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("xml", WeChatTextMessage.class);
        xstream.aliasField("ToUserName", WeChatTextMessage.class, "toUserName");
        xstream.aliasField("FromUserName", WeChatTextMessage.class, "fromUserName");
        xstream.aliasField("CreateTime", WeChatTextMessage.class, "createTime");
        xstream.aliasField("MsgType", WeChatTextMessage.class, "messageType");
        xstream.aliasField("Content", WeChatTextMessage.class, "content");
        xstream.aliasField("MsgId", WeChatTextMessage.class, "msgId");
        WeChatTextMessage wechatTextMessage = (WeChatTextMessage)xstream.fromXML(xml); 
        return wechatTextMessage;
    }
    
    private String getReplyTextMessage(String content, String weChatUser){
        WeChatReplyTextMessage we = new WeChatReplyTextMessage();
        we.setMessageType("text");
        we.setFuncFlag("0");
        we.setCreateTime(new Long(new Date().getTime()).toString());
        we.setContent(content);
        we.setToUserName(weChatUser);
        we.setToUserName("wyy155155");//TODO 你的公众帐号微信号
        XStream xstream = new XStream(new DomDriver()); 
        xstream.alias("xml", WeChatReplyTextMessage.class);
        xstream.aliasField("ToUserName", WeChatReplyTextMessage.class, "toUserName");
        xstream.aliasField("FromUserName", WeChatReplyTextMessage.class, "fromUserName");
        xstream.aliasField("CreateTime", WeChatReplyTextMessage.class, "createTime");
        xstream.aliasField("MsgType", WeChatReplyTextMessage.class, "messageType");
        xstream.aliasField("Content", WeChatReplyTextMessage.class, "content");
        xstream.aliasField("FuncFlag", WeChatReplyTextMessage.class, "funcFlag");
        String xml =xstream.toXML(we);
        return xml;
    }

}