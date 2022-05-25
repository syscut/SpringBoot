package com.example.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Prgm010;
import com.example.model.Prgm040;


@Service
@Transactional
public class SystemPrgService {
	
	@Autowired
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getMenu(Map<String, String> usrInf){
		Map<String, Object> result = new LinkedHashMap<>();
		List<Prgm040> prgm040 = em.createNativeQuery("select distinct b.sys_no, b.sys_name "
											+ "from prgm020 a, prgm040 b "
											+ "where b.sys_no = a.prg_no[1,3] and "
											+ "(a.emp_no = :emp_no or (a.usr_group = :usr_group and a.emp_no = 0)) "
											+ "order by 1","Prgm040").setParameter("emp_no", usrInf.get("empNo"))
											.setParameter("usr_group", usrInf.get("usrGroup")).getResultList();
		result.put("menuItem", prgm040);
		
		List<Prgm010> prgm010 = em.createNativeQuery("select prg_no, prg_name, remark from prgm010 where menu_prg = 'Y'","Prgm010").getResultList();
		result.put("programs", prgm010);
		
		return result;
	}

}
