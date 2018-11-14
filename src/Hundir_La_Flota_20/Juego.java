package Hundir_La_Flota_20;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Juego extends JPanel{
	
	Tablero t_contrincante;
	Tablero t_jugador;

	Juego()
	{

		GridLayout layout = new GridLayout(2,1);
		setLayout(layout);
		
	
		
		//--TABLERO CONTRINCANTE------
		
		t_contrincante = new Tablero(false);
		add(t_contrincante);
				
		//--AÑADIR TABLERO JUGADOR------
				
		t_jugador = new Tablero(true);
		add(t_jugador);
		
		
	}
	

	
}
