package com.icinbank.details;

public class TransferDetails {
	
	private int saccount;
	private int raccount;
	private int amount;
	private String Username;
	private String ifsc;
	
	
	
	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public int getSaccount() {
		return saccount;
	}
	
	public void setSaccount(int saccount) {
		this.saccount = saccount;
	}
	
	public int getRaccount() {
		return raccount;
	}
	
	public void setRaccount(int raccount) {
		this.raccount = raccount;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}

	
}
