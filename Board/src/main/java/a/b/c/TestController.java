package a.b.c;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.domain.MemberVO;

@Controller
public class TestController {
	
	
	@RequestMapping("/e3")
	public String e3(RedirectAttributes rttr, Model model) {
		
		rttr.addAttribute("test1", "test1");
		rttr.addFlashAttribute("test2", "test2");
		model.addAttribute("test3", "test3");
		
		return "redirect:/f";
		
		
	}
	
	@RequestMapping("/e2")
	public String e2(RedirectAttributes rttr) {
		System.out.println("e2:::::::::::::::::::::::");
		
		rttr.addAttribute("test", "test");
		rttr.addFlashAttribute("test2", "test2");
		return "redirect:/f";
	}
	
	@RequestMapping("/f")
	public void f(@ModelAttribute("test") String test,
				@ModelAttribute("test2") String test2,
				@ModelAttribute("test3") String test3) {
		System.out.println("f::::::::::::::::::::::::::::");
		System.out.println(test);
		System.out.println(test2);
		System.out.println(test3);
	}
	
	@RequestMapping("/e")
	public String e(Model model) {
		System.out.println("e::::::::::::::::::::::::::");
		model.addAttribute("test", "test");
		return "redirect:/f";
	}
	
	@RequestMapping("/d")
	public void d(Model model) {
		MemberVO vo = new MemberVO("m001", "john", 11);
		model.addAttribute("vo", vo);
		System.out.println("dddddddddddddddddddddddd");
	}
	
	@RequestMapping("/c")
	public void c(String result, Model model) {
		System.out.println("c:::::::::::::::::::::::::::::::::::");
		model.addAttribute("result", result);
	}
	
	@RequestMapping("/b")
	public void b(String msg, String age, Model model) {
		System.out.println("b:::::::::::::::::::::::::::::::::::");
		System.out.println(msg);
		System.out.println(age);
		model.addAttribute("msg",msg);
		model.addAttribute("age",age);
	}
	
	@RequestMapping("/a")
	public void a() {
		System.out.println("a::::::::::::::::::::::::::::");
	}
	
	@RequestMapping("/t1")
	public String t1() {
		System.out.println("t1():::::::::::::::::::::::::::::::");
		
		return "test1";
	}

}
