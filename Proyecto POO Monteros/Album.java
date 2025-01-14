/*	Proyecto Final de Programación Orientada a Objetos
	2CM4
	Alumnos: Monteros Cervantes Miguel Angel
    		 Martínez Ortiz Fabiola Yahel
             Martínez Ramírez Bryan Jair
*/
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.io.*;
import javax.swing.event.*;

public class Album extends JFrame implements ActionListener,Runnable{
	
	private JPanel Superior, Inferior, Botonera, abajo, izq, der;
	//private JPanel panel;
	private JButton botones[], iniciar, atras, adelante, zoom, comentar,eli_com;
	private ImageIcon imagenes [];
	private Thread cambio;
	Container c = getContentPane();
	Icon icono;
	JLabel foto,comentario[], aux;
	int i, x, t, convertido, res;
	boolean it;
	String test;
	JTextField texto, come;
	JSlider slider=new JSlider(0,110,1);
	
	public Album(){
	
	texto = new JTextField(); come = new JTextField(); 
	zoom = new JButton("Zoom"); comentar = new JButton("Comentar");
	eli_com = new JButton("Eliminar Comentario");
	comentario = new JLabel[28];
	
	c.setLayout(new  FlowLayout(FlowLayout.CENTER));	
	Superior = new JPanel();
    Superior.setLayout(new GridLayout(4,7));
	
	cambio = new Thread(this);
	
	botones=new JButton[28];
	imagenes = new ImageIcon[28];
	imagenes[0]= new ImageIcon("1.PNG");
	imagenes[1]= new ImageIcon("2.PNG");
	imagenes[2]= new ImageIcon("3.PNG");
	imagenes[3]= new ImageIcon("4.PNG");
	imagenes[4]= new ImageIcon("5.PNG");
	imagenes[5]= new ImageIcon("6.PNG");
	imagenes[6]= new ImageIcon("7.PNG");
	imagenes[7]= new ImageIcon("8.PNG");
	imagenes[8]= new ImageIcon("9.PNG");
	imagenes[9]= new ImageIcon("10.PNG");
	imagenes[10]= new ImageIcon("11.PNG");
	imagenes[11]= new ImageIcon("12.PNG");
	imagenes[12]= new ImageIcon("13.PNG");
	imagenes[13]=new ImageIcon("14.PNG");
	imagenes[14]= new ImageIcon("15.PNG");
	imagenes[15]= new ImageIcon("16.PNG");
	imagenes[16]= new ImageIcon("17.PNG");
	imagenes[17]= new ImageIcon("18.PNG");
	imagenes[18]= new ImageIcon("19.PNG");
	imagenes[19]= new ImageIcon("20.PNG");
	imagenes[20]= new ImageIcon("21.PNG");
	imagenes[21]= new ImageIcon("22.PNG");
	imagenes[22]= new ImageIcon("23.PNG");
	imagenes[23]= new ImageIcon("24.PNG");
	imagenes[24]= new ImageIcon("25.PNG");
	imagenes[25]= new ImageIcon("26.PNG");
	imagenes[26]= new ImageIcon("27.PNG");
	imagenes[27]= new ImageIcon("28.PNG");
	
	iniciar = new JButton ("Presentacion");
	atras = new JButton ("Anterior");
	adelante = new JButton ("Siguiente");
	
	izq = new JPanel();
    izq.setLayout(new GridLayout(1,5));
	izq.add(atras);
	
	aux = new JLabel();
	
	for(i=0; i<28; i++){
			botones[i]= new JButton(""+(i+1));
			botones[i].setBounds(0,0,75,60);
			icono = new ImageIcon(imagenes[i].getImage().getScaledInstance(botones[i].getWidth(), botones[i].getHeight(), Image.SCALE_DEFAULT));
			botones[i].setIcon(icono);
			botones[i].setBackground(java.awt.Color.white);
			Superior.add(botones[i]);
			botones[i].addActionListener(this);
			comentario[i] = new JLabel("");
		}
	
	Inferior = new JPanel();
	//Inferior.setBounds(new Rectangle(0, 0, 550, 340));
	Inferior.setLayout(null);
	Dimension d = new Dimension(550,340);
	Inferior.setPreferredSize(d);
	//Inferior.setLayout(new FlowLayout(FlowLayout.LEFT));
	Inferior.setVisible(true);
	i = 0; x = 0;
	icono = new ImageIcon(imagenes[i].getImage().getScaledInstance(550, 340,0));
	foto = new JLabel();	
	foto.setIcon(icono);
	Inferior.add(foto);
	
	der = new JPanel();
    der.setLayout(new GridLayout(1,5));
	der.add(adelante);
	
	
	Botonera = new JPanel();
    Botonera.setLayout(new GridLayout(1,5));
	
	Botonera.add(iniciar);
	atras.addActionListener(this);
	adelante.addActionListener(this);
	iniciar.addActionListener(this);
	
	abajo = new JPanel();
    abajo.setLayout(new GridLayout(1,1));
	abajo.add(aux);
	
	Botonera.add(slider);
	
	slider.addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
            convertido = slider.getValue();
			if(convertido<10)
				convertido=9;
			zoomcito();
        }
    });
         
	
	Botonera.add(come);
	Botonera.add(comentar);
	Botonera.add(eli_com);
	comentar.addActionListener(this);
	eli_com.addActionListener(this);
	
	//Superior.setSize(900,300);
	//Inferior.setSize(550,340);
	foto.setSize(550,340);
	Botonera.setSize(900,200);
	izq.setSize(350,340);
	der.setSize(350,340);
	abajo.setSize(850,100);
	//Inferior.setBounds(0,0,550,340);
	
	c.add(Superior); c.add(izq); c.add(Inferior);c.add(der);c.add(abajo);c.add(Botonera);
		
	setSize(900, 730); setVisible(true);
	
	}
	
	public void actionPerformed(ActionEvent e) {
		
		JButton btn = (JButton)e.getSource();
		
		if(btn==comentar)
		{
			comentario[i].setText(come.getText());
			adelantarfoto();
			atrasarfoto();
		}
		if(btn==eli_com)
		{
			comentario[i].setText("");
			adelantarfoto();
			atrasarfoto();
		}
		if (btn==adelante)
		{
			adelantarfoto();
		}
		if (btn==atras)
		{
			atrasarfoto();
		}
		if (btn==iniciar)
		{
			x++;
			if(x%2==0)
			{
				t = 10000;
			}
			else
			{
				t = 1;
				if(x==1)
					cambio.start();
			}
			
		}
		else
		{
      		for(res=0;res<28;res++)
			{
				test = btn.getText().toString();
				convertido = Integer.parseInt(test);
				convertido--;
				if(convertido==res)
				{
					i=res;
					icono = new ImageIcon(imagenes[i].getImage().getScaledInstance(550, 340,0));
					foto.setIcon(icono);
					aux.setBounds(0,0,200,200);
					aux.setText("                                         "+comentario[i].getText());
				}
			}
    	}		
	}
	
	void adelantarfoto()
	{
		i++;
		if(i==29)
			i=0;
		icono = new ImageIcon(imagenes[i].getImage().getScaledInstance(550, 340,0));
		foto.setIcon(icono);
		aux.setText("                                         "+comentario[i].getText());
		
	}
	
	void atrasarfoto()
	{
		i--;
		if(i==-1)
			i=28;
		icono = new ImageIcon(imagenes[i].getImage().getScaledInstance(550, 340,0));
		foto.setIcon(icono);
		aux.setText("                                         "+comentario[i].getText());
	}
	
	public void run ()
	{
		while (true){
			
			try 
			{
				adelantarfoto();
				cambio.sleep(t * 1500);
			}catch (Exception e){e.getMessage();}
		
		
		}
	}
	
	void zoomcito()
	{
		icono = new ImageIcon(imagenes[i].getImage().getScaledInstance((50*convertido)/10, (340*convertido)/10,0));
		foto.setIcon(icono);
		//Inferior.setIcon(icono);
	}
	
	public static void main (String argv [])
	{
		new Album();
	}
}
