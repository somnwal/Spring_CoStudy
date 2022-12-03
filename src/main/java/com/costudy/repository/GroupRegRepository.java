package com.costudy.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.costudy.entity.GroupReg;

@Repository
@Transactional
public interface GroupRegRepository extends JpaRepository<GroupReg, Long>{
	
	// 1 : 가입 대기
	// 2 : 가입 승인
	
	@Query(value = "select * from group_reg where user_id = ?1 and reg_state = \"2\"", nativeQuery = true)
	public List<GroupReg> findRegisteredGroupByUserID(String userID);
	
	@Query(value = "select * from group_reg where user_id = ?1 and reg_state = \"1\"", nativeQuery = true)
	public List<GroupReg> findRegisterWaitingGroupByUserID(String userID);
	
	@Query(value = "select * from group_reg where group_id = ?1 and reg_state = \"2\"", nativeQuery = true)
	public List<GroupReg> findRegisteredGroupByGroupID(Long groupID);
	
	@Query(value = "select * from group_reg where group_id = ?1 and reg_state = \"1\"", nativeQuery = true)
	public List<GroupReg> findRegisterWaitingGroupByGroupID(Long groupID);
	
	@Query(value = "select * from group_reg where user_id = ?1 and group_id = ?2 and reg_state = \"2\"", nativeQuery = true)
	public List<GroupReg> findRegisteredGroupByUserIDAndGroupID(String userID, Long groupID);
	
	@Query(value = "select * from group_reg where user_id = ?1 and group_id = ?2 and reg_state = \"1\"", nativeQuery = true)
	public List<GroupReg> findRegisterWaitingGroupByUserIDAndGroupID(String userID, Long groupID);
	
	@Query(value = "select reg_state from group_reg where user_id = ?1 and group_id = ?2 limit 1", nativeQuery = true)
	public String getRegState(String userID, Long groupID);
	
	@Query(value = "select * from group_reg where user_id = ?1 and group_id = ?2 limit 1", nativeQuery = true)
	public Optional<GroupReg> findByUserIDAndGorupID(String userID, Long groupID);
	
	@Modifying
	@Query(value = "insert into group_reg (reg_id, user_id, group_id, reg_state) values (0, ?1, ?2, '1')", nativeQuery = true)
	public void applyUser(String userID, Long groupID);
	
	@Modifying
	@Query(value = "update group_reg set reg_state = '2' where user_id = ?1 and group_id = ?2", nativeQuery = true)
	public void acceptUser(String userID, Long groupID);
	
	@Modifying
	@Query(value = "delete from group_reg where user_id = ?1 and group_id = ?2", nativeQuery = true)
	public void denyUser(String userID, Long groupID);
}