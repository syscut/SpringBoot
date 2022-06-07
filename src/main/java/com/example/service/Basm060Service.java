package com.example.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Basm060;
import com.example.model.Basm060Class;
import com.example.repository.Basm060Repository;

@Service
@Transactional
public class Basm060Service {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	DateTimeFormatter stringFormatter = DateTimeFormatter.ofPattern("EEEMMMddHH:mm:sszzzyyyy", Locale.US);
	
	@Autowired
	private EntityManager em;
	
	@Autowired
	Basm060Repository repository;
	
	public Map<String, Object> update(Basm060 basm060) {
		Map<String, Object> result = new HashMap<String,Object>();
		basm060.setUpdate_date(LocalDate.now().format(formatter));
		repository.save(basm060);
		result.put("status", true);
		result.put("message", "");
		return result;
	}
	
	public Map<String, Object> create(Basm060 basm060) {
		Map<String, Object> result = new HashMap<String,Object>();
		basm060.setUpdate_date(LocalDate.now().format(formatter));
		basm060.setCreate_date(LocalDate.now().format(formatter));
		Integer id = repository.findMax();
		basm060.setCust_no(id+1);//DB 不是auto increment 只能自己找 max
		repository.save(basm060);
		result.put("status", true);
		result.put("message", "");
		return result;
	}
	
	public Map<String, Object> delete(Basm060 basm060){
		Map<String, Object> result = new HashMap<String,Object>();
		repository.deleteById(basm060.getCust_no());
		result.put("status", true);
		result.put("message", "");
		return result;
	}
	

	@SuppressWarnings("unchecked")
	public List<Basm060Class> search(Basm060Class basm060Class){
		List<String> include = new LinkedList<String>(Arrays.asList("*"));
		include.add("cust_no");
		Query query = em.createNativeQuery("select a.*, b.zip_area "
                						  +"from basm060 a left join basm020 b "
                						  +"on b.zip_code = a.zip_code "
                						  +"where 1 = 1 "
                						  +andSqlBuilder(basm060Class,include)
                						  +" order by cust_no",Basm060Class.class);
		
		List<Basm060Class> content;
			content = query.setMaxResults(300).getResultList();
		
//try {
//set ISOLATION_LEVEL
//https://stackoverflow.com/questions/8428235/how-to-get-current-connection-object-in-spring-jdbc
//https://www.ibm.com/docs/en/informix-servers/12.10?topic=database-informix-environment-variables-informix-jdbc-driver
//	Connection connection = jdbcTemplate.getDataSource().getConnection();
//	connection.setTransactionIsolation(IfxConnection.TRANSACTION_READ_UNCOMMITTED);
//} catch (SQLException e) {
//	e.printStackTrace();
//}
			
		return content;
	}
	
	public String andSqlBuilder(Object par , List<String> include) {
		String s = par.toString();
//		String[] includes = {"cust_no","cust_name","cust_tel"};
//		List<String> include = Arrays.asList(includes);
		StringBuilder sb = new StringBuilder();
		if(include==null) {
			include = new LinkedList<String>(Arrays.asList(""));
		}
		for(String param : s.replaceFirst(".*?\\((.*)\\)", "$1").split(", ")) {
			String key = param.split("=",2)[0];
			String value= param.split("=",2)[1];
//			System.out.println("key "+key+" value "+value);
			if(include.contains(key)||include.contains("*")) {
				if(value.matches("^(<|>)(?!=).*")) {
					sb.append(" and "+ key +" "+ value.substring(0,1) +" '"+ value.substring(1) +"'");
				}else if(value.matches("^(<=|>=).*")) {
					sb.append(" and "+ key +" "+ value.substring(0,2) +" '"+ value.substring(2)+ "'");
				}else if(value.startsWith("=")) {
					sb.append(" and ("+ key +" is null or "+ key +" = ' ')");
				}else if(value.startsWith("!=")) {
					sb.append(" and ("+ key +" is not null or "+ key +" != ' ')");
				}else if(value.matches(".*?:.*")) {
					sb.append(" and "+ key +" between '"+value.split(":")[0]+"' and '"+value.split(":")[1]+"'");
				}else if(value.matches("^\\*.*[^\\*]$")) {
					sb.append(" and "+ key +" like '%"+value.substring(1)+"'");
				}else if(value.matches("^[^\\*].*\\*$")) {
					sb.append(" and "+ key +" like '"+value.substring(0,value.length()-1)+"%'");
				}else if(value.matches("^\\*.*\\*$")) {
					sb.append(" and "+ key +" like '%"+value.substring(1,value.length()-1)+"%'");
				}else if(value.contains("?")) {
					sb.append(" and "+ key +" like '"+value.replace("?", "_")+"'");
				}else if(value.contains("|")&&value.matches("^[^|].*[^|]$")&&!value.contains("||")) {
					sb.append(" and "+ key +" in ('"+value.replace("|", "','")+"')");
				}else if(!value.isBlank()&&!value.matches("null")){
					sb.append(" and "+key+" = "+value);
				}
			}else if(!value.isBlank()&&!value.matches("null")){
				sb.append(" and "+key+" = '"+value+"'");
			}
		}
		
		return sb.toString();
	}
	
}
