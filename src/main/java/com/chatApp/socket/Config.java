package com.chatApp.socket;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.ByteArrayMessageConverter;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSocketMessageBroker
public class Config implements WebSocketMessageBrokerConfigurer{

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		
        registry.addEndpoint("/ws-server").withSockJS();   //to connect with server 

	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		
		registry.enableSimpleBroker("/topic");   //to subscribe so that we can receive message /topic/return-to
		
        registry.setApplicationDestinationPrefixes("/app");  //to send message  /app/message
        
        registry.setUserDestinationPrefix(("/user"));

	}

	@Override
	public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
		
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setObjectMapper(new ObjectMapper());

		DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
		resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);
		
		converter.setContentTypeResolver(resolver);
		messageConverters.add(new StringMessageConverter());
		messageConverters.add(new ByteArrayMessageConverter());
		messageConverters.add(converter);
		
		return false;
	}
	
	
	
	

}
