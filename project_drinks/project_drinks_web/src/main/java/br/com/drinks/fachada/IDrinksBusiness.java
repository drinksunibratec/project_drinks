package br.com.drinks.fachada;

import java.util.List;

import javax.security.auth.login.LoginException;

import br.com.drinks.basicas.Cliente;
import br.com.drinks.basicas.Estabelecimento;
import br.com.drinks.basicas.Produto;
import br.com.drinks.erro.DaoException;
import br.com.drinks.erro.GeralException;

public interface IDrinksBusiness {
	
	//---------Cliente-------
	public void inserirCLiente(Cliente cliente);
	
	public boolean loginCliente(Cliente cliente);
	
	public Cliente consultarClientePorEmail(Cliente cliente);
	
	public List<Cliente> consultarTodosOsClientes();
	
	//---------Estabelecimento-------	
	public void salvarEstabelecimento(Estabelecimento entidade) throws GeralException;
	
	public void alterarEstabelecimento(Estabelecimento entidade) throws GeralException;
	
	public void excluirEstabelecimento(Estabelecimento entidade) throws GeralException;
	
	public Estabelecimento efetuarLogin(String login, String senha) throws LoginException;
	
	public List<Estabelecimento> consultarTodosOsEstabelecimentos() throws DaoException;
	
	public Estabelecimento consultarEstabelecimentoPorCNPJ(Estabelecimento estabelecimento) throws GeralException;
	
	public Estabelecimento consultarEstabelecimentoPorLogin(Estabelecimento estabelecimento) throws GeralException;
	
	//---------Produtos-------
	public void salvarProduto(Produto produto) throws GeralException;
	
	public void alterarProduto(Produto produto) throws GeralException;
	
	public void excluirProduto(Produto produto) throws GeralException;
	
	public List<Produto> consultarTodosOsProdutos(); 

	
	
}