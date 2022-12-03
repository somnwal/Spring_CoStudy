package com.costudy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

import com.costudy.entity.GroupReg;
import com.costudy.entity.User;
import com.costudy.repository.GroupRegRepository;

@Service
public class GroupRegService {
	
	@Autowired
	private GroupRegRepository groupRegRepository;
	
	public List<GroupReg> findRegisteredGroupByUserID(String userID) {
		return groupRegRepository.findRegisteredGroupByUserID(userID);
	}
	
	public List<GroupReg> findRegisterWaitingGroupByUserID(String userID) {
		return groupRegRepository.findRegisterWaitingGroupByUserID(userID);
	}
	
	public List<GroupReg> findRegisteredGroupByGroupID(Long groupID) {
		return groupRegRepository.findRegisteredGroupByGroupID(groupID);
	}
	
	public List<GroupReg> findRegisterWaitingGroupByGroupID(Long groupID) {
		return groupRegRepository.findRegisterWaitingGroupByGroupID(groupID);
	}
	
	public List<GroupReg> findRegisteredGroupByUserIDAndGroupID(String userID, Long groupID) {
		return groupRegRepository.findRegisteredGroupByUserIDAndGroupID(userID, groupID);
	}
	
	public List<GroupReg> findRegisterWaitingGroupByUserIDAndGroupID(String userID, Long groupID) {
		return groupRegRepository.findRegisterWaitingGroupByUserIDAndGroupID(userID, groupID);
	}
	
	public String getRegState(String userID, Long groupID) {
		
		return groupRegRepository.getRegState(userID, groupID);
	}
	
	public Optional<GroupReg> findByUserIDAndGorupID(String userID, long groupID) {
		return groupRegRepository.findByUserIDAndGorupID(userID, groupID);
	}
	
	// 유저 가입 신청
	public void applyUser(String userID, Long groupID) {
		groupRegRepository.applyUser(userID, groupID);
	}
	
	// 유저 가입 승인
	public void acceptUser(String userID, Long groupID) {
		groupRegRepository.acceptUser(userID, groupID);
	}
	
	// 유저 가입 거절
	public void denyUser(String userID, Long groupID) {
		groupRegRepository.denyUser(userID, groupID);
	}

}
