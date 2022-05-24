package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Mafm080;
import com.example.repository.Mafm080Repository;

@Service
@Transactional
public class Mafm080Service {
	
	
	@Autowired
	Mafm080Repository mafm080Repository;
	
	public Iterable<Mafm080> findAll(){
		return mafm080Repository.findAll();
	}

}
