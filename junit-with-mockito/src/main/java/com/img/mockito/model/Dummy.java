package com.img.mockito.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Table
@Entity
public class Dummy {
	@Id
	private int id;
	private String name;
	private int age;
	private String gender;

	public Dummy() {
	}

	public Dummy(int id, String name, int age, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

}
