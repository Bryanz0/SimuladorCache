package main;
import noname.*;
import servicios.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
/**
 * Simulador de Memoria cache (Cache Simulator)
 * 
 * El siguiente programa simula el almacenamiento de direcciones de la memoria principal a la memoria cache
 * utilizando un mapeo asociativo. Este es un ejemplo sencillo de este funcionamiento por lo cual, tanto el tamaño
 * de la memoria principal como la memoria cache es sumamente pequeño para los datos reales.
 * 
 * 
 *  Esta el la clase donde se despliegara los datos la simulacion mediante una GUI los datos comprendidos son:
 * Memoria Principal (Main Memory)
 * Memoria Cache (Cache)
 * Numero de exitos y perdidas (Miss and hits rate)
 * Ejemplo de un reemplazo del algoritmo LRU (Least Recently Used)
 * Ejemplo de politica Write Through 
 * 
 * 
 */



public class GUI extends JFrame implements ActionListener {
	
	static Reemplazo r = new Reemplazo();
	static MainMemory mm = new MainMemory();
	static MissAndHit mh= new MissAndHit();
	static Cache c = new Cache();
	int [] result=new int[2];
	
	private static String acumulador="";
	private static final long serialVersionUID = 1L;
	private JLabel lblmembrete1,lblmembrete2,lblmembrete3,lblmembrete4,lblmembrete5,lblmembrete6,lblmembrete8,lblmembrete9,lblmembrete10,lblmembrete11,lblmembrete12,lblmembrete13;
	private JButton btnboton1, btnboton2, btnboton3;
	private JTextArea txtSalida = new JTextArea("");
	private Container cntPrincipal;
	private JPanel panelDatos = new JPanel(new GridLayout());
	private JScrollPane panelSalida = new JScrollPane(); 
	private JPanel panelBotones = new JPanel(new GridLayout());
	
	
	private JLabel label;
	private JPanel panel;
	private JPanel panel_1;
	
	
	public GUI() {
		//titulo (title)
		super("Simulador de memoria cache");
		
		setSize(500, 400);
		setLocationByPlatform(true);
		//panel principal
		cntPrincipal = getContentPane();
		cntPrincipal.setLayout(new BorderLayout());
		
		// Agrega un panel al panel principal en la parte norte
		cntPrincipal.add(panelDatos, BorderLayout.NORTH);
		cntPrincipal.add(panelSalida, BorderLayout.CENTER);
		cntPrincipal.add(panelBotones, BorderLayout.SOUTH);
		
		// agrega coponentes al panel de datos
		lblmembrete1 = new JLabel("Simulador");
		lblmembrete1.setToolTipText("pulse en iniciar");
		//panel de datos
		panelDatos.add(lblmembrete1);
		panel = new JPanel();
		panelDatos.add(panel);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 183, Short.MAX_VALUE)
				.addGap(0, 183, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 23, Short.MAX_VALUE)
				.addGap(0, 23, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
		panel_1 = new JPanel();
		panelDatos.add(panel_1);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 183, Short.MAX_VALUE)
				.addGap(0, 183, Short.MAX_VALUE)
		);
		gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 23, Short.MAX_VALUE)
				.addGap(0, 23, Short.MAX_VALUE)
		);
		panel_1.setLayout(gl_panel_1);
		//se agrega el txt de salida
		panelSalida.setViewportView(txtSalida);
		
		
		// agrega componentes al panel de botones
		btnboton1 = new JButton();
		btnboton1.setText("INICIAR");
		
		btnboton1.setActionCommand("Aceptar");
		btnboton1.addActionListener(this);
		
		// boton de comandos
		btnboton2 = new JButton();
		btnboton2.setText("Cancelar");
		
		btnboton2.setActionCommand("Cancelar");
		btnboton2.addActionListener(this);
		
		// boton de comandos
		btnboton3 = new JButton();
		btnboton3.setText("Salir");
		
		btnboton3.setActionCommand("Salir");
		btnboton3.addActionListener(this);
		//Agregan los botones
		panelBotones.add(btnboton1);
		panelBotones.add(btnboton2);
		panelBotones.add(btnboton3);
		
		super.setBounds(300, 50, 750 , 600);
		setVisible(true);
		
	}
	
	public static void main(String args[]) {
		
		GUI application = new GUI();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String a = e.getActionCommand();
		
		// BOTON DE SALIR//
		if (a.equals("Salir")) {
			System.exit(0);
			
		}
		// boton aceptar
		if (a.equals("Aceptar")) {
			// se llenan la memoria principal
			mm.llenarMM();
			// se llenan la memoria cache
			c.llenarCache(mm);
			//se almacena la informacion en un acumulador
			acumulador+="---------------------------------------------------\n";
			acumulador+="MEMORIA PRINCIPAL\n";
			acumulador+=mm.imprimirMM()+"\n";
			acumulador+="---------------------------------------------------\n";
			acumulador+="MEMORIA CACHE\n";
			acumulador+=c.imprimirCache()+"\n";
			//se llama a la funcion que cuenta el numero de exitos y perdidas
			result=mh.result(mm,c);
			//resultados en el acumulador
			acumulador+="Se calcula el numero de perdidas y exitos en la busqueda de direcciones en memoria cache\n";			
			acumulador+="perdidas: "+result[0]+"\n";
			acumulador+="exitos: "+result[1]+"\n";
			// se declara la direccion y una variable de comparacion
			String direccion="";
			boolean res=false;
			while (res==false){
				//se declara una direccion aleatoria de la memoria principal
			int addressRandom= (int) (Math.random()*mm.getSizeMM()+1);
			String bloque=mm.MainMemory[addressRandom-1];
			//se verifica si esa direccion ya esta en la cache o no
			res=mh.direccionEnMM(c, bloque);
			direccion=bloque;
			}
			acumulador+="---------------------------------------------------\n";
			acumulador+="Seccion LRU\n";
			acumulador+="La direccion a remplazar en la memoria cache es: "+direccion+"\n";
			//llamado al metodo de reemplazo
			r.reemplazoLRU(c, direccion);
			acumulador+="---------------------------------------------------\n";
			acumulador+="MEMORIA CACHE\n";
			acumulador+=c.imprimirCache()+"\n";
			String word="aa";
			//se obtiene una direccion de MM aleatoria para reemplazar los bits de palabra 
			String address=c.cache[(int) (Math.random()*c.getSizeCache())];
			acumulador+="Seccion Write Through\n";
			acumulador+="Se va a reemplazar los 2 bits de palabra de la direccion: "+address+" por 'aa'\n";
			//llamado a la funcion de Write Through
			r.writeThrough(mm, c, word, address);
			acumulador+="---------------------------------------------------\n";
			acumulador+="MEMORIA PRINCIPAL\n";
			acumulador+=mm.imprimirMM()+"\n";
			acumulador+="---------------------------------------------------\n";
			acumulador+="MEMORIA CACHE\n";
			acumulador+=c.imprimirCache()+"\n";
				txtSalida.append(acumulador);
			return;			
		}
		
		
		if (a.equals("Cancelar")) {
			return;
		}
	}
}