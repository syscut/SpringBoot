package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Basm060 {
	@Id
	private Integer cust_no;
	
	private String cust_name;
	
	private String cust_tel;
	
	private String cust_fax;

	private String inv_addr;
	
	private String zip_code;
	
	private String resp_man;
	
	private String call_man;
	
	private String call_tel;
	
	private String unify_no;
	
	private String pcs_no;
	
	private String public_code;
	
	private String file_code;
	
	private String tx_code;
	
	private Integer main_custno;
	
	private String remk;
	
	private String old_custno;
	
	private String create_id;
	
	private String create_date;
	
	private String update_id;
	
	private String update_date;
}
