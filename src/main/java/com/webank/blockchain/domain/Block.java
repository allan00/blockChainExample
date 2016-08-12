package com.webank.blockchain.domain;

import com.webank.blockchain.domain.Record;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;



public class Block implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int prevIndex;
	private int prevHash;
	private int index;
	private int hash;
	private Timestamp generateTime;
	
	final public static int MAX_SIZE = 5;
	
	private ArrayList<Record> body = new ArrayList<Record>();
	
	public Block() {
		this(0,0);
	}

	public Block(int prevIndex, int prevHash) {
		this.prevIndex = prevIndex;
		this.index = prevIndex + 1;
		this.prevHash = prevHash;
		this.generateTime = new Timestamp(System.currentTimeMillis()); 
		this.hash = hashCode();
	}
	
	public int size() {
		return body.size();
	}
	
	public void add(Record r) {
		if(this.size() < MAX_SIZE) {
			body.add(r);
			this.hash = hashCode();
		}
	}

	@Override
	public String toString() {
		return "Block [prevIndex=" + prevIndex + ", index=" + index
				+ ", prevHash=" + prevHash + ", body=" + body + "]";
	}
	
	public String toJson() {
		String resStr = "{\"prevIndex\":" + prevIndex + ", \"prevHash\":" + prevHash
				+ ", \"index\":" + index + ", \"hash\":" + hash + ", \"generateTime\":\"" + generateTime
				+ "\", \"body\": [";
		Iterator<Record> it = body.iterator();
		while(it.hasNext()) {
			resStr = resStr + it.next() + ",";
		}
		if (this.body.size()>0) {
			resStr = resStr.substring(0,resStr.length()-1);
		}
		resStr += "]}";
		return resStr;
	}

	public int getIndex() {
		return index;
	}
	
	public int getPrevIndex() {
		return prevIndex;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Block other = (Block) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		return true;
	}
	
}
