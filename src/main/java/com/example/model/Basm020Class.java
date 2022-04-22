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
public class Basm020Class {
	
	@Id
	private String zipCode;

	
	private String zipArea;

	
	private String mailZipcode;
}
