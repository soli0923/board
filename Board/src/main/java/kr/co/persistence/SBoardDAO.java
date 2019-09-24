package kr.co.persistence;

import java.util.List;

import kr.co.domain.BoardVO;
import kr.co.domain.SPageTO;

public interface SBoardDAO {
	List<BoardVO> list(SPageTO<BoardVO> sto);
	
	int amount(SPageTO<BoardVO> sto);
	
	BoardVO read(int bno);
	
	void increaseviewcnt(int bno);
	
	void del(int bno);
	
	BoardVO modifyUI(int bno);

	
	void modify(BoardVO vo);
}
