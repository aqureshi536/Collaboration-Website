package com.ahmad.DAO;



import com.ahmad.model.UserAuthorities;

public interface UserAuthoritiesDAO {

	void saveOrUpdateUserAuthority(UserAuthorities userAuthorities);
	
	void deleteUserAuthority(String email);
	
	UserAuthorities getUserAuthority(String userId);
}
