package br.com.drinks.managedbean;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;

import br.com.drinks.business.BasicBusiness;
import br.com.drinks.erro.EntidadeJaExisteException;


public abstract class ManagedBeanGenerico<Entity> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8160386065616067035L;


	private int rowCount;

	private Entity bean;
	
	private List<Entity> list;
	
	private String mensagem;

	private boolean edit;
	
	public abstract BasicBusiness<Entity> getBoPadrao();
	   
    public abstract void setBoPadrao(BasicBusiness<Entity> boPadrao);
    
    public abstract void afterSave();
    
    public abstract void beforeSave();
    
    public abstract void beforeEdit();
    

	@PostConstruct
	public void init() {
		bean = getNewInstanceOfBean();
		initListDataTable();
		setEdit(false);

	}
	
	@SuppressWarnings("unchecked")
	 private Entity getNewInstanceOfBean() {
         try {
           ParameterizedType superClass = 
           (ParameterizedType) getClass()
           .getGenericSuperclass();
		Class<Entity> type = 
           (Class<Entity>) superClass
            .getActualTypeArguments()[0];
                return type.newInstance();
         } catch (InstantiationException e) {
                e.printStackTrace();
                return null;
         } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
         }
   }
	
	private void initListDataTable(){
		list = getAll();
		rowCount = list.size();
	}
	
	public List<Entity> getAll(){
		return  (List<Entity>)getBoPadrao().list();
				
	}
	
	public String inserir() throws EntidadeJaExisteException{
		beforeSave();
		getBoPadrao().insert(bean);
		initListDataTable();
		afterSave();
		return "OK";
	}
	public String editar() {
		beforeEdit();
		getBoPadrao().update(bean);
        edit = false;
        afterSave();
        return "OK";
    }
	
	public String excluir(Entity entity){
		getBoPadrao().remove(entity);
		initListDataTable();
		return "OK";
	}
	
	public String cancelar(){
		afterSave();
        setEdit(false);
        return "OK";
	}
	
	public int getRowCount() {
		return rowCount;
	}


	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}


	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Entity getBean() {
		return (Entity) bean;
	}



	public void setBean(Entity bean) {
		this.bean = bean;
	}



	public List<Entity> getList() {
		return list;
	}



	public void setList(List<Entity> list) {
		this.list = list;
	}



	public boolean isEdit() {
		return edit;
	}



	public void setEdit(boolean edit) {
		this.edit = edit;
	}



}
