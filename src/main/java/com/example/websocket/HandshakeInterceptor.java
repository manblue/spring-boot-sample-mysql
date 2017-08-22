package com.example.websocket;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Component
public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {

	private static Logger logger = LoggerFactory.getLogger(HandshakeInterceptor.class);
	@Override
	public boolean beforeHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		// TODO Auto-generated method stub
        //解决The extension [x-webkit-deflate-frame] is not supported问题
        if (request.getHeaders().containsKey("Sec-WebSocket-Extensions")) {
            request.getHeaders().set("Sec-WebSocket-Extensions", "permessage-deflate");
        }
        
//		logger.info("{} before Handshake {} host:{} port:{}", HandshakeInterceptor.class,this.toString(),
//				request.getRemoteAddress().getHostString(),request.getRemoteAddress().getPort());

		return super.beforeHandshake(request, response, wsHandler, attributes);
	}
	
	@Override
	public void afterHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception ex) {
		// TODO Auto-generated method stub
		logger.info("{} After Handshake", HandshakeInterceptor.class);
		super.afterHandshake(request, response, wsHandler, ex);
	}
}
