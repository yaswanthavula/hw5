package com.example.demo.util;
import org.omg.CORBA.PRIVATE_MEMBER;

public enum Relationships {
	
	MARRIED(1,"Married") , DIVORCED(1,"Divorced") ,SIBLINGS(2,"Siblings") ,OFFSPRING(3,"offspring");
	
	private final long relationNumber;
	private final String relationStatus;
	
	
	private Relationships(long relationNumber, String relationStatus) {
		this.relationNumber = relationNumber;
		this.relationStatus = relationStatus;
	}


	public long getRelationNumber() {
		return relationNumber;
	}


	public String getRelationStatus() {
		return relationStatus;
	}
	
	

}
