package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Psnm110;
import com.example.repository.Psnm110Repository;

@Service
@Transactional
public class LoginService {
	
	@Autowired
	Psnm110Repository psnm110Repository;

	public Boolean checkEmpNo(Integer emp_no){
		return psnm110Repository.findByEmpNo(emp_no).size() == 0;
	}
	
	public List<Psnm110> checkUsrPaswd(Integer emp_no,String usr_paswd){
		return psnm110Repository.findByEmpNoAndUsrPaswd(emp_no, usr_paswd);
	}
}
