package com.example.model;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.example.converter.StringConverter;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class Basm060Class {
	
	@Id
	private String cust_no;

	@Convert(converter = StringConverter.class)
	private String cust_name, inv_addr, remk, create_id, update_id;

	private String cust_tel, cust_fax, zip_code, zip_area, resp_man, call_man, call_tel, unify_no, pcs_no, public_code,
			file_code, tx_code, main_custno, old_custno,create_date, update_date;

}
