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
public class Basm020 {
	
	@Id
	private String zip_code;

	
	private String zip_area;

	
	private String mail_zipcode;
}
