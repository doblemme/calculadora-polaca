package principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import estructuras.Cola;
import excepciones.ColaVacia;
import excepciones.PilaVacia;
import excepciones.TangenteError;

public class Ventana extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cola ecuacion;
	private String operaciones;
	private String operaciones2;
	private JTextField texto;
	public Cola getEcuacion() {
		return ecuacion;
	}
	public void setEcuacion(Cola ecuacion) {
		this.ecuacion = ecuacion;
	}
	
	public Ventana(){
		super("Calculadora");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(400, 70, 320, 350);
		this.setResizable(false);
		
		String labelButtons[] ={"7","8","9","/","sin",
								"4","5","6","*","cos",
								"1","2","3","-","tan",
								"0",".","=","+","^","sqrt","(",")","DEL","C"};
		JPanel todo=(JPanel)this.getContentPane();
		todo.setLayout(new BorderLayout());
		JPanel botones=new JPanel();
		botones.setLayout(new GridLayout(0,5));
		for(int i=0;i<labelButtons.length;i++){
			JButton button =new JButton(labelButtons[i]);
			button.addActionListener(this);
			if(labelButtons[i].charAt(0)>='0' && labelButtons[i].charAt(0)<='9')
				button.setBackground(Color.GRAY);
			else if(labelButtons[i].length()==3 || labelButtons[i].equals("C"))
				button.setBackground(Color.ORANGE);
			else
				button.setBackground(Color.LIGHT_GRAY);
			button.setForeground(Color.BLUE);
			botones.add(button);
		}
		texto= new JTextField();
		texto.setHorizontalAlignment(JTextField.RIGHT);
		texto.setToolTipText("Display");
		texto.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		operaciones="";
		operaciones2="";
		texto.setForeground(Color.MAGENTA);
		texto.setFont(new Font("TimesRoman",Font.BOLD,22));
		texto.setText("0");
		texto.setEditable(false);
		todo.add(texto, BorderLayout.NORTH);
		todo.add(botones,BorderLayout.CENTER);
		ecuacion=new Cola();
		}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(operaciones2.equals("Error")){
			operaciones2="";
			operaciones="";
		}
		char caracter=((JButton) e.getSource()).getText().charAt(0);
		if(caracter>=48 && caracter<=57){
			operaciones=operaciones+caracter;
			operaciones2=operaciones2+caracter;
			texto.setText(operaciones2);
		}else{
			if(caracter=='C'){
				operaciones="";
				operaciones2="";
				texto.setText("0");
				try {
					ecuacion.eliminarTodo();
				} catch (ColaVacia e1) {
					e1.printStackTrace();
				};
			}else if(caracter=='='){
				try {
					ecuacion.toCola(operaciones);
					Cola postfija=ecuacion.toPostfija();
					postfija.imprimir();
					Double resultado=postfija.resuelvePostfija();
					texto.setText(resultado+"");
					operaciones=resultado+"";
					operaciones2=resultado+"";
					ecuacion.eliminarTodo();
				} catch (ColaVacia e1) {
					operaciones2="Error";
					e1.printStackTrace();
				} catch (PilaVacia e1) {
					operaciones2="Error";
					e1.printStackTrace();
				} catch (NumberFormatException e1) {
					operaciones2="Error";
					e1.printStackTrace();
				} catch (TangenteError e1) {
					operaciones2="Error";
					e1.printStackTrace();
				}
			}else if(caracter=='D'){
				if(operaciones2.length()!=0){
					char ultimo=operaciones2.charAt(operaciones2.length()-1);
					switch(ultimo){
					case 't':
						operaciones=operaciones.substring(0, operaciones.length()-1);
						operaciones2=operaciones2.substring(0, operaciones2.length()-4);
						break;
					case 's':
					case 'n':
						operaciones=operaciones.substring(0, operaciones.length()-1);
						operaciones2=operaciones2.substring(0, operaciones2.length()-3);
						break;
					default:
						operaciones=operaciones.substring(0, operaciones.length()-1);
						operaciones2=operaciones2.substring(0, operaciones2.length()-1);
						break;
					}
				}
			}else{
			}
				switch(caracter){
				case '+':
				case'*':
				case'/':
				case'^':
				case'.':
				case'(':
				case')':
					operaciones=operaciones+caracter;
					operaciones2=operaciones2+caracter;
					break;
				case'-':
					if(operaciones=="")
						operaciones=operaciones+"0-";
					else if(!(operaciones.charAt(operaciones.length()-1)>='0' && operaciones.charAt(operaciones.length()-1)<='9'))
						operaciones=operaciones+"0-";
					else
						operaciones=operaciones+caracter;
					operaciones2=operaciones2+caracter;
					break;
				case 'c':
					operaciones=operaciones+caracter;
					operaciones2=operaciones2+" cos";
					break;
				case 't':
					operaciones=operaciones+caracter;
					operaciones2=operaciones2+" tan";
					break;
				case 's':
					char caracter2=((JButton) e.getSource()).getText().charAt(1);
					if(caracter2=='q'){
						operaciones=operaciones+"q";
						operaciones2=operaciones2+" sqrt";
					}else{
						operaciones=operaciones+"s";
						operaciones2=operaciones2+" sin";
					}
					break;
				}
				if(operaciones2.length()==0)
					texto.setText("0");
				else
					texto.setText(operaciones2);
			}
		}
}
