package com.example.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.SystemPrg;

@Service
@Transactional
public class SystemPrgService {
	
	@Autowired
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getMenu(){
		Map<String, Object> result = new LinkedHashMap<>();
		List<SystemPrg> prgm040 = em.createNativeQuery("select sys_no,sys_name from prgm040 where sys_ok = 'Y'",SystemPrg.class).getResultList();
		result.put("menuItem", prgm040);
		List<SystemPrg> prgm010 = em.createNativeQuery("select prg_no,prg_name,remark from prgm010 where menu_prg = 'Y'",SystemPrg.class).getResultList();
		result.put("programs", prgm010);
		return result;
	}

}
