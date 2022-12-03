package com.costudy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="group_reg")
public class GroupReg {
	
	@Id
	@Column(name="reg_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long regID;
	
	@Column(name="user_id")
	private String userID;
	
	@Column(name="group_id")
	private long groupID;
	
	@Column(name="reg_state")
	private int regState;
	
}
