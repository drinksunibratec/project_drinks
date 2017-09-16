package controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import business.ClienteBusiness;
import basicas.Cliente;

@RestController
@RequestMapping("/clientes")
public class ClienteRestController {
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity insert(@Context HttpServletRequest request, @RequestBody Cliente cliente){
		ClienteBusiness.getInstancia().insert(cliente);
		System.out.println("POrraaa!!!");
		return new ResponseEntity(HttpStatus.OK);
	}

}
