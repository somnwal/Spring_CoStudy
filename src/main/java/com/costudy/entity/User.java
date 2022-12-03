package com.costudy.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="user")
public class User {
	@Id
	@Column(name="user_id")
	private String userID;

	@Column(name="user_password")
	private String userPassword;
	
	@Column(name="user_nick")
	private String userNick;

	@Column(name="user_email")
	private String userEmail;
	
	@Column(name="user_born")
	private String userBorn;
	
}