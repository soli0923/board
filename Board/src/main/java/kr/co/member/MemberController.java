package kr.co.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.co.domain.MemberVO;
import kr.co.service.MemberService;

@Controller
@RequestMapping("/member")
@SessionAttributes("login")
public class MemberController {
	
	@Autowired
	private MemberService mservice;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginForm() {
		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberVO vo, Model model) {
		MemberVO dbVO=mservice.read(vo.getId());
		if(dbVO != null) {
			if(dbVO.getName().equals(vo.getName())) {
				model.addAttribute("login", vo);
				return "redirect:/member/list";
			}else {
				return "redirect:/member/login";
			}
		}else {
			return "redirect:/member/login";
		}
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String login(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		
		return "redirect:/member/login";
	}
	
	@RequestMapping("/{id}")
	public String too(@PathVariable("id") String id, Model model) {
		MemberVO vo=mservice.read(id);
		model.addAttribute("vo", vo);
		
		return "/member/read";
	}
	
	@RequestMapping(value = "/del")
	public String del(String id) {
		mservice.del(id);
		return "redirect:/member/list";
	}
	
	@RequestMapping(value = {"/update", "/UPDATE", "updare"}, method = RequestMethod.POST)
	public String update(MemberVO vo) {
		mservice.update(vo);
		return "redirect:/member/read?id="+vo.getId();
	}
	
	
	
	@RequestMapping("/update")
	public void updateUI(String id, Model model) {
		MemberVO vo= mservice.updateUI(id);
		model.addAttribute("vo", vo);
	}
	
	@RequestMapping("/read")
	public void read(String id, Model model) {
		MemberVO vo= mservice.read(id);
		model.addAttribute("vo", vo);
	}
	
	
	@RequestMapping("/list")
	public void list(Model model) {
		
		List<MemberVO> list=mservice.list();
		model.addAttribute("list", list);
		
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void insertUI() {
		
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(MemberVO vo) {
		mservice.insert(vo);
		
		return "redirect:/member/list";
	}
	
	@RequestMapping(value = "/idcheck",  method = RequestMethod.POST,produces = "application/text;charset=utf-8")
	@ResponseBody
	public String  idcheck(String id) {
		MemberVO what=mservice.idcheck(id);
		
		if(what == null) {
			return "사용가능";
		}else {
			return "사용불가";
		}
	}
	
	
	
	
	
	
	

}
