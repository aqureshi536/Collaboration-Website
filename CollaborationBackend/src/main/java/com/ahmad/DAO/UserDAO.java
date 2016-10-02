package com.ahmad.DAO;

import com.ahmad.model.Users;

public interface UserDAO {

	void saveOrUpdateUser(Users user);
	
	void deleteUser(String email);
	
	Users getUser(String userId);
	
}
