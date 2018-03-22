package com.demo.springpoc.controller.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.demo.springpoc.controller.LoginService;
import com.demo.springpoc.controller.LoginServiceImpl;
import com.demo.springpoc.model.dao.LoginDao;
import com.demo.springpoc.model.entity.Login;
import com.demo.springpoc.view.LoginBean;

public class LoginServiceTest {

	@Mock
	private LoginDao loginDao;	
	
	@InjectMocks
	private LoginService loginService = new LoginServiceImpl();
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testDoLoginSuccess() {
		
		LoginBean loginBean = new LoginBean();
		Login login = new Login();
		
		String user = "a";
		String pass = "C";
		
		loginBean.setUsername(user);
		loginBean.setPassword(pass);
		login.setId(1);
		login.setName(user);
		login.setPassword(pass);
		
		// when(loginDao.findByUsernameAndPassword(user, pass)).thenReturn(login);
		when(loginDao.findByUsernameAndPasswork(user, pass)).thenReturn(null);
		
		boolean res = loginService.doLogin(loginBean);
		
		assertEquals("Successful Login Failed!", res, true);
		
		verify(loginDao).findByUsernameAndPassword(user, pass);
	}
	
	@Test
	public void testDoLoginFail() {
		LoginBean loginBean = new LoginBean();
		
		String user = "b";
		String pass = "b";
		
		loginBean.setUsername(user);
		loginBean.setPassword(pass);
		
		when(loginDao.findByUsernameAndPassword(user, pass)).thenReturn(null);
		
		boolean res = loginService.doLogin(loginBean);
		
		assertEquals("Unsuccessful Login Failed!", res, false);
		
		verify(loginDao).findByUsernameAndPassword(user, pass);
	}
}
