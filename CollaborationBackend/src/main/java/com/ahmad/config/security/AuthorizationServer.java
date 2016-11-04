package com.ahmad.config.security;
//This configuration for authorization server will be helping us to manage the access tokens
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

/*@Configuration
@EnableAuthorizationServer*/
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

	//@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	
	// We will allow all the access token to be readable only
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(com.ahmad.config.ApplicationConfig.getOracleDatasource()).withClient("sampleClientId")
				.authorizedGrantTypes("implicit").scopes("read").autoApprove(true).and()
				.withClient("clientIdPassword").secret("secret")
				.authorizedGrantTypes("password", "authorization_code", "refresh_token")
				.scopes("read");

	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager);
	}
	
	 @Bean
	    public TokenStore tokenStore() {
	        return new JdbcTokenStore(com.ahmad.config.ApplicationConfig.getOracleDatasource());
	    }
}