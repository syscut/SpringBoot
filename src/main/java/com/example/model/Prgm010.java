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
		name="Prgm010",
		classes = {@ConstructorResult(
				targetClass = Prgm010.class,
				columns = {
						@ColumnResult(name = "prg_no"),
						@ColumnResult(name = "prg_name"),
						@ColumnResult(name = "remark"),
						@ColumnResult(name = "exec_file")
				}
				)})
public class Prgm010 {
	
	@Id
	private String prg_no;
	
	private String prg_name;
	
	private String remark;
	
	private String exec_file;
	
}
