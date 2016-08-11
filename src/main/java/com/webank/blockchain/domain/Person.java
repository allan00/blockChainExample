package com.webank.blockchain.domain;

public class Person {

	private int id;
	private String name;

	public Person(int id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}

	private String address;

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Person [address=" + address + ", id=" + id + ", name=" + name
				+ "]";
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Person() {
		// TODO Auto-generated constructor stub
	}

}
