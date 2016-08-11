package com.webank.blockchain.domain;

import java.sql.Date;

public class Log {
	
	private int id;
	private String ip;
	private Date date;
	private double amount;
	private String command;
	private String msg;
	
	public Log(String ip) {
		super();
		this.ip = ip;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", ip=" + ip + ", date=" + date + ", command=" + command + ", msg=" + msg + "]";
	}
}
