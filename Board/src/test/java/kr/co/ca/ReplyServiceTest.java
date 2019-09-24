package kr.co.ca;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.domain.ReplyVO;
import kr.co.service.ReplyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})

public class ReplyServiceTest {
	@Autowired
	private ReplyService service;
	
	@Test
	public void test_service_insert() {
		ReplyVO vo = new ReplyVO(1, "service", "김구");
		service.insert(vo);
	}
}
