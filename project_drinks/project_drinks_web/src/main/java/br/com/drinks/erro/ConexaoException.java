package erro;

public class ConexaoException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ConexaoException() {
		super();
	}

	public ConexaoException(Exception e) {
		super(e);
	}

	public ConexaoException(String s) {
		super(s);
	}

	public ConexaoException(Throwable t) {
		super(t);
	}
	
}
