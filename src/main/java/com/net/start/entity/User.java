package com.net.start.entity;



public class User extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer uid;
	private String cityFrom;
	private String cityTo;
	private String company;
	private Integer qq;
	private Integer phone;
	private String username;
	private String password;
	private String email;
	private String tele;
	private Integer isDelete;
	private String avatar;
	private String salt;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getCityFrom() {
		return cityFrom;
	}
	public void setCityFrom(String cityFrom) {
		this.cityFrom = cityFrom;
	}
	public String getCityTo() {
		return cityTo;
	}
	public void setCityTo(String cityTo) {
		this.cityTo = cityTo;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Integer getQq() {
		return qq;
	}
	public void setQq(Integer qq) {
		this.qq = qq;
	}
	public Integer getPhone() {
		return phone;
	}
	public void setPhone(Integer phone) {
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTele() {
		return tele;
	}
	public void setTele(String tele) {
		this.tele = tele;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", uid=" + uid + ", cityFrom=" + cityFrom + ", cityTo=" + cityTo + ", company="
				+ company + ", qq=" + qq + ", phone=" + phone + ", username=" + username + ", password=" + password
				+ ", email=" + email + ", tele=" + tele + ", isDelete=" + isDelete + ", avatar=" + avatar + ", salt="
				+ salt + "]";
	}
	
	
}
