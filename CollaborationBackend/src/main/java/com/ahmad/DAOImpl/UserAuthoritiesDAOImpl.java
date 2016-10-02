package com.ahmad.DAOImpl;



import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ahmad.DAO.UserAuthoritiesDAO;
import com.ahmad.model.UserAuthorities;

@Repository("userAutoritiesDAO")
public class UserAuthoritiesDAOImpl implements UserAuthoritiesDAO {

	@Autowired
	SessionFactory sessionFactory;

	public UserAuthoritiesDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void saveOrUpdateUserAuthority(UserAuthorities userAuthorities) {
		sessionFactory.getCurrentSession().saveOrUpdate(userAuthorities);

	}

	@Transactional
	public void deleteUserAuthority(String email) {
		UserAuthorities userAuthorityToDelete = new UserAuthorities();
		userAuthorityToDelete.setEmail(email);
		sessionFactory.getCurrentSession().delete(userAuthorityToDelete);

	}

	
	@Transactional
	public UserAuthorities getUserAuthority(String userId)
	{
		String hql = "from UserAuthorities where userId=:userId";
		Query query= sessionFactory.getCurrentSession().createQuery(hql).setParameter("userId", userId);
		List<UserAuthorities> gotUserAuthority= query.getResultList();
		if(gotUserAuthority!=null&& !gotUserAuthority.isEmpty())
			return gotUserAuthority.get(0);
	return null;	
	}

}
