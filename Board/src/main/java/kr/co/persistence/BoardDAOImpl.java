package kr.co.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession session;
	
	private final String NS = "kr.co.mapper.board";
	
	
	@Override
	public Integer create(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		return session.insert(NS+".create", vo);
	}


	@Override
	public List<BoardVO> listall() {
		// TODO Auto-generated method stub
		return session.selectList(NS+".listall");
	}


	@Override
	public BoardVO read(int bno) {
		// TODO Auto-generated method stub
		return session.selectOne(NS+".read", bno);
	}


	@Override
	public void increaseviewcnt(int bno) {
		// TODO Auto-generated method stub
		session.update(NS+".increaseviewcnt", bno);
	}


	@Override
	public void del(int bno) {
		// TODO Auto-generated method stub
		session.delete(NS+".del", bno);
	}


	@Override
	public BoardVO modifyUI(int bno) {
		// TODO Auto-generated method stub
		return session.selectOne(NS+".modifyUI", bno);
				
	}


	@Override
	public void modify(BoardVO vo) {
		// TODO Auto-generated method stub
		session.update(NS+".modify", vo);
	}


	@Override
	public int getAmount() {
		// TODO Auto-generated method stub
		return session.selectOne(NS+".getAmount");
	}


	@Override
	public List<BoardVO> list(PageTO<BoardVO> to) {
		// TODO Auto-generated method stub
		return session.selectList(NS+".list", to);
	}


	@Override
	public void addAttch(String filename, int bno) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("filename", filename);
		map.put("bno", bno);
		
		session.insert(NS+".addAttch", map);
	}


	@Override
	public List<String> getAttach(int bno) {
		// TODO Auto-generated method stub
		return session.selectList(NS+".getAttach", bno);
	}


	@Override
	public void clearAttach(int bno) {
		// TODO Auto-generated method stub
		session.delete(NS+".clearAttach", bno);
	}


	@Override
	public void deleteAttach(String filename, int bno) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("filename", filename);
		map.put("bno", bno);
		session.delete(NS+".deleteAttach", map);
		
	}
	
	
}
