package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Psnm110 {
	
	@Id
	@Column(name = "emp_no")
	private Integer empNo;
	
	@Column(name = "emp_name")
	private String empName;
	
	@Column(name = "usr_paswd")
	private String usrPaswd;
	
	@Column(name = "usr_group")
	private String usrGroup;

}
