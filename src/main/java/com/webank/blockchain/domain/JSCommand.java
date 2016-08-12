package com.webank.blockchain.domain;

public class JSCommand {
	private int command;//操作 1捐，2题款
	private double amount;//金额
	private String remark;//备注
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "JSCommand [command=" + command + ", amount=" + amount + ", remark=" + remark + "]";
	}
}
