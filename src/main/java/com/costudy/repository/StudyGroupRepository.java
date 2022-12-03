package com.costudy.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.costudy.entity.StudyGroup;

@Repository
@Transactional
public interface StudyGroupRepository extends JpaRepository<StudyGroup, Long>{
	
	@Modifying
	@Query(value = "update study_group set group_mem_count = group_mem_count + 1 where group_id = ?1", nativeQuery = true)
	public void increaseGroupMemCount(Long id);
	
	@Modifying
	@Query(value = "update study_group set group_mem_count = group_mem_count - 1 where group_id = ?1", nativeQuery = true)
	public void decreaseGroupMemCount(Long id);
	
	@Query(value = "select * from study_group where study_name = ?1", nativeQuery = true)
	public Optional<StudyGroup> findByStudyName(String studyName);
}
