package com.ahmad.DAOImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ahmad.DAO.UserDAO;
import com.ahmad.model.User;

public class UserDAOImpl implements UserDAO {
	@Autowired
	SessionFactory sessionFactory;

	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void saveOrUpdateUser(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@Transactional
	public void deleteUser(String userId) {
		User userToDelete = new User();
		userToDelete.setUserId(userId);
		sessionFactory.getCurrentSession().delete(userToDelete);

	}

	@Transactional
	public User getUser(String userId) {
		String hql= "from User where userId=:userId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("userId",userId);
		List<User> gotUser=query.getResultList();
		if(gotUser!=null && !gotUser.isEmpty())
			return gotUser.get(0);
		return null;
	}

}
