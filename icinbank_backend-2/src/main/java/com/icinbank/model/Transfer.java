package com.icinbank.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Transfer implements Comparable<Transfer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int saccount;
	private int raccount;
	private int amount;
	private LocalDate date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	@Override
	public int compareTo(Transfer o) {
		Integer i1=this.id;
		Integer i2=o.id;
		return i2.compareTo(i1);
	}
	
	
}
