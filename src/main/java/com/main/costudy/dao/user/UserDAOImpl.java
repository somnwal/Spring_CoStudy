package com.main.costudy.dao.user;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.main.costudy.domain.user.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {

	private EntityManager entityManager;
	
	@Autowired
	public UserDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<UserVO> getUsers() {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<UserVO> query = session.createQuery("from UserVO", UserVO.class);
		
		List<UserVO> users = query.getResultList();
		
		return users;
	}

	@Override
	public void saveUser(UserVO user) {
		Session session = entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(user);
	}

	@Override
	public UserVO getUser(int id) {
		Session session = entityManager.unwrap(Session.class);
		
		UserVO user = session.get(UserVO.class, id);
		
		return user;
	}

	@Override
	public void deleteUser(int id) {
		Session session = entityManager.unwrap(Session.class);
		
		Query query = session.createQuery(
				"delete "
				+ "from UserVO "
				+ "where id=:id");
		
		query.setParameter("id", id);
		
		query.executeUpdate();
		
	}

	
}
