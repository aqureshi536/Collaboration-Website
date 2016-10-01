package com.ahmad.DAO;

import com.ahmad.model.UserCheck;

public interface UserDAO {

	void saveOrUpdateUser(UserCheck user);
	
	void deleteUser(String userId);
	
	UserCheck getUser(String userId);
	
}
