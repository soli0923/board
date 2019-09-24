package kr.co.service;

import java.util.List;

import kr.co.domain.PageTO;
import kr.co.domain.ReplyVO;

public interface ReplyService {
	void insert(ReplyVO vo);

	List<ReplyVO> list(int bno);

	void update(ReplyVO vo);

	void delete(int rno);

	PageTO<ReplyVO> list(int bno, int replyPage);

}
