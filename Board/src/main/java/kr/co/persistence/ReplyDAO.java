package kr.co.persistence;

import java.util.List;

import kr.co.domain.PageTO;
import kr.co.domain.ReplyVO;

public interface ReplyDAO {
	void insert(ReplyVO vo);

	List<ReplyVO> list(int bno);

	void update(ReplyVO vo);

	void delete(int rno);

	void del(int bno);

	int getAmount(int bno);

	List<ReplyVO> list(int bno, PageTO<ReplyVO> to);



}
