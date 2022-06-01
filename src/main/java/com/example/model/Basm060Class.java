package com.example.model;

import java.util.Date;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.example.converter.StringConverter;

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
		
		@Convert(converter = StringConverter.class)
		private String cust_name;
		
		private String cust_tel;
		
		private String cust_fax;
		
		@Convert(converter = StringConverter.class)
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
		
		@Convert(converter = StringConverter.class)
		private String remk;
		
		private String old_custno;
		
		@Convert(converter = StringConverter.class)
		private String create_id;
		
		private Date create_date;
		
		@Convert(converter = StringConverter.class)
		private String update_id;
		
		private Date update_date;
	}
