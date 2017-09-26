package com.github.noahdi.ocr;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ComputerZeichnen extends JPanel
{
	
	//
	
	public ComputerZeichnen() {
    	
		
    	this.setBounds(720, 100, 100, 100);
    }
    @Override
    protected void paintComponent (Graphics g)
    {
        this.zeichnen();

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
