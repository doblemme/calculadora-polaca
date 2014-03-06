package estructuras;

public class NodoDoble {
	private String dato;
	private NodoDoble siguiente;
	private NodoDoble anterior;
	private int valor;
	
	
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public NodoDoble getAnterior() {
		return anterior;
	}
	public void setAnterior(NodoDoble anterior) {
		this.anterior = anterior;
	}
	public String getDato() {
		return dato;
	}
	public void setDato(String dato) {
		this.dato = dato;
	}
	public NodoDoble getSiguiente() {
		return siguiente;
	}
	public void setSiguiente(NodoDoble siguiente) {
		this.siguiente = siguiente;
	}
	
	public NodoDoble(String d){
	dato=d;
	siguiente=null;
	anterior=null;
	if(d.equals("+") || d.equals("-"))
		valor=1;
	else if(d.equals("*") || d.equals("/"))
		valor=2;
	else if(d.equals("s") || d.equals("c") || d.equals("t"))
		valor=3;
	else if(d.equals("^") || d.equals("q"))
		valor=4;
	else
		valor=0;
	}
	
}
