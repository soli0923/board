package kr.co.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.domain.BoardVO;
import kr.co.domain.SPageTO;

@Repository
public class SBoardDAOImpl implements SBoardDAO{
	
	@Autowired
	private SqlSession session;
	
	private final String NS="kr.co.mapper.sboard";

	@Override
	public List<BoardVO> list(SPageTO<BoardVO> sto) {
		// TODO Auto-generated method stub
		
		
		return session.selectList(NS+".list", sto);
	}

	@Override
	public int amount(SPageTO<BoardVO> sto) {
		// TODO Auto-generated method stub
		return session.selectOne(NS+".amount", sto);
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

	
	
}
