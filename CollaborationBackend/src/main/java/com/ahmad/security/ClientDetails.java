package com.ahmad.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oauth_client_details")
public class ClientDetails {
	@Id
	@Column(name = "client_id")
	private String clientId;
	@Column(name = "resource_ids")
	private String resourceId;
	@Column(name = "client_secret")
	private String clientSecret;
	private String scope;
	@Column(name = "authorized_grant_types")
	private String authorizedGrantTypes;
	@Column(name = "web_server_redirect_uri")
	private String webServerRedirectUri;
	@Column(name = "authorities")
	private String authorities;
	@Column(name = "access_token_validity")
	private String accessTokenValidity;
	@Column(name = "refresh_token_validity")
	private String refereshTokenValidity;
	@Column(name = "additional_information")
	private String additionalInformation;

	private String autoapprove;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}

	public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	public String getWebServerRedirectUri() {
		return webServerRedirectUri;
	}

	public void setWebServerRedirectUri(String webServerRedirectUri) {
		this.webServerRedirectUri = webServerRedirectUri;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public String getAccessTokenValidity() {
		return accessTokenValidity;
	}

	public void setAccessTokenValidity(String accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}

	public String getRefereshTokenValidity() {
		return refereshTokenValidity;
	}

	public void setRefereshTokenValidity(String refereshTokenValidity) {
		this.refereshTokenValidity = refereshTokenValidity;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public String getAutoapprove() {
		return autoapprove;
	}

	public void setAutoapprove(String autoapprove) {
		this.autoapprove = autoapprove;
	}

}
