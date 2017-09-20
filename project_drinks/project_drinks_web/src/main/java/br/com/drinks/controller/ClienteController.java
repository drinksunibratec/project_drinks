package br.com.drinks.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.drinks.basicas.Cliente;
import br.com.drinks.basicas.Message;
import br.com.drinks.business.ClienteBusiness;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/inserir", method = RequestMethod.POST, consumes = {"application/json"})
	public @ResponseBody ResponseEntity insert(@Context HttpServletRequest request, @RequestBody Cliente cliente){
		ClienteBusiness.getInstancia().insert(cliente);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	
	@RequestMapping("/")
	public String welcome() {//Welcome page, non-rest
		return "Welcome to RestTemplate Example.";
	}

	@RequestMapping("/hello/{player}")
	public Message message(@PathVariable String player) {//REST Endpoint.

		Message msg = new Message(player, "Hello " + player);
		return msg;
	}

	
}
