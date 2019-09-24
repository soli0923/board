package kr.co.service;


import kr.co.domain.BoardVO;
import kr.co.domain.SPageTO;

public interface SBoardService {
	SPageTO<BoardVO> list(SPageTO<BoardVO> sto);
	
	int amount(SPageTO<BoardVO> sto);
	
	BoardVO read(int bno);

	
	void del(int bno);

	BoardVO modifyUI(int bno);

	void modify(BoardVO vo);


}
