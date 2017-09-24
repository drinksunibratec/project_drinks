package br.com.drinks.fachada;

import java.util.List;

import javax.security.auth.login.LoginException;

import br.com.drinks.basicas.Cliente;
import br.com.drinks.basicas.Estabelecimento;
import br.com.drinks.erro.DaoException;
import br.com.drinks.erro.GeralException;

public interface IDrinksBusiness {
	
	
	public void inserirCLiente(Cliente cliente);
	
	public boolean loginCliente(Cliente cliente);
	
	public void salvarEstabelecimento(Estabelecimento estabelecimento) throws GeralException;
	
	public void alterarEstabelecimento(Estabelecimento estabelecimento) throws GeralException;
	
	public void excluirEstabelecimento(Estabelecimento estabelecimento) throws GeralException;
	
	public Estabelecimento efetuarLogin(String email, String senha) throws LoginException;
	
	public List<Estabelecimento> consultarTodosOsEstabelecimentos() throws DaoException;

}