package com.main.costudy.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.costudy.dao.user.UserDAO;
import com.main.costudy.domain.user.UserVO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	@Transactional
	public List<UserVO> getUsers() {
		return userDAO.getUsers();
	}

	@Override
	@Transactional
	public void saveUser(UserVO user) {
		userDAO.saveUser(user);
	}

	@Override
	@Transactional
	public UserVO getUser(int id) {
		return userDAO.getUser(id);
	}

	@Override
	@Transactional
	public void deleteUser(int id) {
		userDAO.deleteUser(id);
	}

}
