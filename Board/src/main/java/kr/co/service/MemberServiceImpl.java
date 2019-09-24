package kr.co.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.domain.MemberVO;
import kr.co.persistence.MemberDAO;

@Service
@Transactional
public class MemberServiceImpl implements MemberService{
	
	@Inject
	private MemberDAO mdao;
	
	
	@Override
	public void insert(MemberVO vo) {
		mdao.insert(vo);
	}


	@Override
	public MemberVO idcheck(String id) {
		// TODO Auto-generated method stub
		return mdao.idcheck(id);
	}


	@Override
	public List<MemberVO> list() {
		// TODO Auto-generated method stub
		return mdao.list();
	}


	@Override
	public MemberVO read(String id) {
		// TODO Auto-generated method stub
		return mdao.read(id);
	}


	@Override
	public MemberVO updateUI(String id) {
		// TODO Auto-generated method stub
		return mdao.read(id);
	}


	@Override
	public void update(MemberVO vo) {
		// TODO Auto-generated method stub
		mdao.update(vo);
		
	}


	@Override
	public void del(String id) {
		// TODO Auto-generated method stub
		mdao.del(id);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
