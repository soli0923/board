package kr.co.service;

import java.util.List;

import kr.co.domain.MemberVO;

public interface MemberService {
	void insert(MemberVO vo);

	MemberVO idcheck(String id);

	List<MemberVO> list();

	MemberVO read(String id);

	MemberVO updateUI(String id);

	void update(MemberVO vo);

	void del(String id);
}
