package Hundir_La_Flota_20;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Tablero2_Copia extends JPanel{
	
	Boton[] boton = new Boton [105];
	JLabel[] letra = new JLabel[11];
	JLabel[] numero = new JLabel[10];
	
	//------------------------------
	
	Boton brotacion = new Boton("");
	int rotacion = 1;

	Tablero2_Copia(boolean jugador)
	{
		//--CARACTERISTICAS DEL TABLERO
		GridLayout layout = new GridLayout(11,11);
		setLayout(layout);
		
		setBorder(new EmptyBorder(25,5,25,25));
		
		//AÑADIR LISTENER AL TABLERO PARA QUE AL PULSAR R SE CAMBIE LA ROTACION
		
		//--CREAR TABLERO
		crearTablero();
	}
	
	//----METODOS----
	
	public void crearTablero()
	{
		//-----CREAR LAS LETRAS
		for(int x = 0;x<11;x++)
		{
			switch(x)
			{
				case 0: letra[x] = new JLabel("", SwingConstants.CENTER);break;
				case 1: letra[x] = new JLabel("A", SwingConstants.CENTER);break;
				case 2: letra[x] = new JLabel("B", SwingConstants.CENTER);break;
				case 3: letra[x] = new JLabel("C", SwingConstants.CENTER);break;
				case 4: letra[x] = new JLabel("D", SwingConstants.CENTER);break;
				case 5: letra[x] = new JLabel("E", SwingConstants.CENTER);break;
				case 6: letra[x] = new JLabel("F", SwingConstants.CENTER);break;
				case 7: letra[x] = new JLabel("G", SwingConstants.CENTER);break;
				case 8: letra[x] = new JLabel("H", SwingConstants.CENTER);break;
				case 9: letra[x] = new JLabel("I", SwingConstants.CENTER);break;
				case 10: letra[x] = new JLabel("J", SwingConstants.CENTER);break;
			}
						
			add(letra[x]);
		}
		
		//------CREAR LOS NUMEROS Y BOTONES
		int contador = 0;
		
		for(int x = 0;x<100;x++)
		{
			if(x%10==0 && x!=99)
			{
				numero[contador] = new JLabel(""+(contador+1), SwingConstants.CENTER);
				add(numero[contador]);
				contador++;
			}
			
				boton[x] = new Boton(""+x);
				boton[x].setSize(25, 25);

				add(boton[x]);
		}
	}
	

	public void anadirBarco3()
	{
		for(int x = 0; x<100; x++)
		{
			boton[x].addMouseListener(new EanadirBarco());
			boton[x].addKeyListener(new EcambiarRotacion());
		}	
	}

	//--------CLASES INTERNAS
	
	
	class EanadirBarco implements MouseListener
	{
		//---METODOS PROPIOS----
		public void barcosHorizontal(Boton objeto)
		{			
			for(int x=0;x<100;x++)
			{
				
				int linea = x/10;
				linea = linea * 10;
						
				int numero = x - linea;
				
				if(objeto==boton[x] && numero<=7)
				{
					boton[x].setColorEleccionVerde();
						boton[x].setBorder(
						BorderFactory.createMatteBorder(2, 2, 2, 0, Color.black));
						
					boton[x+1].setColorEleccionVerde();
						boton[x+1].setBorder(
						BorderFactory.createMatteBorder(2, 0, 2, 0, Color.black));
						
					boton[x+2].setColorEleccionVerde();
						boton[x+2].setBorder(
						BorderFactory.createMatteBorder(2, 0, 2, 2, Color.black));
				}
				else if(objeto==boton[x] && numero==8)
				{
					boton[x].setColorEleccionRojo();
						boton[x].setBorder(
						BorderFactory.createMatteBorder(2, 2, 2, 0, Color.black));
					
					boton[x+1].setColorEleccionRojo();
						boton[x+1].setBorder(
						BorderFactory.createMatteBorder(2, 0, 2, 2, Color.black));
				}
				else if(objeto==boton[x] && numero==9)
				{
					boton[x].setColorEleccionRojo();
						boton[x].setBorder(
						BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
				}	
			}
		}
		
		
		public void barcosVertical(Boton objeto)
		{
			for(int x=0;x<100;x++)
			{
				int linea = x/10;
				linea = linea * 10;		
				
				if(objeto==boton[x] && linea>=3)
				{
					boton[x].setColorEleccionVerde();
						boton[x].setBorder(
						BorderFactory.createMatteBorder(0, 2, 2, 2, Color.black));
						
					boton[x-10].setColorEleccionVerde();
						boton[x-10].setBorder(
						BorderFactory.createMatteBorder(0, 2, 0, 2, Color.black));
						
					boton[x-20].setColorEleccionVerde();
						boton[x-20].setBorder(
						BorderFactory.createMatteBorder(2, 2, 0, 2, Color.black));
				}
				else if(objeto==boton[x] && linea==2)
				{
					boton[x].setColorEleccionRojo();
						boton[x].setBorder(
						BorderFactory.createMatteBorder(2, 2, 2, 0, Color.black));
					
					boton[x-10].setColorEleccionRojo();
						boton[x-10].setBorder(
						BorderFactory.createMatteBorder(2, 0, 2, 2, Color.black));
				}
				else if(objeto==boton[x] && linea==1)
				{
					boton[x].setColorEleccionRojo();
						boton[x].setBorder(
						BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
				}
					
			}
		}
		
		
		public void barcosHorizontalRojo(Boton objeto)
		{		
			for(int x=0;x<100;x++)
			{
				int linea = x/10;
				linea = linea * 10 + 10;
				
				if(objeto==boton[x])
				{
					boton[x].setBackground(new JButton().getBackground());	
						boton[x].setBorder(new JButton().getBorder());
						
					boton[x+1].setBackground(new JButton().getBackground());
						boton[x+1].setBorder(new JButton().getBorder());
						
					boton[x+2].setBackground(new JButton().getBackground());
						boton[x+2].setBorder(new JButton().getBorder());
				}
					
			}

			
		}
		
		
		public void barcosVerticalRojo(Boton objeto)
		{
			for(int x=0;x<100;x++)
			{	
				int linea = x/10;
				linea = linea * 10 + 10;
				
				if(objeto==boton[x])
				{
					boton[x].setBackground(new JButton().getBackground());	
						boton[x].setBorder(new JButton().getBorder());
						
					boton[x-10].setBackground(new JButton().getBackground());
						boton[x-10].setBorder(new JButton().getBorder());
						
					boton[x-20].setBackground(new JButton().getBackground());
						boton[x-20].setBorder(new JButton().getBorder());
				}	
			}	
		}
		
		//--------------------------	

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		
			Boton objeto = (Boton) e.getSource();
			
			if(rotacion == 0)
			{
			barcosHorizontal(objeto);
			}
			else if(rotacion == 1)
			{
			barcosVertical(objeto);
			}	
			
		}

		@Override //---ESTE ES EL METODO QUE DA PROBLEMAS AL SALIR DEL ARRAY
		public void mouseExited(MouseEvent e) {

			Boton objeto = (Boton) e.getSource();

			if(rotacion == 0)
			{
			barcosHorizontalRojo(objeto);
			}
			else if(rotacion == 1)
			{
			barcosVerticalRojo(objeto);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

	}
	
	////---------------------------------------------------------------------------
	////---------------------------------------------------------------------------
	////---------------------------------------------------------------------------
	
	class EcambiarRotacion extends EanadirBarco implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
			for(int x = 0; x<100; x++)
			{
				boton[x].setBackground(new JButton().getBackground());
				boton[x].setBorder(new JButton().getBorder());
			}
			
			if(rotacion == 0)
			{
				rotacion = 1;
				barcosVertical((Boton)e.getSource());
			}
			else if (rotacion == 1)
			{
				rotacion = 0;
				barcosHorizontal((Boton)e.getSource());
			}
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
		}
		
	}
	
	
	
}
	
