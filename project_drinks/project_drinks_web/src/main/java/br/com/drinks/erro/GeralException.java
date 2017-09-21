package erro;

public class GeralException extends Exception {

	private static final long serialVersionUID = 1L;

	public GeralException() {
		super();
	}

	public GeralException(Exception e) {
		super(e);
	}

	public GeralException(String s) {
		super(s);
	}

	public GeralException(Throwable t) {
		super(t);
	}
	
}
