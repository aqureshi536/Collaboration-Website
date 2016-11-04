package com.ahmad.security;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/* 
 * Reference->
 * https://blabadi.blogspot.in/2014/08/creating-your-own-oauth2-server-and.html
 */
@Entity
@Table(name="oauth_access_token")
public class AccessToken {
	@Id
	private String tokenId;
	private byte[] token;
	private String authenticationId;
	private String clientId;
	private String username;
	private byte[] authentication;
	private String refreshToken;

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public byte[] getToken() {
		return token;
	}

	public void setToken(byte[] token) {
		this.token = token;
	}

	public String getAuthenticationId() {
		return authenticationId;
	}

	public void setAuthenticationId(String authenticationId) {
		this.authenticationId = authenticationId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public byte[] getAuthentication() {
		return authentication;
	}

	public void setAuthentication(byte[] authentication) {
		this.authentication = authentication;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}
