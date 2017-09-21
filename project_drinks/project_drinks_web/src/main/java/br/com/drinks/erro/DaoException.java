package erro;

public class DaoException extends Exception{

private static final long serialVersionUID = 1L;
	
	public DaoException(){
        super();
    }

    public DaoException(Exception e){
        super(e);
    }

    public DaoException(String s){
        super(s);
    }
    
    public DaoException(Throwable t){
        super(t);
    }
    
}
