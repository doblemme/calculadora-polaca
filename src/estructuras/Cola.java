package estructuras;

import excepciones.ColaVacia;
import excepciones.PilaVacia;



public class Cola {
	private NodoDoble inicio;
	private NodoDoble fin;
	
	public Cola(){
		inicio=null;
		fin=null;
	}

	public void Push(String d){
		NodoDoble nuevo=new NodoDoble(d);
		if (inicio== null){
			inicio=nuevo;
			fin=inicio;
		}else{
			nuevo.setSiguiente(inicio);
			inicio.setAnterior(nuevo);
			inicio=nuevo;
		}
	}
	
	public void eliminarTodo() throws ColaVacia{
		while(fin!=null)
			this.Pop();
	}
	
	public String Pop() throws ColaVacia{
		if(inicio==null)
			throw new ColaVacia();
		else if(inicio!=fin){
			NodoDoble aux=fin;
			fin=fin.getAnterior();
			fin.setSiguiente(null);
			aux.setAnterior(null);
			String resultado=aux.getDato();
			aux=null;
			return resultado;
		}else{
			String resultado=inicio.getDato();
			inicio=null;
			fin=null;
			return resultado;
		}
		
	}
	public void toCola(String ecuacion){
		String apoyo="";
		char apoyo2;
		for(int i=0;i<ecuacion.length();i++){
			apoyo2=ecuacion.charAt(i);
			if(apoyo2>=48 && apoyo2<=57){
				apoyo=apoyo+apoyo2;
			}else if(apoyo2=='.')
					apoyo=apoyo+apoyo2;
					else{ 
						if(!apoyo.equals(""))
							this.Push(apoyo);
						this.Push(apoyo2+"");
						apoyo="";
					}
		}
		if(apoyo!="")
			this.Push(apoyo);
	}
	
	public void imprimir(){
		if(inicio!=null){
			System.out.print("Inicio <->");
			NodoDoble aux=inicio;
			while(aux!=null){
				System.out.print(aux.getDato()+" <->");
				aux=aux.getSiguiente();
			}
			System.out.print(" Fin\n");
		}else
			System.out.println("Null");
	}
	
	public Cola toPostfija() throws ColaVacia, PilaVacia{
		if(inicio==null)
			throw new ColaVacia();
		NodoDoble aux=this.fin;
		Cola postfija=new Cola();
		Pila riel=new Pila();
		boolean bandera=false;
		while(aux!=null){
			if(aux.getDato().charAt(0)>=48 && aux.getDato().charAt(0)<=57){
				postfija.Push(this.Pop());
				aux=fin;
			}else{
				if(riel.getInicio()==null || this.fin.getDato().equals("(")){
					if(this.fin.getDato().equals("("))
						bandera=true;
					riel.Push(this.Pop());
					aux=this.fin;
				}else{
					if(this.fin.getDato().equals(")")){
						while(!(riel.getInicio().getDato().equals("(")))
							postfija.Push(riel.Pop());
						System.out.println(riel.Pop()+" "+this.Pop());
						bandera=false;
						aux=fin;
					}else if(bandera==true){
							riel.Push(this.Pop());
							aux=this.fin;
					}else {
						while(riel.getInicio()!=null && riel.getInicio().getValor()>=this.fin.getValor())
							postfija.Push(riel.Pop());
						riel.Push(this.Pop());
						aux=this.fin;
					}
				}
			}
		}
		if(riel.getInicio()!=null)
			while(riel.getInicio()!=null)
				if(riel.getInicio().getDato()!=")" && riel.getInicio().getDato()!="(")
					postfija.Push(riel.Pop());
				else
					System.out.println(riel.Pop());
		return postfija;
	}
	
	public double resuelvePostfija() throws ColaVacia, NumberFormatException, PilaVacia, TangenteError{
		Pila auxiliar=new Pila();
		Double a=0.0,b=0.0,resultado=0.0;
		char simbolo;
		while(this.fin!=null){
			if(this.fin.getDato().charAt(0)>=48 && this.fin.getDato().charAt(0)<=57)
				auxiliar.Push(this.Pop());
			else{
				a=Double.parseDouble(auxiliar.Pop());
				System.out.println(a);
				if(auxiliar.getInicio()==null)
					b=1.0;
				simbolo=this.fin.getDato().charAt(0);
				switch (simbolo){
					case '+':
						if(auxiliar.getInicio()!=null)
							b=Double.parseDouble(auxiliar.Pop());
						resultado=b+a;
						break;
					case '-':
						if(auxiliar.getInicio()!=null)
							b=Double.parseDouble(auxiliar.Pop());
						else
							b=0.0;
							resultado=b-a;
						break;
					case '*':
						if(auxiliar.getInicio()!=null)
							b=Double.parseDouble(auxiliar.Pop());
						resultado=b*a;
						break;
					case '/':
						if(auxiliar.getInicio()!=null)
							b=Double.parseDouble(auxiliar.Pop());
						resultado=b/a;
						break;
					case '^':
						if(auxiliar.getInicio()!=null)
							b=Double.parseDouble(auxiliar.Pop());
						resultado=Math.pow(b, a);
						break;
					case 'q':
						resultado=Math.sqrt(a);
						break;
					case 's':
						if(a%180==0)
							resultado=0.0;
						else
							resultado=Math.sin(Math.toRadians(a));
						break;
					case 'c':
						if(a%90==0 && (a/90)%2==1)
							resultado=0.0;
						else
							resultado=Math.cos(Math.toRadians(a));
						break;
					case 't':
						if(a%90==0 && (a/90)%2==1)
							throw new TangenteError();
						else if(a.equals(180.0) || a.equals(360.0))
							resultado=0.0;
						else
							resultado=Math.tan(Math.toRadians(a));
						break;
				}
				System.out.println(b+this.Pop()+a+"= "+resultado);
				auxiliar.Push(resultado+"");
				resultado=0.0;
			}
		}
		return Double.parseDouble(auxiliar.getInicio().getDato());
	}

}
