package com.example.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Prgm010;
import com.example.model.Prgm040;


@Service
@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
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
		
		List<Prgm010> prgm010 = em.createNativeQuery("select distinct a.prg_no, a.prg_name, a.remark, a.exec_file, "
											+ "b.allow_add, b.allow_update, b.allow_delete, b.allow_query, b.allow_exec, "
											+ "b.allow_bn1, b.allow_bn2, b.allow_bn3, b.allow_bn4 "
											+ "from prgm010 a,prgm020 b "
											+ "where a.menu_prg = 'Y' and "
											+ "a.prg_no = b.prg_no and "
											+ "((b.usr_group = :usr_group and b.emp_no = 0) or "
											+ "b.emp_no = :emp_no) "
											+ "order by 1","Prgm010").setParameter("emp_no", usrInf.get("empNo"))
											.setParameter("usr_group", usrInf.get("usrGroup")).getResultList();
		result.put("programs", prgm010);
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Prgm010> getPrg(Map<String, String> usrInf){
		
		List<Prgm010> prgm010 = em.createNativeQuery("select distinct a.prg_no, a.prg_name, a.remark ,a.exec_file, "
											+ "b.allow_add, b.allow_update, b.allow_delete, b.allow_query, b.allow_exec, "
											+ "b.allow_bn1, b.allow_bn2, b.allow_bn3, b.allow_bn4 "
											+ "from prgm010 a,prgm020 b "
											+ "where b.prg_no[1,3] = :sys_no and "
											+ "a.menu_prg = 'Y' and "
											+ "a.prg_no = b.prg_no and "
											+ "((b.usr_group = :usr_group and b.emp_no = 0) or "
											+ "b.emp_no = :emp_no) "
											+ "order by 1","Prgm010").setParameter("emp_no", usrInf.get("empNo"))
											.setParameter("usr_group", usrInf.get("usrGroup"))
											.setParameter("sys_no", usrInf.get("sysNo")).getResultList();
		
		return prgm010;
	}

}
