package controller;


import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import dados.geral.DaoEstabelecimento;
import dados.geral.IDaoEstabelecimento;
import erro.DaoException;
import basicas.Estabelecimento;

public class ControladorEstabelecimento {
	
private IDaoEstabelecimento daoEstabelecimento;
	
	public ControladorEstabelecimento(){
		this.daoEstabelecimento = new DaoEstabelecimento(null);
	}
	
	public void cadastrarEstabelecimento(Estabelecimento estabelecimento){
		this.daoEstabelecimento.inserir(estabelecimento);
	}
	
	public void alterarEstabelecimento(Estabelecimento estabelecimento){
		this.daoEstabelecimento.alterar(estabelecimento);
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
		return daoEstabelecimento.efetuarLogin(eMail, senha);
	}
	
	public List<Estabelecimento> listarEstabelecimento() throws DaoException{
		List<Estabelecimento> listaDeEstabelecimentos = new ArrayList<Estabelecimento>();
		listaDeEstabelecimentos = this.daoEstabelecimento.consultarTodos();
		return listaDeEstabelecimentos;
	}
	
}