package stomp.co.kr.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import stomp.co.kr.dao.StompDAO;
import stomp.co.kr.domain.StompUser;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private StompDAO dao;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value = "/login" , method = RequestMethod.POST)
	public String login(StompUser user, HttpSession session) {
		
		StompUser getUser = dao.selectUser(user.getStomp_id());
		
		if(getUser == null || !getUser.getStomp_pw().equals(user.getStomp_pw())){
			return "redirect:/";
		}
		
		session.setAttribute("loginId", getUser.getStomp_id());
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/basicChatRoom", method = RequestMethod.GET)
	public String basicChatRoom() {
		return "websocket/basicChatRoom";
	}
	
	@RequestMapping(value = "/multiChatRoom", method = RequestMethod.GET)
	public String multiChatRoom(int roomNum, Model model) {
		model.addAttribute("roomNum",roomNum);
		return "websocket/multiChatRoom";
	}
	
}
