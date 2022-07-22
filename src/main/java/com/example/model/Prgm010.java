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
						@ColumnResult(name = "exec_file"),
						@ColumnResult(name = "allow_add"),
						@ColumnResult(name = "allow_update"),
						@ColumnResult(name = "allow_delete"),
						@ColumnResult(name = "allow_query"),
						@ColumnResult(name = "allow_exec"),
						@ColumnResult(name = "allow_bn1"),
						@ColumnResult(name = "allow_bn2"),
						@ColumnResult(name = "allow_bn3"),
						@ColumnResult(name = "allow_bn4")
				}
				)})
public class Prgm010 {
	
	@Id
	private String prg_no;
	
	private String prg_name;
	
	private String remark;
	
	private String exec_file;
	
	private char allow_add;
	
	private char allow_update;
	
	private char allow_delete;
	
	private char allow_query;
	
	private char allow_exec;
	
	private char allow_bn1;
	
	private char allow_bn2;
	
	private char allow_bn3;
	
	private char allow_bn4;
	
}
