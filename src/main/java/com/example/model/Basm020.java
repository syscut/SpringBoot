package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Basm020 {
@Id
@Column(name = "zip_code")
private String zipCode;

@Column(name = "cust_area")
private String zipArea;

@Column(name = "mail_zipcode")
private String mailZipcode;

public String getZipCode() {
	return zipCode;
}

public void setZipCode(String zipCode) {
	this.zipCode = zipCode;
}

public String getZipArea() {
	return zipArea;
}

public void setZipArea(String zipArea) {
	this.zipArea = zipArea;
}

public String getMailZipcode() {
	return mailZipcode;
}

public void setMailZipcode(String mailZipcode) {
	this.mailZipcode = mailZipcode;
}

}
