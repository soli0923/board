package kr.co.ca;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/js1", method = RequestMethod.GET)
	public String js1() {
		
		return "js1";
	}
	
	
	@RequestMapping(value = "/js2", method = RequestMethod.GET)
	public String js2() {
		
		return "js2";
	}
	
	
	@RequestMapping(value = "/js3", method = RequestMethod.GET)
	public String js3() {
		
		return "js3";
	}
	
	
	@RequestMapping(value = "/js4", method = RequestMethod.GET)
	public String js4() {
		
		return "js4";
	}
	
	@RequestMapping(value = "/js5", method = RequestMethod.GET)
	public String js5() {
		
		return "js5";
	}
	
	@RequestMapping(value = "/js6", method = RequestMethod.GET)
	public String js6() {
		
		return "js6";
	}
	
}
