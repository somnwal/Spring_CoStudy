package com.costudy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.costudy.entity.User;
import com.costudy.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	// 가입
	public User save(User user) {
		userRepository.save(user);
		
		return user;
	}
	
	// 아이디로 유저 찾기
	public Optional<User> findById(String userId) {
		Optional<User> user = userRepository.findById(userId);
		
		return user;
	}
	
	// 닉네임으로 유저 찾기
	public Optional<User> findByUserNick(String userNick) {
		
		List<User> users = userRepository.findByUserNick(userNick);
		
		User tmp = null;
		
		if(users != null && users.size() > 0) {
			tmp = users.get(0);
		}
		
		Optional<User> user = Optional.ofNullable(tmp);
		
		return user;
	}
	
	// 유저 삭제
	public void deleteById(String id) {
		userRepository.deleteById(id);
	}

	// 유저 수정
	public void updateById(String id, User user) {
		Optional<User> e = userRepository.findById(id);
		
		if(e.isPresent()) {
			e.get().setUserID(user.getUserID());
			e.get().setUserPassword(user.getUserPassword());
			e.get().setUserNick(user.getUserNick());
			e.get().setUserEmail(user.getUserEmail());
			e.get().setUserBorn(user.getUserBorn());
		}
	}
}
