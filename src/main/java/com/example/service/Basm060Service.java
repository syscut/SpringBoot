package com.example.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Basm060;
import com.example.model.Basm060Class;

@Service
@Transactional
public class Basm060Service {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	
	@Autowired
	private EntityManager em;
	
//	@Autowired 
//	JdbcTemplate jdbcTemplate;
	
	public Map<String, Object> update(Basm060Class basm060Class) {
		Map<String, Object> result = new HashMap<String,Object>();
		String param_left = "(";
		String param_right = "(";
		String[] params = basm060Class.toString().replaceAll("Basm060Class\\((.*)\\)", "$1").replaceAll(" ", "").split(",");
		
		for (String val : params) {
			String[] col = val.split("=");
			if(col.length==2 && !col[1].matches("null") && !col[0].matches("cust_no")) {
				param_left += col[0]+",";
				param_right += "\""+col[1]+"\",";
			}else if(col.length==1&& !col[0].matches("cust_no")&&!col[0].matches("update_date")) {
				param_left += col[0]+",";
				param_right += "\"\",";
			}
		}
		
		param_left+="update_date)";
		param_right+="\""+LocalDate.now().format(formatter)+"\")";
System.out.print("update basm060 set "+ param_left +" = "+ param_right +" where cust_no = "+basm060Class.getCust_no());
//		int update_num =  em.createNativeQuery("update basm060 set "+ param_left +" = "+ param_right +" where cust_no = "+basm060Class.getCust_no())
//		.executeUpdate();
		
		result.put("status", true);
		result.put("message", "筆資料更新成功");
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Basm060> search(Basm060Class basm060Class){
		String param = "";
		Integer cust_no = null;
		Integer main_custno = null;
		System.out.println(basm060Class.toString().replaceAll("Basm060Class\\((.*)\\)", "$1").replaceAll(" ", ""));
		for (String val : basm060Class.toString().replaceAll("Basm060Class\\((.*)\\)", "$1").replaceAll(" ", "").split(",")) {
			String[] col = val.split("=");
			if(col.length==2 && !col[1].matches("null")) {
				//System.out.println("col[0]:"+col[0]+"col[1]:"+col[1]);
				if(col[0].matches("cust_no")) {
					
					if(col[1].matches("^[<>=]\\d+")) {
						param += " and cust_no "+col[1].substring(0,1)+" :cust_no";
						cust_no = Integer.valueOf(col[1].substring(1));
					}else if(col[1].matches("^(<=|>=)\\d+")){
						param += " and cust_no "+col[1].substring(0,2)+" :cust_no";
						cust_no = Integer.valueOf(col[1].substring(2));
					}else{
						param += " and cust_no = :cust_no";
						cust_no = Integer.valueOf(col[1]);
					}
				}else if(col[0].matches("main_custno")) {
					
					if(col[1].matches("^[<>=]\\d+")) {
						param += " and main_custno "+col[1].substring(0,1)+" :main_custno";
						main_custno = Integer.valueOf(col[1].substring(1));
					}else if(col[1].matches("^(<=|>=)\\d+")){
						param += " and main_custno "+col[1].substring(0,2)+" :main_custno";
						main_custno = Integer.valueOf(col[1].substring(2));
					}else{
						param += " and main_custno = :main_custno";
						main_custno = Integer.valueOf(col[1]);
					}
				}else if(col[0].matches("zip_code||zip_area")) {
					param += " and b."+ col[0] +" = '"+col[1]+"'";
				}else if(col[0].matches("create_id||create_date||update_id||update_date")) {
					param += " and a."+ col[0] +" = '"+col[1]+"'";
				} else {
					param += " and "+ col[0] +" = '"+col[1]+"'";
				}
			}
		}
		//System.out.println(param);
		
		Query query = em.createNativeQuery("select a.*, b.zip_area "
                						  +"from basm060 a,gfcdb@tauyan\\:basm020 b "
                						  +"where a.zip_code = b.zip_code "
                						  +param,Basm060.class);
		
		if(cust_no!=null) {
			//System.out.println("cust_no:"+cust_no);
			query.setParameter("cust_no", cust_no);
		}
		if(main_custno!=null) {
			//System.out.println("main_custno:"+main_custno);
			query.setParameter("main_custno", main_custno);
		}
		
		List<Basm060> content;
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
			
//			Big5_GFC big5_GFC = new Big5_GFC();
//			big5_GFC.newDecoder();
//			big5_GFC.newEncoder();
//			ByteBuffer buffer = ByteBuffer.wrap(content.get(0).getCust_name().getBytes());
//System.out.println(big5_GFC.decode(buffer));
////content.get(0).setCust_name(big5_GFC.decode(buffer).toString());
//		System.out.println(content.get(0).getCust_name());
		return content;
	}
}
