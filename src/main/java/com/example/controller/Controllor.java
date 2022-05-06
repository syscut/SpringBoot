package com.example.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Basm020;
import com.example.model.Basm060;
import com.example.model.Basm060Class;
import com.example.service.Basm060Service;
import com.example.service.Basm020Service;

@CrossOrigin("*")
@RestController
public class Controllor {
	private Basm020Service basm020Service;
	private Basm060Service basm060Service;
	public Controllor(Basm060Service basm060Service,Basm020Service basm020Service) {
		super();
		this.basm060Service = basm060Service;
		this.basm020Service = basm020Service;
	}
	
	
	@PostMapping(path = "/search")
	public  List<Basm060Class> r(@RequestBody Basm060Class basm060Class) throws Exception{
		List<Basm060Class> result = basm060Service.search(basm060Class);
		return result;
	}
	
	@PostMapping(path = "/basn021")
	public  List<Basm020> basn021(@RequestBody Basm020 basm020){
		List<Basm020> result = basm020Service.search(basm020.getZip_code(),basm020.getZip_area());
		return result;
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
