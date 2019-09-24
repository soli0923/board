package kr.co.persistence;

import java.util.List;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTO;

public interface BoardDAO {
	public Integer create(BoardVO vo) throws Exception;

	public List<BoardVO> listall();

	public BoardVO read(int bno);
	
	public void increaseviewcnt(int bno);

	public void del(int bno);

	public BoardVO modifyUI(int bno);

	public void modify(BoardVO vo);

	public int getAmount();

	public List<BoardVO> list(PageTO<BoardVO> to);
	
	public void addAttch(String filename, int bno);
	
	public List<String> getAttach(int bno);
	
	public void clearAttach(int bno);

	public void deleteAttach(String filename, int bno);
	
	
}
