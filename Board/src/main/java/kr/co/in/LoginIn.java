package kr.co.in;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.co.domain.LoginDTO;

public class LoginIn extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("loginpost >>>>>>>>>>>>>>>>>>>>>>>> preHandle");
		HttpSession session = request.getSession(false);
		if(session != null) {
			if(session.getAttribute("login") != null) {
				session.removeAttribute("login");
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("loginpost >>>>>>>>>>>>>>>>>>>>>>>> postHandle");
		
		HttpSession session = request.getSession();
		Object obj = modelAndView.getModel().get("vo");
		if(obj != null) {
			session.removeAttribute("err_login");
			session.setAttribute("login", obj);
			response.sendRedirect("/");
		} else {
			session.setAttribute("err_login", "아이디/비밀번호가 잘못되었습니다.");
			response.sendRedirect("/user/login");
			
		}
		
	}

	
	
}
