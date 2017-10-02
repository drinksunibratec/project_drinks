package br.com.drinks.business;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
	public boolean insert(Estabelecimento entity)  throws EntidadeJaExisteException{
		boolean inseriu = false;
		try {
			
			Estabelecimento estabelecimento = DrinksBusiness.getInstancia().consultarEstabelecimentoPorCNPJ(entity);
			if(estabelecimento != null) {
				throw new EntidadeJaExisteException("Estabelecimento com esse CPNJ j� existe");
			}else {
				estabelecimento = DrinksBusiness.getInstancia().consultarEstabelecimentoPorLogin(estabelecimento);
				if(estabelecimento != null) {
					throw new EntidadeJaExisteException("J� existe Estabelecimento com este Login!");
				}else {	
					DrinksBusiness.getInstancia().salvarEstabelecimento(entity);
					estabelecimento = DrinksBusiness.getInstancia().consultarEstabelecimentoPorCNPJ(entity);
					if(estabelecimento != null) {
						inseriu = true;
					}
				}
			}
		} catch (GeralException e) {
			e.printStackTrace();
		}
		return inseriu;
				

	}

	@Override
	public void remove(Estabelecimento entity) {
		try {
			DrinksBusiness.getInstancia().excluirEstabelecimento(entity);
		} catch (GeralException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<Estabelecimento> list() {
		List<Estabelecimento> lista = new ArrayList<Estabelecimento>();
		try {
			lista = DrinksBusiness.getInstancia().consultarTodosOsEstabelecimentos();
		} catch (DaoException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("N�o Foi poss�vel Listar Estabelecimentos!"));
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public void update(Estabelecimento entity) {
		try {
			DrinksBusiness.getInstancia().alterarEstabelecimento(entity);
		} catch (GeralException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("N�o Foi poss�vel Alterar Estabelecimento!"));
			
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
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login N�o Encontrado!"));
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
