package com.example.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.model.Basm060;

public interface Basm060Repository extends CrudRepository<Basm060, Integer>{
	
	@Query(value = "select max(cust_no) from basm060",nativeQuery = true)
	public Integer findMax();
}
