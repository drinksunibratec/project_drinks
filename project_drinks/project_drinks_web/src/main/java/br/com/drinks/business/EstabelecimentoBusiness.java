package br.com.drinks.business;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import br.com.drinks.basicas.Estabelecimento;
import br.com.drinks.erro.DaoException;
import br.com.drinks.erro.EntidadeJaExisteException;
import br.com.drinks.erro.GeralException;
import br.com.drinks.fachada.DrinksBusiness;


public class EstabelecimentoBusiness extends BasicBusiness<Estabelecimento> {


	private static EstabelecimentoBusiness instancia;

	public static EstabelecimentoBusiness getInstancia() {
		if(instancia == null){
			instancia = new EstabelecimentoBusiness();
		}
		return instancia;
	}

	@Override
	public void insert(Estabelecimento entity)  throws EntidadeJaExisteException{
		try {
			
			boolean existe = DrinksBusiness.getInstancia().existeEstabelecimentoPorCNPJ(entity);
			if(existe) {
				throw new EntidadeJaExisteException("Estabelecimento com esse CPNJ já existe");
			}else {
				DrinksBusiness.getInstancia().salvarEstabelecimento(entity);
			}
		} catch (GeralException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void remove(Estabelecimento entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Estabelecimento> list() {
		List<Estabelecimento> lista = new ArrayList<Estabelecimento>();
		try {
			lista = DrinksBusiness.getInstancia().consultarTodosOsEstabelecimentos();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public void update(Estabelecimento entity) {
		try {
			DrinksBusiness.getInstancia().alterarEstabelecimento(entity);
		} catch (GeralException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Estabelecimento pesquisarEstabelecimentoPorLogin(String login){

		Estabelecimento estabelecimento = new Estabelecimento();
		List<Estabelecimento> estabelecimentos = new ArrayList<Estabelecimento>();

		try {
			estabelecimentos = this.listarEstabelecimento();
			for (int i = 0; i < estabelecimentos.size(); i++) {
				if(estabelecimentos.get(i).getLogin().equals(login)){
					estabelecimento = estabelecimentos.get(i);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return estabelecimento;
	}

	public Estabelecimento efeturarLogin(String login, String senha)throws LoginException{
		return DrinksBusiness.getInstancia().efetuarLogin(login, senha);
	}

	public List<Estabelecimento> listarEstabelecimento() throws DaoException{
		List<Estabelecimento> listaDeEstabelecimentos = new ArrayList<Estabelecimento>();
		listaDeEstabelecimentos = DrinksBusiness.getInstancia().consultarTodosOsEstabelecimentos();
		return listaDeEstabelecimentos;
	}

}
