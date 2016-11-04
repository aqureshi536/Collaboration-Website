package com.ahmad.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;

import com.ahmad.model.Blog;
import com.ahmad.model.BlogComment;
import com.ahmad.model.Event;
import com.ahmad.model.Forum;
import com.ahmad.model.ForumPost;
import com.ahmad.model.ForumPostComment;
import com.ahmad.model.Friend;
import com.ahmad.model.JobOpportunity;
import com.ahmad.model.UserAuthorities;
import com.ahmad.model.UserDetail;
import com.ahmad.model.Users;
import com.ahmad.security.AccessToken;
import com.ahmad.security.RefreshToken;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.ahmad")
public class ApplicationConfig {

	@Bean(name = "datasource")
	public static DataSource getOracleDatasource() {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		datasource.setUrl("jdbc:oracle:thin:@localhost:1521:oracledb");
		datasource.setUsername("system");
		datasource.setPassword("258741");
		return datasource;
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
		properties.put("hibernate.hbm2ddl.auto", "update");
		return properties;
	}

	@Bean
	public SessionFactory getSessionFactory(DataSource datasource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(datasource);
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClass(UserDetail.class);
		sessionBuilder.addAnnotatedClass(Users.class);
		sessionBuilder.addAnnotatedClass(UserAuthorities.class);
		sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(BlogComment.class);
		sessionBuilder.addAnnotatedClass(Forum.class);
		sessionBuilder.addAnnotatedClass(ForumPost.class);
		sessionBuilder.addAnnotatedClass(ForumPostComment.class);
		sessionBuilder.addAnnotatedClass(Event.class);
		sessionBuilder.addAnnotatedClass(JobOpportunity.class);
		sessionBuilder.addAnnotatedClass(Friend.class);
		sessionBuilder.addAnnotatedClass(AccessToken.class);
		sessionBuilder.addAnnotatedClass(RefreshToken.class);

		return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransctionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;

	}

	@Bean
	public MultipartResolver multipartResolver() {
		org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(25000000);
		return multipartResolver;
	}

	// ========================= Security ========================
/*
	// datasource intializer for security purpose
	@Bean
	public DataSourceInitializer dataSourceInitializer() {
		DataSourceInitializer initailizer = new DataSourceInitializer();
		initailizer.setDataSource(getOracleDatasource());
		initailizer.setDatabasePopulator(databasePopulator());
		return initailizer;
	}

	@Value("classpath:schema.sql")
	private Resource schemaScript;

	private DatabasePopulator databasePopulator() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.setScripts(schemaScript);
		return null;
	}
	
	
	@Autowired
	private Environment env;
	
	@Bean
	public TokenStore tokenStore(){
		return new JdbcTokenStore(getOracleDatasource());
	}
	
	@Primary
	@Bean
	public RemoteTokenServices tokenService(){
		RemoteTokenServices tokenServices = new RemoteTokenServices();
		tokenServices.setCheckTokenEndpointUrl("http://localhost:8080/CollaborationWebsite/oauth/token");
		tokenServices.setClientId("clientIdPassword");
		tokenServices.setClientSecret("secret");
		return tokenServices;
	}
	*/

}
