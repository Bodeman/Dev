package com.demo.springpoc.model.dao;

import com.demo.springpoc.model.entity.Login;

public interface LoginDao {
	public Login findByUsernameAndPassword(String user, String pass);
}
