package a.b.c;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample")
public class Test3Controller {
	
	@RequestMapping("/resttest4")
	public void resttest4() {
		
	}
	
	@RequestMapping("/resttest3")
	public void resttest3() {
		
	}

	
	@RequestMapping("/resttest2")
	public void resttest2() {
		
	}
	
	@RequestMapping("/hello")
	public void hello() {
		
	}
}
