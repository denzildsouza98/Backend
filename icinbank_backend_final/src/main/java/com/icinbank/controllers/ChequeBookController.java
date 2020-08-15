package com.icinbank.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.icinbank.model.Chequebook;
import com.icinbank.response.ChequeResponse;
import com.icinbank.service.ChequebookService;

@RestController
public class ChequeBookController {

	@Autowired
	private ChequebookService service;

	@PostMapping("/cheque/request")
	public ChequeResponse createrequest(@RequestBody Chequebook chequebook) {
		return service.createrequest(chequebook);
	}

	@GetMapping("/cheque/getbyAccount/{account}")
	public List<Chequebook> getRequests(@PathVariable("account") int account) {
		List<Chequebook> list=service.getRequests(account);
		Collections.reverse(list);
		//return service.getRequests(account);
		return list;
	}
	

}
