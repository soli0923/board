package kr.co.ca;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.domain.ReplyVO;
import kr.co.persistence.ReplyDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class ReplyTest {
	@Autowired
	private ReplyDAO dao;
	
	@Test
	public void testReplyInsert() {
		ReplyVO vo = new ReplyVO(1, "댓글 junit", "홍길동");
		dao.insert(vo);
	}
}
