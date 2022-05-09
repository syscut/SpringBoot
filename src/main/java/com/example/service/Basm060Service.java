package com.example.service;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
	public List<Basm060Class> search(Basm060Class basm060Class) throws Exception{
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
			Map<String, Charset> map = Charset.availableCharsets();
		      for (String name : map.keySet()) {
		         System.out.println(name);
		      }
			System.out.println(decode(content.get(0).getCust_name()));
		return content;
	}
	//測試自訂編碼
	public String decode(String s) throws Exception {
		char[][] b2c = new char[0][];
		b2c = new char[256][];
		b2c[250] = "\u003f\u5803\u83d3\u854b\u4f8a\u7d89\u78d8\u701e\u4e98\u7215\u548f\u73cf\u6f34\u4f03\u5d10\u73f7\u5a96\u7065\u569e\u5ef8\u5cef\u7175\u71ba\u732e\u5586\u73c9\u6630\u5f63\u9235\u5742\u6d72\u3d7e\u5ecd\ue020\u7fa3\u6e70\u6e29\u3ab1\u70f1\u664b\u7add\u52f2\u53cc\u69e1\u36ac\u4fe5\u90a8\u7866\u6822\u51c9\u512f\u4552\u7081\u8c51\u9686\u8218\u71d1\u811a\u8385\u668e\u6f44\u7460\u96a3\u8054\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\u7d8b\u70df\u9427\u706f\u68ca\u714a\u6e76\u6716\u69fa\u5b96\u935f\u51c3\u6fda\u7ff1\u9f8e\u43aa\u745d\u5a81\u5b9d\u306e\u98d2\u781c\u6ed9\u74c8\u59f8\u5ce0\u44f5\u7551\u743c\u57c4\u936e\u7907\u92ed\u5050\u793b\u7980\u799d\u79c3\u79e1\u7a06\u7a0e\u7a43\u7a50\u7ac3\u7ad2\u7ae2\u7ae7\u7af8\u7b36\u7b62\u7b6c\u7b7a\u7b7b\u7b9a\u7b9e\u7b9f\u7ba2\u7ba7\u7bf6\u7c12\u7c14\u7c4f\u7c56\u7c58\u7cab\u7cfa\u7d48\u7d4b\u7d77\u7dd4\u7dd5\u7dd6\u7de4\u7e4d\u7e5d\u7e7f\u7e89\u7e8e\u7e92\u7ed4\u7ef1\u7f12\u7f30\u7f71\u7f82\u7fae\u7fc6\u8020\u8022\u8025\u8031\u805f\u8068\u80ac".toCharArray();
		b2c[251] = "\u003f\u80b7\u519a\u5416\u5414\u34df\u4c7d\u62c3\ue0a4\ue0a5\ue0a6\u54cb\ue0a8\u55bc\u80b6\u51a7\ue0ac\ue0ad\ue0ae\u5513\u8226\ue0b1\u551e\u55de\u63b9\u6898\ue0b6\u5502\u5569\u75b4\u63fc\u9e84\u922a\ue0bd\ue0be\ue0bf\u88c7\u609e\u5625\u7145\u5af2\ue0c5\u528f\u564f\u7793\u8786\u8e2d\u81b6\u35fe\u71f6\ue0ce\u8804\u9385\ue0d1\ue0d2\u7666\ue0d4\u9c72\ue0d6\u62ce\ue0d8\u9e96\u4989\u7cd3\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\u8eda\u9eab\u64e7\ue0df\u82aa\u6767\u6d72\u7906\u79c4\u7f4e\u87ee\ue0e7\u83be\u862f\u89a5\u460f\ue0ec\u98c8\u9940\u994d\u9a0c\u9ad7\u9e98\u9f26\ue0f4\u4f03\ue0f6\u4f39\u4f32\u4fab\u5056\ue0fb\u502e\u50a6\u50d9\u510e\u50ed\u5101\u51f4\u521f\u5260\u5257\u529a\u52d1\u52e0\u52e1\u51d3\u51df\u7546\u51fe\u5332\u532c\u5333\u53ab\u53aa\u9f8e\u5469\u549c\u53da\u6aca\u5573\ue119\u35d6\ue11b\u5611\u564d\ue11e\u5654\u35f3\ue121\u5746\u577a\u5767\u583a\u57e6\u57dd\u5840\ue129\u5826\u58dc\u59b7\u59d9\u5c20\ue12f\u5cd1\u5ce9\u5d46\u5d8e\ue134\u5df5\u5e0b\u5e12\u5e2e\u5e5e".toCharArray();
		b2c[252] = "\u003f\ue13a\u5f4d\u7a7d\u5bc3\u7abb\ue13f\ue140\u5fa4\u5fba\u5fbc\ue144\u6077\u60a4\u60b3\u60fd\u6130\ue14a\u619c\u6159\u617d\u615c\u637f\u61e2\u70d5\u6239\u6268\u6285\u6282\u62a6\u62d5\u6364\u6335\u6490\u3a5c\u6648\u6667\u671e\u6803\u67ac\u67f9\u68b6\u692c\u6901\u6900\u68ca\u6973\u6980\u693e\ue16a\u69b2\u69c0\u698a\u69fa\u69e9\u6ab2\u6a52\u6a9d\u6b1d\u6b52\u6bfa\u3cd1\u6cce\u6e4f\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\u6db9\u6f44\ue17b\u6fda\u7081\u79cc\u7134\u3dcc\u7176\ue182\ue183\u7215\u7250\u7287\u732c\u72b2\u72e2\u7302\u7348\u7328\u734f\u7371\u6585\ue190\u745d\u7468\u751e\u7553\u757a\u76a1\u76cc\u7724\u772b\u4065\u7853\u78d3\u78d8\u9d7e\u78af\ue1a0\u6142\u79a9\u9c3a\u79d4\ue1a5\u7a2d\u7a3e\u7a49\u7add\u7ada\u75c3\u7607\u7602\ue1ae\u7667\u766f\u5620\u8a67\u9f17\u7b1f\u7b92\u7ba3\u7bcf\ue1b8\u7bfc\u7c42\u7c51\u7c5d\u7c70\u7c7e\u7c86\u7c83\u7cac\u7cc7\u7cc2\ue1c4\u7cda\ue1c6\u7d4d\u7d5a\u7df5\u7e27\u7e6e\u9e95\u5273\u801d\u8062\u8063\u8066\u805b\u80a6\u8103\u9e81\u81c8".toCharArray();
		b2c[253] = "\u003f\ue1d7\u8fa0\u81f6\u821a\ue1db\u8771\u87a9\u878c\u9e9e\u87c1\u9e1c\ue1e2\u87d6\u87f5\u880f\u8818\u882d\u8842\u8845\u9c35\u886e\u88aa\u88a0\u629d\u8943\u894d\u7172\u885e\ue1f3\u82fd\u44b7\u82d0\u8385\u9d02\u9f69\u8420\ue1fb\u84da\u84ad\u44fb\u854b\ue200\u8602\u8610\u63d1\u8989\u898a\u8994\u9c45\u89bc\u8a7e\u8a49\u8b0c\u8b43\u8b81\u8b4c\ue20f\u8cad\u8ceb\u8cf7\u8d0c\u8d82\u8da6\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\u8dd4\u8e28\u8e4f\u8e77\u8e75\u8ea7\u8f19\ue21d\u8f5c\u9d44\u90c4\u9151\ue222\u915c\u9159\u9167\u917c\u918e\u91bb\u9ea8\u9056\u904c\u91fe\u9262\u92be\u936b\u936e\u93ba\u9427\u9597\u95a7\ue235\u9638\ue237\u9683\u9696\u9681\u96bd\u9703\u9721\u9731\u975f\u979f\u97b4\u97b8\u97ba\u97be\u97c8\u9ebf\u981f\u982e\u9fa5\u9847\u984b\u9866\u986c\u98b4\u9f5a\u98dc\u98e6\u9942\u9939\u993b\u9f53\u994a\u9f45\u4b7e\u99da\u7ac8\u9aba\u9abd\u9f02\u9b2a\ue25f\u9b2d\u9ae2\u9b02\u9aff\u4c07\u9b09\u4c04\u4c3b\u9b8f\u9b9d\u9bb0\u9c0c\u9c15\u9c0a\u9bff\u9c2e\u80e8\u80e9\u80ec\u810e".toCharArray();
		b2c[254] = "\u003f\u6c39\u4e6a\u9641\u58f2\u7962\u6822\ue27a\ue27b\u8107\u57d7\u44ea\u6435\u8534\u40df\ue282\u691a\u9c02\u75b1\u83d3\u7171\u6a0b\ue289\u6a7a\u9af4\ue28c\ue28d\u77b9\u6530\u6d96\u5637\u6725\u6a2b\u7d5d\u9061\u4e98\u7ca6\u7b39\ue299\u5975\u36c7\u707f\ue29d\u6e8b\ue29f\ue2a0\u975d\u92d0\u5754\ue2a4\u3ed9\ue2a6\ue2a7\u6199\u7460\u713f\u792e\u5a96\u9348\u668e\u4ffd\u9341\u3654\u5d75\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\u701e\u657b\u708d\u5ecd\ue2b7\u60a7\u7ab0\u5f63\ue2bb\u73f7\ue2bd\u7468\u75b4\ue2c0\u9b69\u00bd\u2153\u2154\u00bc\u00be\u2155\u2156\u2157\u2158\u2159\u215a\u8692\u86a0\u86ad\u86b2\u87a5\u87b1\u215b\u215c\u215d\u215e\u87da\u884f\u8860\u8887\u888f\u8890\u88e6\u88ff\u8900\u8924\u8947\u8954\u8965\u8980\u8991\u8a1c\u8a29\u8a2b\u8a38\u8a3d\u2312\u8a90\u8a94\u8a9c\u8aa9\u8aaf\u8ab4\u8aea\u8b1f\u2030\u8b3f\u8b4d\u8b5e\u8b62\u8b69\u8b9b\u8c51\u8c9b\u8c9f\u8cd4\u8cd6\u8112\u8131\u8136\u815f\u816d\u817d\u8193\u81aa\u81b8\u81c1\u8229\u822d\u822e\u823e\u8262\u826a\u8288".toCharArray();


		ByteBuffer buffer = ByteBuffer.wrap(s.getBytes("MS950"));
		String result = "";
		
		while(buffer.remaining()>=2) {
		int pos = buffer.position();
        int high = buffer.get(pos) & 0xFF; 
        int low = buffer.get(pos + 1) & 0xFF;
        if(high<250||high>254) {
        	buffer.position(pos+2);
        	continue;
        }
        low -= (short)b2c[high][0];
        
        char j = b2c[high][low];
        result += j;
        buffer.position(pos+2);
		}
        return result;
	}
}
