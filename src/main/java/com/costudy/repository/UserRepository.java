package com.costudy.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.costudy.entity.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, String>{
	
	@Query(value = "select * from user where user_nick = ?1", nativeQuery = true)
	public List<User> findByUserNick(String userNick);
	
}