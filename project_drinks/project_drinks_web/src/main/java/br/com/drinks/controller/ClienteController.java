package br.com.drinks.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.drinks.basicas.Cliente;
import br.com.drinks.business.ClienteBusiness;
import br.com.drinks.utils.HttpUtils;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/inserir", method = RequestMethod.POST, consumes = {"application/json"})
	public @ResponseBody ResponseEntity insert(@Context HttpServletRequest request, @RequestBody Cliente cliente){
		boolean inseriu = ClienteBusiness.getInstancia().insert(cliente);
		return new ResponseEntity(inseriu, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = {"application/json"})
	public @ResponseBody ResponseEntity login(@Context HttpServletRequest request, @RequestBody Cliente cliente){
		boolean loginOK = ClienteBusiness.getInstancia().login(cliente);
		return new ResponseEntity(loginOK, HttpUtils.getHeaders(), HttpStatus.OK);
	}
	
	
}
