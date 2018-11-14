package Hundir_La_Flota_20;

import java.awt.Color;
import java.awt.Font;
import java.awt.Robot;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Uso {
	
		private static Ventana juego = new Ventana();
	
		public static Tablero t_jugador = juego.juego.t_jugador;
		
		private static Tablero t_contrincante = juego.juego.t_contrincante;
	
		private static Texto texto = juego.texto;
		
		private static int[] barco_colocado = new int[5];
		
		private static int bucle = 1;
		
		private static JLabel titulo;


	public static void main(String[] args) throws InterruptedException {
		

		//------------------------------------------------------
		
		t_jugador.setBorder(new EmptyBorder(0,5,0,25));
		t_contrincante.rotar.setVisible(false);
		
		//-------EL JUGADOR COLOCA LOS 5 BARCOS------------------
		
		colocarBarco(t_jugador, 3,"Coloca el barco de 3 casillas.");
		
		colocarBarco(t_jugador, 3,"Coloca otro barco de 3 casillas.");
		
		colocarBarco(t_jugador, 4,"Coloca el barco de 4 casillas.");
		
		colocarBarco(t_jugador, 4,"Coloca otro barco de 4 casillas");
		
		colocarBarco(t_jugador, 5,"Coloca el barco de 5 casillas");
		
		//AHORA EL PROGRAMA COLOCA LOS BARCOS EN EL TABLERO DEL CONTRINCANTE
		
		colocarBarcoAleatorio(t_contrincante,3,1);
		
		colocarBarcoAleatorio(t_contrincante,3,2);
			
		colocarBarcoAleatorio(t_contrincante,4,3);
		
		colocarBarcoAleatorio(t_contrincante,4,4);
		
		colocarBarcoAleatorio(t_contrincante,5,5);
		
		texto.setTamanoFuente(16.5f);
		texto.setTexto("Ahora, la I.A. ha colocado sus barcos y puedes comenzar a jugar.");
		
		//AHORA COMIENZA EL JUEGO Y PUEDES ELEGIR LAS CASILLAS
		
		while(bucle==1)
		{
			turno();
		}
		

	}
	
	
	//----METODOS---
	
	public static void colocarBarco(Tablero tablero, int n_barcos, String ttexto) throws InterruptedException
	{
				texto.setTexto(ttexto);

				tablero.anadirBarco(n_barcos);
				
				while(tablero.proceso==1)
				{
					Thread.sleep(1);
				}
		
	}
		
	public static void colocarBarcoAleatorio(Tablero tablero, int n_barcos, int barco_contador)
	{
		
		int aleatorio = (int) (Math.random()*2+1);
		
		if(aleatorio==1)
		{
			colocarBarcoAleatorioHorizontal(tablero,n_barcos,barco_contador);
		}
		else
		{
			colocarBarcoAleatorioVertical(tablero,n_barcos,barco_contador);
		}
	
		
	}
	
	public static void colocarBarcoAleatorioHorizontal(Tablero tablero, int n_barcos, int barco_contador)
	{
		int aleatorio = (int) (Math.random()*99+1);
		boolean valido = true;
		int bucle = 1;
		
		while(bucle == 1)
		{
			valido = true;
			
			aleatorio = (int) (Math.random()*99+1);
			
				for(int i = 0; i<5;i++)
				{
					if(barco_colocado[i]==aleatorio)
					{
						valido=false;
					}
				}
				
				if(aleatorio%10>10-n_barcos)
				{
					valido = false;
				}
				
				if(tablero.boton[aleatorio].getActivo())
				{
					valido = false;
				}
				
				
				if(tablero.anadirBarcoHorizontal(tablero.boton[aleatorio], n_barcos, barco_contador)==true && valido)//la coordenada 0 da problemas y multiplica los barcos
				{
			
					
					for(int x = 0; x<100;x++)
					{
						
							if(tablero.boton[x].getIluminado())
							{
								tablero.boton[x].setActivo(true);
								//tablero.boton[x].setColorActivo();
								tablero.boton[x].setColorDefault();
								tablero.boton[x].setIdBarco(barco_contador);
								//tablero.boton[x].setText(""+barco_contador);
								tablero.boton[x].setIluminado(false);
							}
						
					}
					
					
					barco_colocado[barco_contador-1] = aleatorio;
					bucle = 0;
				}
				else
				{
					
					
				}
			
			
		}
		
		tablero.barcosHorizontalBorrar(null);
		
	}
	
	public static void colocarBarcoAleatorioVertical(Tablero tablero, int n_barcos, int barco_contador)
	{
		int aleatorio = (int) (Math.random()*99+1);
		boolean valido = true;
		int bucle = 1;
		
		while(bucle == 1)
		{
			
			aleatorio = (int) (Math.random()*99+1);
			
				for(int i = 0; i<5;i++)
				{
					if(barco_colocado[i]==aleatorio)
					{
						valido=false;
					}
				}
				
				if(aleatorio/10<n_barcos-1)
				{
					valido = false;
				}
				
				if(tablero.boton[aleatorio].getActivo())
				{
					valido = false;
				}
				
				
				
				if(tablero.anadirBarcoVertical(tablero.boton[aleatorio], n_barcos, barco_contador)==true && valido)
				{
					
					boolean valido2 = tablero.anadirBarcoVertical(tablero.boton[aleatorio], n_barcos, barco_contador);
					
					

					for(int x = 0; x<100;x++)
					{
						
						int contador_interno = 0;
						
						if(contador_interno<n_barcos)
						
							if(tablero.boton[x].getIluminado())
							{
								tablero.boton[x].setActivo(true);
								//tablero.boton[x].setColorActivo();
								tablero.boton[x].setColorDefault();
								tablero.boton[x].setIdBarco(barco_contador);
								//tablero.boton[x].setText(""+barco_contador);
								tablero.boton[x].setIluminado(false);
								
								contador_interno++;
							}
						
					}
					
					
					barco_colocado[barco_contador-1] = aleatorio;
					bucle = 0;
					valido = false;
				}
				else
				{
					valido = true;
				}
			
			
		}
		
		tablero.barcosHorizontalBorrar(null);
		
	}

	public static void turno() throws InterruptedException
	{
		int contador1 = 0;
		int contador2 = 0;
		
		
		
		
		
		t_contrincante.elegirCasilla(-1);
		
		while(t_contrincante.proceso==1)
		{
			Thread.sleep(1);
		}
		
		//-------------------------------------------------------------
		
		for(int x = 0; x<100;x++)
		{
			if(t_contrincante.boton[x].getActivo())
			{
				contador1++;
			}
			if(t_contrincante.boton[x].getTocado())
			{
				contador2++;
			}
		}
		
		System.out.println("contador1:"+contador1);
		System.out.println("contador2:"+contador2);
		
		if(contador1==contador2)
		{
			texto.setForeground(Color.GREEN);
			texto.setTexto("HAS GANADO!!!!");
			bucle = 0;
			
		}
		
		//--------------------------------------------------------------
		
		if(contador1!=contador2)
		{
			contador1 =0;
			contador2 = 0;
			
			texto.setTexto("La I.A. está eligiendo la casilla para atacar.");
			
			Thread.sleep((int)(Math.random()*3000+500));
			
			int aleatorio = (int) (Math.random()*100);
			
			t_jugador.elegirCasilla(aleatorio);
			
			for(int x = 0; x<100;x++)
			{
				if(t_jugador.boton[x].getActivo())
				{
					contador1++;
				}
				if(t_jugador.boton[x].getHundido())
				{
					contador2++;
				}
			}
			
			if(contador1==contador2)
			{
				texto.setForeground(Color.RED);
				bucle = 0;
				texto.setTexto("HAS PERDIDO!!!!");
				
			}
			else
			{

				texto.setTexto("La I.A. ha atacado. Es tu turno.");
			}
			
		}
		
	}
}
