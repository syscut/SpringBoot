package com.example.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Basm020;

@Service
@Transactional
public class Basm020Service {
	@Autowired
	private EntityManager em;
	@SuppressWarnings("unchecked")
	public List<Basm020> search(String zip_code,String zip_area){
		String param = "";
		
		
		if(!zip_code.isBlank()) {
			if(zip_code.matches("^[<>=]\\d+")) {
				param += " and zip_code "+zip_code.substring(0,1)+" :zip_code";
				zip_code = zip_code.substring(1);
			}else if(zip_code.matches("^(<=|>=)\\d+")){
				param += " and zip_code "+zip_code.substring(0,2)+" :zip_code";
				zip_code = zip_code.substring(2);
			}else{
				param += " and zip_code = :zip_code";
			}
		}
		if(!zip_area.isBlank()) {
			param += " and zip_area like :zip_area";
		}
		Query q = em.createNativeQuery("select * "
	          	  +"from basm020 "
	          	  +"where 1 = 1"
	          	  +param,Basm020.class);
		if(!zip_code.isBlank()) {
			q.setParameter("zip_code", zip_code);
		}
		if(!zip_area.isBlank()) {
			q.setParameter("zip_area", "%"+zip_area+"%");
		}
		List<Basm020> content = q.setMaxResults(300).getResultList();
		return content;
	}
}
