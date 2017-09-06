package business;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import basicas.Estabelecimento;
import erro.DaoException;
import erro.GeralException;
import fachada.DrinksBusiness;

public class EstabelecimentoBusiness extends BasicBusiness<Estabelecimento> {


	private static EstabelecimentoBusiness instancia;

	public static EstabelecimentoBusiness getInstancia() {
		if(instancia == null){
			instancia = new EstabelecimentoBusiness();
		}
		return instancia;
	}

	@Override
	public void insert(Estabelecimento entity)  {
		try {
			DrinksBusiness.getInstancia().salvarEstabelecimento(entity);
		} catch (GeralException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void remove(Estabelecimento entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Estabelecimento> list() {
		// TODO Auto-generated method stub
		return null;
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

	public Estabelecimento pesquisarEstabelecimentoPorLogin(String eMail){

		Estabelecimento estabelecimento = new Estabelecimento();
		List<Estabelecimento> estabelecimentos = new ArrayList<Estabelecimento>();

		try {
			estabelecimentos = this.listarEstabelecimento();
			for (int i = 0; i < estabelecimentos.size(); i++) {
				if(estabelecimentos.get(i).geteMail().equals(eMail)){
					estabelecimento = estabelecimentos.get(i);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return estabelecimento;
	}

	public Estabelecimento efeturarLogin(String eMail, String senha)throws LoginException{
		return DrinksBusiness.getInstancia().efetuarLogin(eMail, senha);
	}

	public List<Estabelecimento> listarEstabelecimento() throws DaoException{
		List<Estabelecimento> listaDeEstabelecimentos = new ArrayList<Estabelecimento>();
		listaDeEstabelecimentos = DrinksBusiness.getInstancia().consultarTodosOsEstabelecimentos();
		return listaDeEstabelecimentos;
	}

}
