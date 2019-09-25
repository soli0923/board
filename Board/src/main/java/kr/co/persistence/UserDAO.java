package kr.co.persistence;

import kr.co.domain.LoginDTO;
import kr.co.domain.UserVO;

public interface UserDAO {

	public UserVO login(LoginDTO dto);
	
}
