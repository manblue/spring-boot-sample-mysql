package com.example.websocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class CountWebSocketHandler extends TextWebSocketHandler {

	private static Logger logger = LoggerFactory.getLogger(CountWebSocketHandler.class);
	private static volatile long onlineCount = 0;
	private static Map<String, WebSocketSession> webSocketMap = new ConcurrentHashMap<String, WebSocketSession>();
	
	@Override
	public void handleMessage(WebSocketSession session,
			WebSocketMessage<?> message) throws Exception {
		// TODO Auto-generated method stub
		super.handleMessage(session, message);
		logger.info("new msg[handleMessage] sessionId:{} msg:{}", session.getId(),message.getPayload());
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session,
			TextMessage message) throws Exception {
		logger.info("new msg[handleTextMessage] sessionId:{} msg:{}", session.getId(),message.getPayload());
		for (WebSocketSession ws : webSocketMap.values()) {
			try {
				ws.sendMessage(message);
			} catch (Exception e) {
				webSocketMap.remove(session.getRemoteAddress().getHostString()+":"+session.getRemoteAddress().getPort());
			}
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		long num = subOnlineCount();
		logger.info("有一连接关闭sessionId:{} code:{} reason:{}！当前在线人数为:{}",session.getId(),status.getCode(),status.getReason(),num);
		webSocketMap.remove(session.getRemoteAddress().getHostString()+":"+session.getRemoteAddress().getPort());
		super.afterConnectionClosed(session, status);
	}
	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionEstablished(session);
		webSocketMap.put(session.getRemoteAddress().getHostString()+":"+session.getRemoteAddress().getPort(), session);
		long num = addOnlineCount();
		logger.info("有新连接加入sessionId:{}！当前在线人数为:{} [host:{} port:{}]", session.getId(),num,
				session.getRemoteAddress().getHostString(),session.getRemoteAddress().getPort());
	}
	
	
	
	public static synchronized long getOnlineCount() {
		return onlineCount;
	}
	public static synchronized long addOnlineCount() {
		CountWebSocketHandler.onlineCount++;
		return CountWebSocketHandler.onlineCount;
	}
	public static synchronized long subOnlineCount() {
		CountWebSocketHandler.onlineCount--;
		return CountWebSocketHandler.onlineCount;
	}
}
