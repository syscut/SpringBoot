package com.example.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Invm010;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
public class Invm010Service {
	
	@Autowired
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Invm010> result(Invm010 invm010){
		List<Invm010> result = em.createNativeQuery("select rowid,item_no,proc_code,schr_code,sbl_no,item_desc, "
							+ "item_spec,draw_no,unit_measure from invm010 where item_no = :item_no",Invm010.class)
							.setParameter("item_no", invm010.getItem_no()).setMaxResults(300).getResultList();
		return result;
	}

}
