package kr.co.ca;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MybatisTest {

	@Autowired
	private SqlSessionFactory sqlFactory;
	
	
	@Inject
	private SqlSession session;
	
	
	@Test
	public void testSqlSession() {
		System.out.println(session+"#############################");
		
	}
	
	
	@Test
	public void testSqlFacotry() {
		System.out.println(sqlFactory+":::::::::::::::::::::::::::::::");
	}
	
	@Test
	public void testOpenSession() {
		SqlSession session = null;
		try {
			session = sqlFactory.openSession();
			
			System.out.println(session+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
}
