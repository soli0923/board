package kr.co.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTO;
import kr.co.persistence.BoardDAO;
import kr.co.persistence.ReplyDAO;

@Service
@Transactional
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO bDao;
	
	@Autowired
	private ReplyDAO rDao;
	
	
	@Override
	public Integer create(BoardVO vo) throws Exception {
		System.out.println("before:::::::::::::::::"+vo.getBno());
		bDao.create(vo);
		System.out.println("after:::::::::::::::::"+vo.getBno());
		
		if(vo.getFiles() !=null) {
			for(String filename : vo.getFiles()) {
				bDao.addAttch(filename, vo.getBno());
			}
		}
		
		return vo.getBno();
	}


	@Override
	public List<BoardVO> listall() {
		// TODO Auto-generated method stub
		return bDao.listall();
	}


	@Override
	public BoardVO read(int bno) {
		// TODO Auto-generated method stub
		bDao.increaseviewcnt(bno);
		return bDao.read(bno);
	}


	@Override
	public void del(int bno) {
		// TODO Auto-generated method stub
		rDao.del(bno);
		bDao.clearAttach(bno);
		bDao.del(bno);
	}


	@Override
	public BoardVO modifyUI(int bno) {
		// TODO Auto-generated method stub
		return bDao.modifyUI(bno);
	}


	@Override
	public void modify(BoardVO vo) {
		// TODO Auto-generated method stub
		bDao.modify(vo);
		
		bDao.clearAttach(vo.getBno());
		if(vo.getFiles()!=null) {
			for (String filename : vo.getFiles()) {
				bDao.addAttch(filename, vo.getBno());
			}
		}
	}


	@Override
	public PageTO<BoardVO> list(PageTO<BoardVO> to) {
		// TODO Auto-generated method stub
		System.out.println("::::::::::::::::::::::list:::::");
		int amount=bDao.getAmount();
		to.setAmount(amount);
		
		List<BoardVO> list= bDao.list(to);
		to.setList(list);
		
		return to;
	}


	@Override
	public int amount() {
		// TODO Auto-generated method stub
		return bDao.getAmount();
	}


	@Override
	public List<String> getAttach(int bno) {
		// TODO Auto-generated method stub
		return bDao.getAttach(bno);
	}


	@Override
	public void deleteAttach(String filename, int bno) {
		bDao.deleteAttach(filename, bno);
		
	}

}
