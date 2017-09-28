package com.github.noahdi.ocr;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ComputerZeichnen extends JPanel
{
	int hoehe, breite, hoehE, breitE;
	Interface_2 intf;
	//
	
	public ComputerZeichnen(int a, int b, int c, int d, Interface_2 buhu) 
	{	
		intf = buhu;
    	
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
        		g.setColor(new Color(0,255-intf.getF(i,j),0));
        		if(intf.getF(i,j)==0)
        		{
        			g.setColor(Color.WHITE);
        		}
        		g.fillRect(j*(breitE/breite), i*(hoehE/hoehe),(breitE/breite),(hoehE/hoehe));
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
