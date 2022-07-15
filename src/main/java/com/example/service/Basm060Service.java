package com.example.service;

import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.annotations.QueryHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Basm060;
import com.example.repository.Basm060Repository;
import com.gfc.nio.charset.MS950StringConverter;

@Service
@Transactional
public class Basm060Service {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	SimpleDateFormat sqlDateFormat = new SimpleDateFormat("MM/dd/yyyy");
	@Autowired
	private EntityManager em;
	@Autowired
	private Basm060Repository repository;
	@Autowired
	private Environment env;
	
	MS950StringConverter m = new MS950StringConverter();
	
	public Map<String, Object> exec(String[] sqlStrings) {
		Map<String, Object> result = new LinkedHashMap<>();
		
		StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("prgp010a_sp");
		storedProcedure.registerStoredProcedureParameter("p_cr_datetime", String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("p_prg_no", String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("p_emp_no", String.class, ParameterMode.IN);
		storedProcedure.setParameter("p_cr_datetime", sqlStrings[0]);
		storedProcedure.setParameter("p_prg_no", sqlStrings[1]);
		storedProcedure.setParameter("p_emp_no", sqlStrings[2]);
		boolean status = storedProcedure.execute();
		result.put("result0",status);
		
		
		return result;
	}
	
	public Map<String, Integer> update(String[] sqlStrings) {
		Map<String, Integer> result = new LinkedHashMap<>();
		for (int sqlAmount = 0; sqlAmount < sqlStrings.length; sqlAmount++) {
			 int successNumber = em.createNativeQuery(sqlStrings[sqlAmount]).executeUpdate();
			result.put("result" + sqlAmount, successNumber);
		}
//		Map<String, Object> result = new HashMap<String, Object>();
//		basm060.setUpdate_date(LocalDate.now().format(formatter));
//		repository.save(basm060);
//		result.put("status", true);
//		result.put("message", "");
		return result;
	}

	public Map<String, Integer> create(String[] sqlStrings) {
		Map<String, Integer> result = new LinkedHashMap<>();
		for (int sqlAmount = 0; sqlAmount < sqlStrings.length; sqlAmount++) {
			 int successNumber = em.createNativeQuery(sqlStrings[sqlAmount]).executeUpdate();
			result.put("result" + sqlAmount, successNumber);
		}
//		basm060.setUpdate_date(LocalDate.now().format(formatter));
//		basm060.setCreate_date(LocalDate.now().format(formatter));
//		Integer id = repository.findMax();
//		basm060.setCust_no(id + 1);// DB 不是auto increment 只能自己找 max
//		repository.save(basm060);
//		result.put("status", true);
//		result.put("message", "");
		return result;
	}

	public Map<String, Object> delete(Basm060 basm060) {
		Map<String, Object> result = new HashMap<String, Object>();
		repository.deleteById(basm060.getCust_no());
		result.put("status", true);
		result.put("message", "");
		return result;
	}

	@SuppressWarnings("unchecked")
	public Map<String, List<Object[]>> search(String[] sqlStrings) {
		Map<String, List<Object[]>> result = new LinkedHashMap<>();
		for (int sqlAmount = 0; sqlAmount < sqlStrings.length; sqlAmount++) {
			
			List<Object[]> content = em.createNativeQuery(sqlStrings[sqlAmount]).setMaxResults(300).getResultList();
			
			for (int i = 0; i < content.size(); i++) {
				
				try {
					content.get(i).getClass();
				} catch (ClassCastException e) {
					Object[] singleResult = {content.get(i)};
					content.remove(i);
					content.add(i, singleResult);
				}
				
				for (int s = 0; s < content.get(i).length; s++) {
					Object o = content.get(i)[s];
					if(o!=null) {
						
						if (o.getClass().getTypeName() == String.class.getName() &&
							!o.toString().matches("\\w+") && 
							!o.toString().isBlank()) {
							content.get(i)[s] = m.decode(o.toString());
						}
						if (o.getClass().getTypeName() == Date.class.getName()) {
							content.get(i)[s] = sqlDateFormat.format(o);
						}
						
					}
				}
			}
			result.put("result" + sqlAmount, content);
		}

//		 System.out.println(content.get(0).get(1, String.class));
//	public List<Tuple> search(Basm060Class basm060Class) {
//		LinkedHashMap<String, String> alias = new LinkedHashMap<>(Map.of("zip_code", "a", "zip_area", "b"));
//		Query query = em.createNativeQuery(
//				"select a.*, b.zip_area " + "from basm060 a left join " + env.getProperty("local.dbName") + "basm020 b "
//						+ "on b.zip_code = a.zip_code " + "where 1 = 1 "
//						+ SqlBuilder.andSqlBuilder(basm060Class, null, alias) + " order by cust_no");
//
//		List<Tuple> content = query.setMaxResults(300).getResultList();
//try {
//set ISOLATION_LEVEL
//https://stackoverflow.com/questions/8428235/how-to-get-current-connection-object-in-spring-jdbc
//https://www.ibm.com/docs/en/informix-servers/12.10?topic=database-informix-environment-variables-informix-jdbc-driver
//	Connection connection = jdbcTemplate.getDataSource().getConnection();
//	connection.setTransactionIsolation(IfxConnection.TRANSACTION_READ_UNCOMMITTED);
//} catch (SQLException e) {
//	e.printStackTrace();
//}
		return result;
	}

}
