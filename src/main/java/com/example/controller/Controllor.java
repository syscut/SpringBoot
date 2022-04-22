package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Basm020;
import com.example.model.Basm020Class;
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
	public  List<Basm060Class> s(@RequestBody Basm060 basm060){
		List<Basm060Class> result = basm060Service.search(basm060.getCustNo());
		return result;
	}
	
	@PostMapping(path = "/basn021")
	public  List<Basm020Class> basn021(@RequestBody Basm020 basm020){
		List<Basm020Class> result = basm020Service.search(basm020.getZipCode(),basm020.getZipArea());
		return result;
	}
}
