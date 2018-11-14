package Hundir_La_Flota_20;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Tablero extends JPanel{
	
	Boton[] boton = new Boton [105];
	JLabel[] letra = new JLabel[11];
	JLabel[] numero = new JLabel[10];
	
	JButton rotar;
	
	JLabel titulo_visible;
	
	//------------------------------
	
	int rotacion = 0;
	int proceso = 0; //--0 = COLOCACION DE BARCO NO INICIADA // 1 = COLOCANDO BARCOS // 2 = PROCESO TERMINADO
	boolean es_posible_colocar = true; //indicar si es posible o no colocar el barco
	int contador_barco = 1;
	int barcos_hundidos = 0;
	
	//------------------------------
	
	EanadirBarco e1 = new EanadirBarco();
	EcambiarRotacion e2 = new EcambiarRotacion();
	EelegirCasilla e3 = new EelegirCasilla();

	Tablero(boolean jugador)
	{
		
		//--CARACTERISTICAS DEL TABLERO
		GridLayout layout = new GridLayout(12,11);
		setLayout(layout);
		
		setBorder(new EmptyBorder(15,5,0,25));
		
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
			
				boton[x] = new Boton("");
				boton[x].setSize(25, 25);
				
			
				add(boton[x]);
			
		}
		
		JPanel panelrotar = new JPanel();
		this.add(panelrotar);
		panelrotar.setLayout(new FlowLayout());
		
		rotar = new JButton("Rotar");
		
		rotar.addActionListener(new EcambiarRotacion());
		rotar.setBackground(Color.cyan);
	
		panelrotar.add(rotar);
	
	}


	public void anadirBarco(int n_barcos)
	{
		proceso = 1;
		
		for(int x = 0; x<100; x++)
		{
			
			e1.setN_barcos(n_barcos);
			boton[x].addMouseListener(e1);
			
			boton[x].addKeyListener(e2);
			
		}
		
	}
	
	public void terminarAnadirBarco()
	{
		for(int x = 0; x<100; x++)
		{
			EanadirBarco e = new EanadirBarco();
			boton[x].removeMouseListener(e1);
			
			boton[x].removeKeyListener(e2);
			
		}
		proceso = 2;
	}
	
	public boolean anadirBarcoHorizontal(Boton objeto, int n_barcos, int barco_contador)
	{
		int bandera = 0;
		
		for(int x=0;x<100;x++)
		{
			
			int linea = x/10;
			linea = linea * 10;
					
			int numero = x - linea;
			
			int contador = 0;
			
		if(objeto==boton[x] && !boton[x].getActivo())
		{
			
			
			if(numero<=10-n_barcos)
			{
				for(int y=0;y<=10-n_barcos;y++)
				{
					
							if(objeto==boton[x] && numero==y)
							{
								
								for(int w = 0;w<n_barcos;w++)
								{
									
									if(contador==0)
									{
										if(!boton[x+contador].getActivo())
										{
											boton[x+contador].setColorEleccionVerde();
											boton[x+contador].setIluminado(true);
											boton[x+contador].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 0, Color.black));	
										}
										else
										{
											bandera++;
										}
										
									}
									else if(contador==n_barcos-1)
									{
										if(!boton[x+contador].getActivo())
										{
											boton[x+contador].setColorEleccionVerde();
											boton[x+contador].setIluminado(true);
											boton[x+contador].setBorder(BorderFactory.createMatteBorder(2, 0, 2, 2, Color.black));	
										}
										else
										{
											bandera++;
										}
									}
									else
									{
										if(!boton[x+contador].getActivo())
										{
											boton[x+contador].setColorEleccionVerde();
											boton[x+contador].setIluminado(true);
											boton[x+contador].setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.black));	
										}
										else
										{
											bandera++;
										}
									}
									
									
									contador++;
								}
								
							}
					
				}
			}
			
			
		}

				
		}
		
		

		if(bandera==0)
		{
			es_posible_colocar = true;
		}
		else
		{
			es_posible_colocar = false;
		}
		
		if(es_posible_colocar)
		{
		
			//contador_barco++;
			return true;
		}
		else
		{
			
			for(int x = 0; x<100;x++)
			{
				
					if(boton[x].getIluminado())
					{
						boton[x].setIluminado(false);
					}
				
			}
			
			
			
			return false;
		}
		
	}
	
	public boolean anadirBarcoVertical(Boton objeto, int n_barcos, int barco_contador)
	{
		int bandera = 0;
		
		for(int x=0;x<100;x++)
		{
			
			int linea = x/10;
					
			int contador = 0;
			
			if(linea>=n_barcos-1)
			{
				for(int y=n_barcos-1;y<10;y++)
				{
					
							if(objeto==boton[x] && linea==y)
							{
								
								for(int w = 0;w<n_barcos;w++)
								{
								
									if(contador==0)
									{
										if(!boton[x-contador].getActivo())
										{
											boton[x-contador].setColorEleccionVerde();
											boton[x-contador].setIluminado(true);
											boton[x-contador].setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, Color.black));	
										}
										else
										{
											bandera++;
										}
									}
									else if(contador/10==n_barcos-1)
									{
										if(!boton[x-contador].getActivo())
										{
											boton[x-contador].setColorEleccionVerde();
											boton[x-contador].setIluminado(true);
											boton[x-contador].setBorder(BorderFactory.createMatteBorder(2, 2, 0, 2, Color.black));	
										}
										else
										{
											bandera++;
										}
									}
									else
									{
										if(!boton[x-contador].getActivo())
										{
											boton[x-contador].setColorEleccionVerde();
											boton[x-contador].setIluminado(true);
											boton[x-contador].setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2, Color.black));
										}
										else
										{
											bandera++;
										}
									}
									
									
									
									contador+=10;
								}
								
							}
					
				}
			
			
			}
		}
		
		
		

		if(bandera==0)
		{
			es_posible_colocar = true;
		}
		else
		{
			es_posible_colocar = false;
		}
		
		if(es_posible_colocar)
		{
		
			//contador_barco++;
			return true;
		}
		else
		{
			
			for(int x = 0; x<100;x++)
			{
				
					if(boton[x].getIluminado())
					{
						boton[x].setIluminado(false);
					}
				
			}
			
			
			
			return false;
		}
		
		
	}
	
	public void barcosHorizontalBorrar(Boton objeto)
	{
		
		for(int x=0;x<100;x++)
		{
			
			if(!boton[x].getActivo())
			{
			boton[x].setBackground(new JButton().getBackground());
			boton[x].setBorder(new JButton().getBorder());
			}
				
		}

		
	}
	
	public void elegirCasilla(int casilla)
	{
		proceso = 1;
		
		if(casilla > -1)
		{
			
				if(boton[casilla].getActivo()) //comprobar si hay barco en esa casilla
				{
					boton[casilla].setColorTocado();
					boton[casilla].setTocado(true);
				}
				else if(!boton[casilla].getActivo()) //comprobar si no hay barco en esa casilla
				{
					boton[casilla].setColorAgua();
				}
				
				terminarElegirCasilla();
		}
		else
		{
				for(int x = 0; x<100; x++)
				{
					
					boton[x].addMouseListener(e3);
					
				}
		}
		
		
	}
	
	public void terminarElegirCasilla()
	{
		int hundir []=  new int [5];
		hundir[0]=0;
		hundir[1]=0;
		hundir[2]=0;
		hundir[3]=0;
		hundir[4]=0;
		
		for(int x = 0; x<100; x++)
		{
			
			boton[x].removeMouseListener(e3);
			
			for(int i = 0; i<5; i++)
			{
				if(boton[x].getIdBarco()==i+1)
				{
					if(boton[x].getTocado())
					{
						
					}
					else
					{
						hundir[i]++;
					}
					
				}
			}
			
		}
		
		//-----------------------------
		
		for(int i = 0; i<5;i++)
		{
			if(hundir[i]==0)
			{
				for(int x = 0; x<100; x++)
				{
					if(boton[x].getIdBarco()==i+1)
					{
						boton[x].setColorHundido();
						boton[x].setHundido(true);
						barcos_hundidos++;
						boton[x].setText(""+boton[x].getIdBarco());
					}
				}
			}
		}
		
		//----------------------------------
		
		
		proceso = 2;
		
	}
	
	
	//--------CLASES INTERNAS
	
	
	
	
	class EanadirBarco implements MouseListener
	{
		
		int n_barcos = 0;

		
		//---METODOS PROPIOS----
		
		public void barcosHorizontal(Boton objeto)
		{
			
			int bandera = 0;
			
			for(int x=0;x<100;x++)
			{
				
				int linea = x/10;
				linea = linea * 10;
						
				int numero = x - linea;
				
				int contador = 0;
				
			if(objeto==boton[x] && !boton[x].getActivo())
			{
				
				
				if(numero<=10-n_barcos)
				{
					for(int y=0;y<=10-n_barcos;y++)
					{
						
								if(objeto==boton[x] && numero==y)
								{
									
									for(int w = 0;w<n_barcos;w++)
									{
										
										if(contador==0)
										{
											if(!boton[x+contador].getActivo())
											{
												boton[x+contador].setColorEleccionVerde();
												boton[x+contador].setIluminado(true);
												boton[x+contador].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 0, Color.black));	
											}
											else
											{
												bandera++;
											}
											
										}
										else if(contador==n_barcos-1)
										{
											if(!boton[x+contador].getActivo())
											{
												boton[x+contador].setColorEleccionVerde();
												boton[x+contador].setIluminado(true);
												boton[x+contador].setBorder(BorderFactory.createMatteBorder(2, 0, 2, 2, Color.black));	
											}
											else
											{
												bandera++;
											}
										}
										else
										{
											if(!boton[x+contador].getActivo())
											{
												boton[x+contador].setColorEleccionVerde();
												boton[x+contador].setIluminado(true);
												boton[x+contador].setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.black));	
											}
											else
											{
												bandera++;
											}
										}
										
										if(bandera==0)
										{
											es_posible_colocar = true;
										}
										else
										{
											es_posible_colocar = false;
										}
										
										contador++;
									}
									
								}
						
					}
				}
				else if(numero>10-n_barcos)
				{
					
					es_posible_colocar = false;
					
					for(int y=11-n_barcos;y<10;y++)
					{
						
								if(objeto==boton[x] && numero==y)
								{
									
									for(int w = 0;w<10-y;w++)
									{
										
										if(contador==0)
										{
											if(!boton[x+contador].getActivo())
											{
												boton[x+contador].setColorEleccionRojo();
												boton[x+contador].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 0, Color.black));	
											}
											
										}
										else if(contador==n_barcos-2)
										{
											if(!boton[x+contador].getActivo())
											{
												boton[x+contador].setColorEleccionRojo();
												boton[x+contador].setBorder(BorderFactory.createMatteBorder(2, 0, 2, 2, Color.black));		
											}
										
										}
										else
										{
											if(!boton[x+contador].getActivo())
											{
												boton[x+contador].setColorEleccionRojo();
												boton[x+contador].setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.black));	
											}
										
											
										}
									
										contador++;
									}
									
								}
						
					}
				}
				
				
			}
	
					
			}
			
			
			
			
		}
		
		
		public void barcosVertical(Boton objeto)
		{
			
			int bandera = 0;
			
			for(int x=0;x<100;x++)
			{
				
				int linea = x/10;
						
				int contador = 0;
				
				if(linea>=n_barcos-1)
				{
					for(int y=n_barcos-1;y<10;y++)
					{
						
								if(objeto==boton[x] && linea==y)
								{
									
									for(int w = 0;w<n_barcos;w++)
									{
									
										if(contador==0)
										{
											if(!boton[x-contador].getActivo())
											{
												boton[x-contador].setColorEleccionVerde();
												boton[x-contador].setIluminado(true);
												boton[x-contador].setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, Color.black));	
											}
											else
											{
												bandera++;
											}
										}
										else if(contador/10==n_barcos-1)
										{
											if(!boton[x-contador].getActivo())
											{
												boton[x-contador].setColorEleccionVerde();
												boton[x-contador].setIluminado(true);
												boton[x-contador].setBorder(BorderFactory.createMatteBorder(2, 2, 0, 2, Color.black));	
											}
											else
											{
												bandera++;
											}
										}
										else
										{
											if(!boton[x-contador].getActivo())
											{
												boton[x-contador].setColorEleccionVerde();
												boton[x-contador].setIluminado(true);
												boton[x-contador].setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2, Color.black));
											}
											else
											{
												bandera++;
											}
										}
										
										if(bandera==0)
										{
											es_posible_colocar = true;
										}
										else
										{
											es_posible_colocar = false;
										}
										
										contador+=10;
									}
									
								}
						
					}
				}
				else if(linea<n_barcos)
				{
					es_posible_colocar = false;
					
					for(int y=n_barcos;y>=0;y--)
					{
						
								if(objeto==boton[x] && linea==y)
								{
									
									for(int w = y;w>=0;w--)
									{
										
										if(contador==0)
										{
											if(!boton[x-contador].getActivo())
											{
												boton[x-contador].setColorEleccionRojo();
												boton[x-contador].setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, Color.black));
											}
										}
										else if(linea==1)
										{
											if(!boton[x-contador].getActivo())
											{
												boton[x-contador].setColorEleccionRojo();
												boton[x-contador].setBorder(BorderFactory.createMatteBorder(2, 2, 0, 2, Color.black));	
											}
										}
										else
										{
											if(!boton[x-contador].getActivo())
											{
												boton[x-contador].setColorEleccionRojo();
												boton[x-contador].setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2, Color.black));	
											}
										}
										
										contador+=10;
									}
									
								}
						
					}
				}
			}
		}
		
		
		public void barcosHorizontalBorrar(Boton objeto)
		{
			
			for(int x=0;x<100;x++)
			{
				
				if(!boton[x].getActivo())
				{
				boton[x].setBackground(new JButton().getBackground());
				boton[x].setBorder(new JButton().getBorder());
				}
					
			}

			
		}
		
		
		public void barcosVerticalBorrar(Boton objeto)
		{
			
			for(int x=0;x<100;x++)
			{
				
				if(!boton[x].getActivo())
				{
				boton[x].setBackground(new JButton().getBackground());
				boton[x].setBorder(new JButton().getBorder());
				}
					
			}
			
		}

		
		//--------------------------
		
		

		@Override
		public void mouseClicked(MouseEvent arg0) 
		{
		
				if(es_posible_colocar)
				{
				
					for(int x = 0; x<100;x++)
					{
						
							if(boton[x].getIluminado())
							{
								boton[x].setActivo(true);
								boton[x].setColorActivo();
								boton[x].setIdBarco(contador_barco);
								//boton[x].setText(""+contador_barco);
							}
						
					}
		
					contador_barco++;
					terminarAnadirBarco();
									
				
			}
			
			

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
			barcosHorizontalBorrar(objeto);
			}
			else if(rotacion == 1)
			{
			barcosVerticalBorrar(objeto);
			}
			
			for(int x = 0; x<100;x++)
			{
				boton[x].iluminado=false;
			}
			
		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		
		public void setN_barcos(int barcos)
		{
			n_barcos = barcos;
		}
		
		
	}
	
	////---------------------------------------------------------------------------
	////---------------------------------------------------------------------------
	////---------------------------------------------------------------------------
	
	class EcambiarRotacion extends EanadirBarco implements KeyListener, ActionListener
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
				this.barcosHorizontalBorrar((Boton)e.getSource());
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

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			for(int x = 0; x<100; x++)
			{
				//this.barcosHorizontalBorrar((Boton)e.getSource());
			}
			
			if(rotacion == 0)
			{
				rotacion = 1;
				//barcosVertical((Boton)e.getSource());
			}
			else if (rotacion == 1)
			{
				rotacion = 0;
				//barcosHorizontal((Boton)e.getSource());
			}
			
		}
		
	}
	
	////---------------------------------------------------------------------------
	////---------------------------------------------------------------------------
	////---------------------------------------------------------------------------
	
	class EelegirCasilla implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
			Boton boton = (Boton) e.getSource();
			
			
				if(!(boton.getAgua() || boton.getTocado()))
				{
					boton.setColorSeleccion();
				}
				
			
			
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
			Boton boton = (Boton) e.getSource();
			
			
			if(!(boton.getAgua() || boton.getTocado()))
			{
				boton.setColorDefault();
			}
			
			
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
			Boton boton = (Boton) e.getSource();
			
			if(boton.getActivo()) //comprobar si hay barco en esa casilla
			{
				boton.setColorTocado();
				boton.setTocado(true);
			}
			else if(!boton.getActivo()) //comprobar si no hay barco en esa casilla
			{
				boton.setColorAgua();
				boton.setAgua(true);
			}
			
			terminarElegirCasilla();
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
	
