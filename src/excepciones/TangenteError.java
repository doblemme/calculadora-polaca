package excepciones;

public class TangenteError extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TangenteError(){
		System.out.println("El angulo ingresado no es valido para la tangente.");
	}

}
