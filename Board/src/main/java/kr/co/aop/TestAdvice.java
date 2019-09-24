package kr.co.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;



@Component
@Aspect
public class TestAdvice {
	
	@Around("execution(* kr.co.service.BoardService*.*(..))")
	public Object checkTime(ProceedingJoinPoint pjp) throws Throwable{
		
		long start = System.currentTimeMillis();
		
		Object obj= pjp.proceed();
		
		long end = System.currentTimeMillis();
		System.out.println("::::::::메서드 실행 시간:::::"+(end-start));
		
		return obj;
	}

	
	@After("execution(* kr.co.service.BoardService*.*(..))")
	public void endLog() {
		System.out.println("##############endLog################");
	}
	
	@Before("execution(* kr.co.service.BoardService*.*(..))")
	public void startLog(JoinPoint jp) {
		System.out.println(">>>>>>>>>>>>>>>startLog>>>>>>>>>>>>");
		System.out.println(Arrays.toString(jp.getArgs()));
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
