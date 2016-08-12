package com.webank.blockchain.domain;

public class TB_Block {

	public int id;
	public byte[] body;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public byte[] getBody() {
		return body;
	}
	public void setBody(byte[] body) {
		this.body = body;
	}
	
}
