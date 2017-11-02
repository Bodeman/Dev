package com.demo.springpoc.model.dao;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.springpoc.model.entity.Login;

@Service
@Transactional
@Repository
public class LoginDaoImpl implements LoginDao {
	private static final Logger logger = LoggerFactory.getLogger(LoginDaoImpl.class);
	
    @Autowired
    private SessionFactory sessionFactory;
    
    private Session getCurrentSession()
    {
    	return sessionFactory.getCurrentSession();
    }
    
    public Login findByUsernameAndPassword(String user, String pass)
    {
    	logger.info("enter findByUsernameAndPassword: user = [" + user + "], pass = [" + pass + "]");
    	
    	Login res = null;
    	
    	Query query = getCurrentSession().createQuery("from Login l where l.name = :name and l.password = :pass");
    	
    	query.setParameter("name", user);
    	query.setParameter("pass", pass);
    	
    	try
    	{
    		res = (Login)query.getSingleResult();
    	}
    	catch(javax.persistence.NoResultException nre)
    	{
    		//do nothing
    	}
    	
    	logger.info("exit findByUsernameAndPassword: res = [" + res + "]");
    	
    	return res;
    }
}
