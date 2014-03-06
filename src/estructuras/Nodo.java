package estructuras;

public class Nodo {
	private String dato;
	private Nodo siguiente;
	private int valor;
	
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public String getDato() {
		return dato;
	}
	public void setDato(String dato) {
		this.dato = dato;
	}
	public Nodo getSiguiente() {
		return siguiente;
	}
	public void setSiguiente(Nodo siguiente) {
		this.siguiente = siguiente;
	}
	
	public Nodo(String d){
	dato=d;
	siguiente=null;
	if(d.equals("+") || d.equals("-"))
		valor=1;
	else if(d.equals("*") || d.equals("/") )
		valor=2;
	else if(d.equals("s") || d.equals("c") || d.equals("t"))
		valor=3;
	else if(d.equals("^") || d.equals("q"))
		valor=4;
	else
		valor=0;
	}
	
}
