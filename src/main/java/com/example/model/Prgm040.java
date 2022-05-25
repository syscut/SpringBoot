package com.example.model;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SqlResultSetMapping(
		name="Prgm040",
		classes = {@ConstructorResult(
				targetClass = Prgm040.class,
				columns = {
						@ColumnResult(name = "sys_no"),
						@ColumnResult(name = "sys_name")
				}
				)})
public class Prgm040 {
	
	@Id
	private String sys_no;
	
	private String sys_name;
	

}
