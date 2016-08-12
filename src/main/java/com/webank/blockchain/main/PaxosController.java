package com.webank.blockchain.main;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class PaxosController {
	@CrossOrigin
	@RequestMapping(value = "/prepare", method = RequestMethod.POST)
	public String bc() {
		return "";
	}
}
