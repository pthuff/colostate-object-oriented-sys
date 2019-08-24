/*
 * CIS611						Spring Session 2019						Paul Huff
 *
 * 								Program Assignment PA08
 * 
 * 								Message class for creating message objects 
 * 
 * 								Date Created: 4/11/2019
 * 
 * 								Saved in: Message.java
 */
package PA08;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//add the class template

import java.io.Serializable;

public class Message implements Serializable {
	
	private int id;
	private String lastName, firstFName;
	private char mi;
	private String address, city, state; 	
	private String mPhoneNo, hPhoneNo;
	private String mPhoneCarrier, hPhoneCarrier;
	private int opType;
	
	ObjectInputStream input;
	ObjectOutputStream output;
	
	
	public Message(int id, String lastName, String firstFName, char mi, String address, String city, String state,
			String mPhoneNo, String hPhoneNo, String mPhoneCarrier, String hPhoneCarrier, int opType) {
				
		this.id = id;
		this.lastName = lastName;
		this.firstFName = firstFName;
		this.mi = mi;
		this.address = address;
		this.city = city;
		this.state = state;
		this.mPhoneNo = mPhoneNo;
		this.hPhoneNo = hPhoneNo;
		this.mPhoneCarrier = mPhoneCarrier;
		this.hPhoneCarrier = hPhoneCarrier;
		this.opType = opType;
	}

	// provide the getter and setter methods
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getFirstFName() {
		return firstFName;
	}


	public void setFirstFName(String firstFName) {
		this.firstFName = firstFName;
	}


	public char getMi() {
		return mi;
	}


	public void setMi(char mi) {
		this.mi = mi;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getmPhoneNo() {
		return mPhoneNo;
	}


	public void setmPhoneNo(String mPhoneNo) {
		this.mPhoneNo = mPhoneNo;
	}


	public String gethPhoneNo() {
		return hPhoneNo;
	}


	public void sethPhoneNo(String hPhoneNo) {
		this.hPhoneNo = hPhoneNo;
	}


	public String getmPhoneCarrier() {
		return mPhoneCarrier;
	}


	public void setmPhoneCarrier(String mPhoneCarrier) {
		this.mPhoneCarrier = mPhoneCarrier;
	}


	public String gethPhoneCarrier() {
		return hPhoneCarrier;
	}


	public void sethPhoneCarrier(String hPhoneCarrier) {
		this.hPhoneCarrier = hPhoneCarrier;
	}

	public int getOpType() {
		return opType;
	}

	public void setOpType(int opType) {
		this.opType = opType;
	}		
}
