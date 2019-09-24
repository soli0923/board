package kr.co.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.domain.BoardVO;
import kr.co.domain.SPageTO;
import kr.co.persistence.SBoardDAO;

@Service
@Transactional
public class SBoardServiceImpl implements SBoardService{
	
	@Autowired
	private SBoardDAO sbDao;
	
	@Override
	public SPageTO<BoardVO> list(SPageTO<BoardVO> sto) {
		int amount=sbDao.amount(sto);
		sto.setAmount(amount);
		
		List<BoardVO> list= sbDao.list(sto);
		sto.setList(list);

		return sto;
	}
	
	
	@Override
	public int amount(SPageTO<BoardVO> sto) {
		// TODO Auto-generated method stub
		return sbDao.amount(sto);
	}
	
	
	
	
	

	@Override
	public BoardVO read(int bno) {
		// TODO Auto-generated method stub
		sbDao.increaseviewcnt(bno);
		return sbDao.read(bno);
	}
	
	

	@Override
	public void del(int bno) {
		// TODO Auto-generated method stub
		sbDao.del(bno);
	}
	

	@Override
	public BoardVO modifyUI(int bno) {
		// TODO Auto-generated method stub
		return sbDao.modifyUI(bno);
	}

	
	
	@Override
	public void modify(BoardVO vo) {
		// TODO Auto-generated method stub
		sbDao.modify(vo);
	}

	
}
