package com.ahmad.security;

import java.util.List;

public interface LoginService {

	String login(String username, String password);

	List<String> getUserRoles(String userId);

	boolean validateUserId(String userId);
}
