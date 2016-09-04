package com.ahmad.DAO;

import java.util.List;

import com.ahmad.model.UserDetail;

public interface UserDetailDAO {

	void saveOrUpdateUserDetail(UserDetail userDetail);
	
	void deleteUserDetail(String userId);
	
	UserDetail getUserDetail(String userId);
	
	List<UserDetail> listUserDetails();
}
