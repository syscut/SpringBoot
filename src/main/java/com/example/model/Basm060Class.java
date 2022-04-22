package com.example.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Basm060Class {
	
	@Id
	private Integer custNo;
	
	private String custName;
	
	private String custTel;
	
	private String custFax;

	private String invAddr;
	
	private String zipCode;
	
	private String respMan;
	
	private String callMan;
	
	private String callTel;
	
	private String unifyNo;
	
	private String pcsNo;
	
	private String publicCode;
	
	private String fileCode;
	
	private String txCode;
	
	private Integer mainCustno;
	
	private String remk;
	
	private String oldCustno;
	
	private String createId;
	
	private Date createDate;
	
	private String updateId;
	
	private Date updateDate;
	
}
