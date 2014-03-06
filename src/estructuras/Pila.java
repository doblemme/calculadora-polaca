package estructuras;

import excepciones.PilaVacia;

public class Pila {
	private Nodo inicio;
	
	public Nodo getInicio() {
		return inicio;
	}

	public void setInicio(Nodo inicio) {
		this.inicio = inicio;
	}

	public Pila(){
		inicio=null;
	}
	
	public void Push(String d){
		Nodo nuevo=new Nodo(d);
		if(inicio==null)
			inicio=nuevo;
		else{
			nuevo.setSiguiente(inicio);
			inicio=nuevo;
		}
	}
	
	public String Pop() throws PilaVacia{
		String  n=null;
		if(!isVacia()){
			Nodo aux=inicio;
			inicio=inicio.getSiguiente();
			aux.setSiguiente(null);
			n=aux.getDato();
			aux=null;
		}else
			throw new PilaVacia();
		
		return n;
	}

	private boolean isVacia(){
		return inicio==null;
	}
	
	public void imprimir(){
		if(inicio!=null){
			System.out.print("Inicio -> ");
			Nodo aux=inicio;
			while(aux!=null){
				System.out.print(aux.getDato()+"-> ");
				aux=aux.getSiguiente();
			}
			System.out.print("Fin");
		}else
			System.out.println("Null");
	}

}
