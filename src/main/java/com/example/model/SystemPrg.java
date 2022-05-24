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
public class SystemPrg {
	
	@Id
	private String sys_no;
	
	private String sys_name;
	
	private String prg_no;
	
	private String prg_name;
	
	private String remark;

}
