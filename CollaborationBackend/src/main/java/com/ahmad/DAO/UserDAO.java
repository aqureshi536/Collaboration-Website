package com.ahmad.DAO;

import com.ahmad.model.User;

public interface UserDAO {

	void saveOrUpdateUser(User user);
	
	void deleteUser(String userId);
	
	User getUser(String userId);
	
}
