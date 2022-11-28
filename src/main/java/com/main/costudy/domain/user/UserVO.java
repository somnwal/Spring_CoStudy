package com.main.costudy.domain.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="costudy_user")
public class UserVO {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="user_id")
	private String userID;
	
	@Column(name="user_password")
	private String userPassword;
	
	@Column(name="user_nick")
	private String userNick;
	
	@Column(name="user_email")
	private String userEmail;
	
	@Column(name="user_profile")
	private String userProfile;
	
	@Column(name="user_born")
	private Date userBorn;
	
	@Column(name="user_gender")
	private String userGender;
	
	@Column(name="is_cert")
	private int isCertificated;
	
	@Column(name="user_level")
	private int userLevel;

	
	public UserVO() {
	}

	public UserVO(String userID, String userPassword, String userNick, String userEmail, String userProfile,
			Date userBorn, String userGender, int isCertificated, int userLevel) {
		this.userID = userID;
		this.userPassword = userPassword;
		this.userNick = userNick;
		this.userEmail = userEmail;
		this.userProfile = userProfile;
		this.userBorn = userBorn;
		this.userGender = userGender;
		this.isCertificated = isCertificated;
		this.userLevel = userLevel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}

	public Date getUserBorn() {
		return userBorn;
	}

	public void setUserBorn(Date userBorn) {
		this.userBorn = userBorn;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public int getIsCertificated() {
		return isCertificated;
	}

	public void setIsCertificated(int isCertificated) {
		this.isCertificated = isCertificated;
	}

	public int getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

	@Override
	public String toString() {
		return "UserVO [userID=" + userID + ", userPassword=" + userPassword + ", userNick=" + userNick + ", userEmail="
				+ userEmail + ", userProfile=" + userProfile + ", userBorn=" + userBorn + ", userGender=" + userGender
				+ ", isCertificated=" + isCertificated + ", userLevel=" + userLevel + "]";
	}
	
}