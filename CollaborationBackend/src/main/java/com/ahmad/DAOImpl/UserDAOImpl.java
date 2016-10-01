package com.ahmad.DAOImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ahmad.DAO.UserDAO;
import com.ahmad.model.UserCheck;

public class UserDAOImpl implements UserDAO {
	@Autowired
	SessionFactory sessionFactory;

	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void saveOrUpdateUser(UserCheck user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@Transactional
	public void deleteUser(String userId) {
		UserCheck userToDelete = new UserCheck();
		userToDelete.setUserId(userId);
		sessionFactory.getCurrentSession().delete(userToDelete);

	}

	@Transactional
	public UserCheck getUser(String userId) {
		String hql= "from User where userId=:userId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("userId",userId);
		List<UserCheck> gotUser=query.getResultList();
		if(gotUser!=null && !gotUser.isEmpty())
			return gotUser.get(0);
		return null;
	}

}
