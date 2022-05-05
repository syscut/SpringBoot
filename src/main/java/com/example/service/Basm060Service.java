package com.example.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Basm060;
import com.example.model.Basm060Class;
import com.example.repository.Basm060Repository;
import com.gfc.nio.charset.Big5_GFC;
import com.informix.jdbc.IfxConnection;

@Service
@Transactional
public class Basm060Service {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	DateTimeFormatter stringFormatter = DateTimeFormatter.ofPattern("EEEMMMddHH:mm:sszzzyyyy", Locale.US);
	
	@Autowired
	private EntityManager em;
	
	@Autowired
	Basm060Repository repository;
	
//	@Autowired 
	JdbcTemplate jdbcTemplate;
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
		basm060.setCust_no(id+1);
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
	public String search(Basm060Class basm060Class){
		String param = "";
		Integer cust_no = null;
		Integer main_custno = null;
		//System.out.println(basm060Class.toString().replaceAll("Basm060Class\\((.*)\\)", "$1").replaceAll(" ", ""));
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
				}else if(col[0].matches("create_id||update_id")) {
					param += " and a."+ col[0] +" = '"+col[1]+"'";
				}else if(col[0].matches("create_date||update_date")){
					param += " and a."+ col[0] +" = '"+LocalDate.parse(col[1],stringFormatter).format(formatter)+"'";
				} else {
					param += " and "+ col[0] +" = '"+col[1]+"'";
				}
			}
		}
		//System.out.println(param);
		
		Query query = em.createNativeQuery("select a.*, b.zip_area "
                						  +"from basm060 a,gfcdb@tauyan\\:basm020 b "
                						  +"where a.zip_code = b.zip_code "
                						  +param,Basm060Class.class);
		
		if(cust_no!=null) {
			//System.out.println("cust_no:"+cust_no);
			query.setParameter("cust_no", cust_no);
		}
		if(main_custno!=null) {
			//System.out.println("main_custno:"+main_custno);
			query.setParameter("main_custno", main_custno);
		}
		
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
			
			Charset big5_GFC = Big5_GFC.forName("X-Big5-GFC");
			Charset utf8 = Big5_GFC.forName("UTF-8");
			CharBuffer cb  = CharBuffer.wrap(content.get(0).getCust_name());
			ByteBuffer encodedData = big5_GFC.encode(cb);
			CharBuffer decodedData = big5_GFC.decode(encodedData);
			
			System.out.println(decodedData);

			
			String a = "[{\"cust_name\":\""+decodedData+"\"}]";
			return a;
		//return content;
	}
}
