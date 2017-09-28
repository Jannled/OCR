package com.github.noahdi.ocr;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ComputerZeichnen extends JPanel
{
	int hoehe, breite, hoehE, breitE;
	//
	
	public ComputerZeichnen(int a, int b, int c, int d) {
    	
		hoehe = a;
		breite= b;
		hoehE = c;
		breitE= d;
    	this.setBounds(410, 100, breitE, hoehE);
    }
    @Override
    protected void paintComponent (Graphics g)
    {
        for(int i=0;i<hoehe;i++)
        {
        	for(int j=0;j<breite;j++)
        	{
        		//g.fillReck()
        	}
        }

        repaint();
        
    }
    
    public void zeichnen()
    {
    	Graphics h = getGraphics();
    	h.setColor(Color.red);
    	h.fillRect(100, 100, 10, 10);
    	repaint();
    }
    
}
