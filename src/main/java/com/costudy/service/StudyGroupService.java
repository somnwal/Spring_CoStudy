package com.costudy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.costudy.entity.StudyGroup;
import com.costudy.repository.StudyGroupRepository;

@Service
public class StudyGroupService {
	
	@Autowired
	StudyGroupRepository studyGroupRepository;
	
	public Optional<StudyGroup> findById(Long id) {
		return studyGroupRepository.findById(id);
	}
	
	public List<StudyGroup> findAll() {
		return studyGroupRepository.findAll();
	}
	
	public Optional<StudyGroup> findByStudyName(String studyName) {
		return studyGroupRepository.findByStudyName(studyName);
	}
	
	public StudyGroup save(StudyGroup studyGroup) {
		
		if(!studyGroupRepository.findById(studyGroup.getGroupID()).isPresent()) {
			studyGroup.setGroupMemCount(1);
		}
		
		return studyGroupRepository.save(studyGroup);
	}
	
	public void increaseGroupMemCount(Long id) {
		studyGroupRepository.increaseGroupMemCount(id);
	}
	
	public void decreaseGroupMemCount(Long id) {
		studyGroupRepository.decreaseGroupMemCount(id);
	}
	
	public void deleteById(Long id) {
		studyGroupRepository.deleteById(id);
	}
}