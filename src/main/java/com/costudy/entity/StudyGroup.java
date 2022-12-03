package com.costudy.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "study_group")
public class StudyGroup {
	
	@Id
	@Column(name = "group_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long groupID;
	
	@Column(name = "group_master")
	private String groupMaster;
	
	@Column(name = "study_name")
	private String studyName;
	
	@Column(name = "study_start")
	private String studyStart;
	
	@Column(name = "study_finish")
	private String studyFinish;
	
	@Column(name = "study_location")
	private String studyLocation;
	
	@Column(name = "group_mem_count")
	private int groupMemCount;
}
