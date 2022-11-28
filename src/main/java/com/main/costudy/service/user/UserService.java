package com.main.costudy.service.user;

import java.util.List;

import com.main.costudy.domain.user.UserVO;

public interface UserService {

	public List<UserVO> getUsers();
	
	public void saveUser(UserVO user);
	
	public UserVO getUser(int id);
	
	public void deleteUser(int id);
	
}
