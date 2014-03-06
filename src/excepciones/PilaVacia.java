package excepciones;

public class PilaVacia extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PilaVacia(){
		System.out.println("La pila esá vacía.");
	}

}
