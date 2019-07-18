package com.net.start.entity;

public class Companier extends BaseEntity{

	
	private static final long serialVersionUID = 1L;
	
	private String companyName;
	private String companyAddress;
	private String contact;
	private Integer telephone;
	private Integer phone;
	private String picturePath;
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Integer getTelephone() {
		return telephone;
	}
	public void setTelephone(Integer telephone) {
		this.telephone = telephone;
	}
	public Integer getPhone() {
		return phone;
	}
	public void setPhone(Integer phone) {
		this.phone = phone;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	@Override
	public String toString() {
		return "Companier [companyName=" + companyName + ", companyAddress=" + companyAddress + ", contact=" + contact
				+ ", telephone=" + telephone + ", phone=" + phone + ", picturePath=" + picturePath + "]";
	}
	
	
	
	
	
	
}
