package br.com.drinks.business;


import java.util.List;

import br.com.drinks.erro.EntidadeJaExisteException;

public abstract class BasicBusiness<Entity> {

	public abstract void insert(Entity entity) throws EntidadeJaExisteException;
	public abstract void remove(Entity entity);
	public abstract List<Entity> list();
	public abstract void update(Entity entity);
}
