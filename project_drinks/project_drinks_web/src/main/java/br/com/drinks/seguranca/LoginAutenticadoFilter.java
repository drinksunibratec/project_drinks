package seguranca;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginAutenticadoFilter
 */
@WebFilter(servletNames="Faces Servlet", filterName="/LoginAutenticadoFilter")
public class LoginAutenticadoFilter implements Filter {

	private static final String[] arquivosEscape = { 
		"javax.faces.resource",
		"index.xhtml", 
		"/pages/home_admin_teste.xhtml"};
	
    public LoginAutenticadoFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession sessao = req.getSession();

		if (verificaPaginaInicial(request)) {
			chain.doFilter(request, response);
		} else {

			if (sessao == null || sessao.getAttribute("loginBean") == null || 
					((LoginBean) sessao.getAttribute("loginBean")).getEstabelecimentoLogado() == null) {
				
				RequestDispatcher dis = request
						.getRequestDispatcher("/index.xhtml");
				dis.forward(request, response);
			} else {

		// pass the request along the filter chain
		chain.doFilter(request, response);
		}
	}
}

	private boolean verificaPaginaInicial(ServletRequest request) {
		// TODO Auto-generated method stub
		
		String resource = ((HttpServletRequest) request).getRequestURI();

		for (String esc : arquivosEscape) {
			if (resource.contains(esc)) {
				return true;
			}
		}
		System.out.println(resource);
		return false;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
