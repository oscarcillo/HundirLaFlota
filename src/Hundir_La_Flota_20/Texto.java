package Hundir_La_Flota_20;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Texto extends JPanel{
	
	JLabel texto = new JLabel("HUNDIR LA FLOTA");

	Texto()
	{
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 25, 0, new JPanel().getBackground()));
		
		add(texto);
		texto.setFont(this.getFont().deriveFont(17.0f));
	}
	
	//--METODOS---
	
	public void setTexto(String texto)
	{
		this.texto.setText(texto);
	}
	
	public void setTamanoFuente(float numero)
	{
		this.texto.setFont(this.texto.getFont().deriveFont(numero));
	}
	
}
