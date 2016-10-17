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
	public Friend getFriend(String user1, String user2, char status) {
		String hql = "from Friend where user1=:user1 and user2=:user2 and status=:status";
		Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("user1", user1)
				.setParameter("user2", user2).setParameter("status", status);
		List<Friend> listOfFriend = query.getResultList();
		if(listOfFriend!=null &&!listOfFriend.isEmpty())
			return listOfFriend.get(0);
		return null;
	}

	@Override
	public void sendRequest(Friend friend) {
		sessionFactory.getCurrentSession().saveOrUpdate(friend);

	}

	@Override
	public void acceptRequest(Friend friend) {
		sessionFactory.getCurrentSession().saveOrUpdate(friend);

	}

	@Override
	public void rejectRequest(Friend friend) {
		sessionFactory.getCurrentSession().delete(friend);

	}

	@Override
	public List<Friend> getFriendsN(String userId, char status) {
		String hql = "from Friend where (user1=:userId or user2=:userId) and status!=:status";
		Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("userId", userId)
				.setParameter("status", status);
		List<Friend> listOfFriends = query.getResultList();
		return listOfFriends;
		
	}

}
