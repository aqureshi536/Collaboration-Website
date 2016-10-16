package com.ahmad.DAOImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ahmad.DAO.FriendDAO;
import com.ahmad.model.Friend;

@Repository("friendDAO")
@Transactional
public class FriendDAOImpl implements FriendDAO {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Friend> getFriends(String userId, char status) {
		String hql = "from Friend where user1=:userId or user2=:userId and status=:status";
		Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("userId", userId)
				.setParameter("status", status);
		List<Friend> listOfFriends = query.getResultList();
		return listOfFriends;
	}

	@Override
	public void sendRequest() {
		// TODO Auto-generated method stub

	}

	@Override
	public void acceptRequest() {
		// TODO Auto-generated method stub

	}

	@Override
	public void rejectRequest() {
		// TODO Auto-generated method stub

	}

}
