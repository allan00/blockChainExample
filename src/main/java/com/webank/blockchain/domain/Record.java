package com.webank.blockchain.domain;

import java.sql.Timestamp;

public class Record {
	private int id;
	private int command;
	private double amount;
	private Timestamp time;
	private String remark;
	private String ip;

	public Record() {
	}
	public Record(int id, int command, double amount, Timestamp time, String remark, String ip) {
		this.id = id;
		this.command = command;
		this.amount = amount;
		this.time = time;
		this.remark = remark;
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", command"+"]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCommand() {
		return command;
	}

	public void setCommand(int command) {
		this.command = command;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
