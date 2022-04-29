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
		private String cust_no;
		
		private String cust_name;
		
		private String cust_tel;
		
		private String cust_fax;

		private String inv_addr;
		
		private String zip_code;
		
		private String zip_area;
		
		private String resp_man;
		
		private String call_man;
		
		private String call_tel;
		
		private String unify_no;
		
		private String pcs_no;
		
		private String public_code;
		
		private String file_code;
		
		private String tx_code;
		
		private String main_custno;
		
		private String remk;
		
		private String old_custno;
		
		private String create_id;
		
		private String create_date;
		
		private String update_id;
		
		private String update_date;
	}
