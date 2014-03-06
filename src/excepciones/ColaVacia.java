package excepciones;

public class ColaVacia extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ColaVacia(){
		System.out.println("La cola está vacía;");
	}

}
