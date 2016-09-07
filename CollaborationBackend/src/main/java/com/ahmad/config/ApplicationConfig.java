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

import com.ahmad.model.Blog;
import com.ahmad.model.BlogComment;
import com.ahmad.model.Forum;
import com.ahmad.model.ForumPost;
import com.ahmad.model.ForumPostComment;
import com.ahmad.model.JobOpportunity;
import com.ahmad.model.Test;
import com.ahmad.model.User;
import com.ahmad.model.UserAuthorities;
import com.ahmad.model.UserDetail;

import antlr.debug.Event;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.ahmad")
public class ApplicationConfig {

	@Bean(name = "datasource")
	public DataSource getOracleDatasource() {
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
		return properties;
	}

	@Bean
	public SessionFactory getSessionFactory(DataSource datasource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(datasource);
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClass(UserDetail.class);
		sessionBuilder.addAnnotatedClass(User.class);
		sessionBuilder.addAnnotatedClass(UserAuthorities.class);
		sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(BlogComment.class);
		sessionBuilder.addAnnotatedClass(Forum.class);
		sessionBuilder.addAnnotatedClass(ForumPost.class);
		sessionBuilder.addAnnotatedClass(ForumPostComment.class);
		sessionBuilder.addAnnotatedClass(Event.class);
		sessionBuilder.addAnnotatedClass(JobOpportunity.class);
		sessionBuilder.addAnnotatedClass(Test.class);
		return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransctionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;

	}
}
