package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Psnm110;

public interface Psnm110Repository extends CrudRepository<Psnm110,Integer>{
	
	List<Psnm110> findByEmpNo(Integer emp_no);
	
	List<Psnm110> findByEmpNoAndUsrPaswd(Integer emp_no,String usr_paswd);

}
