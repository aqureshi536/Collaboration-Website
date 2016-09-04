package com.ahmad.DAOImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.ahmad.DAO.ForumDAO;
import com.ahmad.model.Forum;

public class ForumDAOImpl implements ForumDAO {
	@Autowired
	SessionFactory sessionFactory;

	public ForumDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void saveOrUpdateForum(Forum forum) {
		sessionFactory.getCurrentSession().saveOrUpdate(forum);

	}

	@Override
	public void deleteForum(String forumId) {
		Forum forumToDelete = new Forum();
		forumToDelete.setForumId(forumId);
		sessionFactory.getCurrentSession().delete(forumToDelete);
	}

	@Override
	public Forum getForum(String forumId) {
		String hql = "from Forum where forumId=:forumId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("forumId", forumId);
		List<Forum> gotForum = query.getResultList();
		if (gotForum != null && !gotForum.isEmpty())
			return gotForum.get(0);
		return null;
	}

	@Override
	public List<Forum> listForums() {
		String hql = "from Forum";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Forum> listOfForums = query.getResultList();
		return listOfForums;
	}

}
