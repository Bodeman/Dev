package com.demo.springpoc.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LOGIN")
public class Login {
	@Id
	@Column(name="id")
	private int id;
	
	private String name;
	
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append("\n ======= Begin Login Object ======= ");
		sb.append("\n id:       [" + id + "]");
		sb.append("\n name:     [" + name + "]");
		sb.append("\n password: [" + password + "]");
		sb.append("\n ======= End Login Object   ======= ");
		
		return (new String(sb));
	}
}
