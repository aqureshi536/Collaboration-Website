package com.ahmad.viewmodel;

import com.ahmad.model.UserDetail;

public class UserModel {

	private UserDetail userDetail;
	private char status;

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

}
