package com.example.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{

//	@Bean
//	public ServerEndpointExporter serverEndpointExporter(){
//		return new ServerEndpointExporter();
//	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new CountWebSocketHandler(), "/webSocket/count")
		.addInterceptors(new HandshakeInterceptor())
		.addInterceptors(new OHandshakeInterceptor()).setAllowedOrigins("http://192.168.0.123:8088");
	}
}
