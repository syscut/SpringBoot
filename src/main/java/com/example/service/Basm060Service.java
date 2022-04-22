package com.example.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Basm060Class;

@Service
@Transactional
public class Basm060Service {
	
	@Autowired
	private EntityManager em;
	@SuppressWarnings("unchecked")
	public List<Basm060Class> search(Integer cust_no){
		List<Basm060Class> content = 
				em.createNativeQuery("select * "
				                   +"from basm060 "
						           +"where cust_no = ?",Basm060Class.class)
				.setParameter(1, cust_no).getResultList();
		return content;
	}
}
