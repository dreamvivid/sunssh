package com.sund.test.domain;

import java.util.Date;
import org.hibernate.annotations.Entity;
import org.hibernate.annotations.NamedQuery;
@Entity
@NamedQuery(name="test.testHbm",query="from TestHbm hbm where hbm.name = :name")
public class TestHbm {
	private Integer id;
	private String name;
	private Date date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return this.getId()+"#"+this.getName()+"#"+this.getDate();
	}

}
