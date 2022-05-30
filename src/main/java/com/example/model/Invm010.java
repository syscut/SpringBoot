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
		name="Invm010",
		classes = {@ConstructorResult(
				targetClass = Invm010.class,
				columns = {
						@ColumnResult(name = "rowid"),
						@ColumnResult(name = "item_no"),
						@ColumnResult(name = "proc_code"),
						@ColumnResult(name = "schr_code"),
						@ColumnResult(name = "sbl_no"),
						@ColumnResult(name = "item_desc"),
						@ColumnResult(name = "item_spec"),
						@ColumnResult(name = "draw_no"),
						@ColumnResult(name = "unit_measure"),
				}
				)})
public class Invm010 {
	
	@Id
	private Integer rowid;
	
	private String item_no;
	
	private String proc_code;
	
	private String schr_code;
	
	private String sbl_no;
	
	private String item_desc;
	
	private String item_spec;
	
	private String draw_no;
	
	private String unit_measure;

}
