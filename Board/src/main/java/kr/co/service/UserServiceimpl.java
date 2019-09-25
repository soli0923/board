package kr.co.service;
import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.domain.LoginDTO;
import kr.co.domain.UserVO;
import kr.co.persistence.UserDAO;

@Service
@Transactional
public class UserServiceimpl implements UserService{

	@Inject
	private UserDAO dao;
	
	
	@Override
	public UserVO login(LoginDTO dto) {
		// TODO Auto-generated method stub
		return dao.login(dto);
	}

	
	
}
