package com.example.websocket;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.OriginHandshakeInterceptor;

@Component
public class OHandshakeInterceptor extends OriginHandshakeInterceptor {
	private static Logger logger = LoggerFactory.getLogger(OHandshakeInterceptor.class);
	@Override
	public boolean beforeHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		// TODO Auto-generated method stub
//		return super.beforeHandshake(request, response, wsHandler, attributes);
//		logger.info("{} before Handshake {} host:{} port:{}", OHandshakeInterceptor.class,this.toString(),
//				request.getRemoteAddress().getHostString(),request.getRemoteAddress().getPort());
		return true;
	}
}
