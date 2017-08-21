package com.example.websocket;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.annotation.PostConstruct;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
* @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
* 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
*/
//@WebServlet(name = "websocket",urlPatterns="/websocket")
@ServerEndpoint("/websocket")
//@Scope("prototype")
@Component
public class WebSocketTest  {

	private static Logger logger = LoggerFactory.getLogger(WebSocketTest.class);
	
	private static volatile int onlineCount = 0;
	private static CopyOnWriteArraySet<WebSocketTest> webSocketTests = new CopyOnWriteArraySet<WebSocketTest>();
	private static ConcurrentMap<String, Session> webSocketMap = new ConcurrentHashMap<String, Session>();
	private Session session;
	
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		webSocketTests.add(this);
		webSocketMap.put(session.getId(), session);
		logger.info("有新连接加入sessionId:{}！当前在线人数为:{}", session.getId(),addOnlineCount());
	}
	
	@OnClose
	public void onClose(Session session) {
		webSocketTests.remove(this);
		webSocketMap.remove(session.getId());
		logger.info("有一连接关闭sessionId:{}！当前在线人数为:{}", session.getId(),subOnlineCount());
	}
	
	@OnMessage
	public void onMessage(String msg,Session session){
		logger.info("来自客户端sessionId:{} 的消息:{}", session.getId(),msg);
		//群发消息
		for (Session session2 : webSocketMap.values()) {
			try {
				session2.getBasicRemote().sendText(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@OnError
	public void onError(Session session,Throwable err){
		logger.info("来自客户端sessionId:{} 的异常消息:{}", session.getId(),err.getMessage());
		err.printStackTrace();
	}
	
	@PostConstruct
	public void init() {
		logger.info("init:{} {}", WebSocketTest.class,this.toString());
	}
	
	public static synchronized int getOnlineCount() {
		return onlineCount;
		}
		public static synchronized int addOnlineCount() {
		return WebSocketTest.onlineCount++;
		}
		public static synchronized int subOnlineCount() {
			return WebSocketTest.onlineCount--;
		}
}
