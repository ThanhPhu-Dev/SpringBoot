package cf.dinhthanhphu;

public class CustomerNotFoundException extends Exception{

	private static final long serialVersionUID = -6603871463544943998L;
	
	public CustomerNotFoundException(String message) {
		super(message);
	}

}
