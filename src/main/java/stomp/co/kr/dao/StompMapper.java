package stomp.co.kr.dao;

import stomp.co.kr.domain.StompUser;

public interface StompMapper {
	
	public StompUser selectUser(String userId);
}
