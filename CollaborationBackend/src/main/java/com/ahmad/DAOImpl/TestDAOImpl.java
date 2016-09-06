package com.ahmad.DAOImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ahmad.DAO.TestDAO;
import com.ahmad.model.Test;
@Repository("testDAO")
public class TestDAOImpl implements TestDAO {

	@Autowired
	SessionFactory sessionFactory;

	public TestDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void saveOrUpdateTest(Test test) {
		sessionFactory.getCurrentSession().saveOrUpdate(test);

	}

	@Transactional
	public void delete(String testId) {
		Test testToDelete = new Test();
		testToDelete.setTestId(testId);
		sessionFactory.getCurrentSession().delete(testToDelete);
	}

	@Transactional
	public Test getTest(String testId) {
		String hql = "from Test where testId=:testId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("testId", testId);
		List<Test> gotTest = query.getResultList();
		if (gotTest != null && !gotTest.isEmpty())
			return gotTest.get(0);
		return null;
	}

	@Transactional
	public List<Test> listTest() {
		String hql = "from Test";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Test> listOfTests = query.getResultList();
		return listOfTests;
	}

}
