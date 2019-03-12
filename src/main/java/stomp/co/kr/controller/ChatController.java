package stomp.co.kr.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import stomp.co.kr.domain.Message;
import stomp.co.kr.domain.StompUser;


@Controller
public class ChatController {
	
	private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
	
	// 채팅 메세지 전달
	@MessageMapping("/basicChatRoom")				// stompClient.send("/chat", ...)의 첫번째 파라미터와 동일
	@SendTo("/subscribe/basicChatRoom")				//	stompClient.subscribe("/subscribe/chat", ...)의 첫번쨰 파라미터와 동일
	public Message sendChatMessage(Message message, SimpMessageHeaderAccessor headerAccessor){
		logger.info("채팅 컨트롤러 시작");
		//인터셉터에서 등록해두었던 사용자 정보 가져오기
		StompUser userObject = (StompUser)headerAccessor.getSessionAttributes().get("user");
		
		message.setId(userObject.getStomp_id());
		message.setUsername(userObject.getStomp_nm());
		message.setChatdate(LocalDateTime.now());
		
		logger.info("채팅 컨트롤러 종료");
		return message;
	}
	
	//멀티 채팅방
	@MessageMapping("/chat/{roomNum}")				// stompClient.send("/chat", ...)의 첫번째 파라미터와 동일
	@SendTo("/subscribe/chat/{roomNum}")				//	stompClient.subscribe("/subscribe/chat", ...)의 첫번쨰 파라미터와 동일
	public Message sendChatMessage(@DestinationVariable("roomNum") String roomNum ,Message message, SimpMessageHeaderAccessor headerAccessor){
		logger.info("채팅 컨트롤러 시작");
		//인터셉터에서 등록해두었던 사용자 정보 가져오기
		StompUser userObject = (StompUser)headerAccessor.getSessionAttributes().get("user");
		
		message.setId(userObject.getStomp_id());
		message.setUsername(userObject.getStomp_nm());
		message.setChatdate(LocalDateTime.now());
		
		logger.info("채팅 컨트롤러 종료");
		return message;
	}
}
