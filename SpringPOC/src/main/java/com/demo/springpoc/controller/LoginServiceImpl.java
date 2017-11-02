package com.demo.springpoc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.springpoc.model.dao.LoginDao;
import com.demo.springpoc.model.entity.Login;
import com.demo.springpoc.view.LoginBean;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private LoginDao loginDao;
	
	public boolean doLogin(LoginBean loginBean)
	{
		logger.info("enter doLogin: loginBean = [" + loginBean + "]");
		
		boolean res = false;
		
		Login qr = loginDao.findByUsernameAndPassword(loginBean.getUsername(), loginBean.getPassword());
		
		if (qr != null) 
		{
			res = true;
		}
		
		logger.info("exit doLogin: res = [" + res + "]");
		
		return res;
	}
}
