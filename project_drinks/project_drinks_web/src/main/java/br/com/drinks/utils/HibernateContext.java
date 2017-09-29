package br.com.drinks.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.drinks.dados.genericos.DAOFactory;

public class HibernateContext implements ServletContextListener  {
	
	@Override //quando desliga o tomcat
    public void contextDestroyed(ServletContextEvent event) {
    }

    @Override //quando liga o tomcat
    public void contextInitialized(ServletContextEvent event) {
    	new DAOFactory();
    }

}
