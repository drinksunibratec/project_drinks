package br.com.drinks.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.drinks.basicas.Produto;
import br.com.drinks.business.ProdutoBusiness;
import br.com.drinks.utils.HttpUtils;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/list/produtos" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity listTodosProdutos(@Context HttpServletRequest request){
		List<Produto> listaProdutos = ProdutoBusiness.getInstancia().list();
		return new ResponseEntity(listaProdutos, HttpUtils.getHeaders(), HttpStatus.OK);
	}

}


