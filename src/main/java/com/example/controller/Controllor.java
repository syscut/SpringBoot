package com.example.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Basm020;
import com.example.model.Basm060;
import com.example.model.Basm060Class;
import com.example.model.Mafm080;
import com.example.model.Psnm110;
import com.example.service.Basm060Service;
import com.example.service.LoginService;
import com.example.service.Mafm080Service;
import com.example.service.SystemPrgService;
import com.example.service.Basm020Service;

@CrossOrigin("*")
@RestController
public class Controllor {
	@Autowired
	private Basm020Service basm020Service;
	@Autowired
	private Basm060Service basm060Service;
	@Autowired
	private Mafm080Service mafm080Service;
	@Autowired
	private SystemPrgService systemPrgService;
	@Autowired
	private LoginService loginService;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@PostMapping(path = "/menu")
	public  Map<String, Object> menu(@RequestBody Map<String, String> usrInf){
		return systemPrgService.getMenu(usrInf);
	}
	
	@PostMapping(path = "/getDate")
	public  String getDate(){
		return LocalDate.now().format(formatter);
	}
	
	@PostMapping(path = "/login")
	public Map<String, String> login(@RequestBody Psnm110 psnm110){
		Map<String,String> result = new LinkedHashMap<>();
		if(loginService.checkEmpNo(psnm110.getEmpNo())) {
			result.put("status", "invaildUserId");
			return result;
		}
		List<Psnm110> psnm110List = loginService.checkUsrPaswd(psnm110.getEmpNo(), psnm110.getUsrPaswd());
		if(psnm110List.size()==0) {
			result.put("status", "invaildPassWord");
			return result;
		}
		result.put("status", "ok");
		result.put("empName", psnm110List.get(0).getEmpName());
		result.put("usrGroup", psnm110List.get(0).getUsrGroup());
		return result;
	}
	
	@PostMapping(path = "/search")
	public  List<Basm060Class> r(@RequestBody Basm060Class basm060Class) throws Exception{
		return basm060Service.search(basm060Class);
	}
	
	@PostMapping(path = "/basn021")
	public  List<Basm020> basn021(@RequestBody Basm020 basm020){
		return basm020Service.search(basm020.getZip_code(),basm020.getZip_area());
	}
	
	@PostMapping(path = "/mafm080")
	public  Iterable<Mafm080> mafm080(){
		return mafm080Service.findAll();
	}
	
	@PostMapping(path = "/update")
	public Map<String, Object> u(@RequestBody Basm060 basm060){
		Map<String, Object> result = basm060Service.update(basm060);
		return result;
	}
	
	@PostMapping(path = "/create")
	public Map<String, Object> c(@RequestBody Basm060 basm060){
		Map<String, Object> result = basm060Service.create(basm060);
		return result;
	}
	
	@PostMapping(path = "/delete")
	public Map<String, Object> d(@RequestBody Basm060 basm060){
		Map<String, Object> result = basm060Service.delete(basm060);
		return result;
	}
}
