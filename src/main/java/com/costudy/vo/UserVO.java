package com.costudy.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
	private String userID;

	private String userPassword;
	
	private String userPassword2;
	
	private String userNick;

	private String userEmail;
	
	private String userBorn;
}
