package br.com.drinks.fachada;

import java.util.List;

import javax.security.auth.login.LoginException;

import br.com.drinks.basicas.Cliente;
import br.com.drinks.basicas.Estabelecimento;
import br.com.drinks.basicas.Produto;
import br.com.drinks.dados.genericos.DAOFactory;
import br.com.drinks.erro.DaoException;
import br.com.drinks.erro.GeralException;



public class DrinksBusiness implements IDrinksBusiness{

	private static IDrinksBusiness fachada;

	public static IDrinksBusiness getInstancia() {
		if (fachada == null) {
			fachada = new DrinksBusiness();
		}
		return fachada;
	}	
	//---------Cliente-------
	@Override
	public void inserirCLiente(Cliente entidade) {
		DAOFactory.getClienteDAO().inserir(entidade);
		
	}


	@Override
	public boolean loginCliente(Cliente cliente) {
		return DAOFactory.getClienteDAO().login(cliente);
	}
	
	
	@Override
	public List<Cliente> consultarTodosOsClientes() {
		return DAOFactory.getClienteDAO().consultarTodos();
	}

	//---------Estabelecimento-------
	@Override
	public void salvarEstabelecimento(Estabelecimento entidade) throws GeralException{
		DAOFactory.getEstabelecimentoDAO().inserir(entidade);		
	}

	@Override
	public void alterarEstabelecimento(Estabelecimento entidade) throws GeralException {
		DAOFactory.getEstabelecimentoDAO().alterar(entidade);			
	}
	
	@Override
	public void excluirEstabelecimento(Estabelecimento entidade) throws GeralException {
		DAOFactory.getEstabelecimentoDAO().remover(entidade);		
	}

	@Override
	public Estabelecimento efetuarLogin(String email, String senha) throws LoginException {
		return DAOFactory.getEstabelecimentoDAO().efetuarLogin(email, senha);
	}

	@Override
	public List<Estabelecimento> consultarTodosOsEstabelecimentos() throws DaoException {
		return DAOFactory.getEstabelecimentoDAO().consultarTodos();
	}
	
	@Override
	public boolean existeEstabelecimentoPorCNPJ(Estabelecimento estabelecimento) throws GeralException {
		return DAOFactory.getEstabelecimentoDAO().existeEstabelecimentoPorCNPJ(estabelecimento);
	}
	
	//---------Produtos-------
	
	@Override
	public void salvarProduto (Produto produto) throws GeralException {
		DAOFactory.getProdutoDAO().inserir(produto);
	}
	
	@Override
	public void alterarProduto(Produto produto) throws GeralException {
		DAOFactory.getProdutoDAO().alterar(produto);
	}

	@Override
	public List<Produto> consultarTodosOsProdutos() {
		return DAOFactory.getProdutoDAO().consultarTodos();
	}


}