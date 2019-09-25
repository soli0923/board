package kr.co.ca;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.domain.LoginDTO;
import kr.co.domain.UserVO;
import kr.co.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Inject
	private UserService uservice;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login(@ModelAttribute("dto") LoginDTO dto) {
	}
	
	@RequestMapping(value = "/loginpost", method = RequestMethod.POST)
	public void login(LoginDTO dto, Model model) {
		UserVO vo = uservice.login(dto);
		if(vo == null) {
			return;
		}
		model.addAttribute("vo", vo);
	}
	
	

}
