package com.ahmad.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.ahmad.DAO.UserDetailDAO;
import com.ahmad.model.UserDetail;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	UserDetailDAO userDetailDAO;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		System.out.println(auth.toString());
		String username = auth.getPrincipal().toString();
		String password = auth.getCredentials().toString();

		UserDetail recievedUser = userDetailDAO.checkUser(username, password);
		if (recievedUser != null) {
			String userRole = recievedUser.getUserAuthorities().getAuthority();
			List<String> roles = new ArrayList<>();
			roles.add(userRole);
			Collection<SimpleGrantedAuthority> authorities = fillUserAuthorities(roles);
			Authentication filledAuthentication = new UsernamePasswordAuthenticationToken(username, password,
					authorities);
			return filledAuthentication;

		} else {
			System.out.println("Authentication failed...... generating error");
			throw new BadCredentialsException(username + " and " + password + " are invalid ..");
		}

	}
	/**
	 * utility method to convert the user roles to a Collection<GrantedAuthority> for spring security to deal with 
	 * @param roles the list of roles as string
	 * @return a collection of SimpleGrantedAuthority that represent user roles
	 */
	private Collection<SimpleGrantedAuthority> fillUserAuthorities(List<String> userRoles) {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for(String role : userRoles){
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

	@Override
	public boolean supports(Class<?> arg0) {

		return true;
	}

}
