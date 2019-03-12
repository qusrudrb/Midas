package stomp.co.kr.interceptor;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import stomp.co.kr.dao.StompDAO;

@Component
public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(HandshakeInterceptor.class);

	@Autowired
	private StompDAO dao;
	
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		logger.info("***** Before Handshake *****");
		
		if(request instanceof ServletServerHttpRequest){
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession();
			attributes.put("user", dao.selectUser((String)session.getAttribute("loginId")));
		}
		
		logger.info("attr : {}" , attributes);
		
		return super.beforeHandshake(request, response, wsHandler, attributes);
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception ex) {
		logger.info("***** After Handshake *****");
		super.afterHandshake(request, response, wsHandler, ex);
	}
	
	
}
