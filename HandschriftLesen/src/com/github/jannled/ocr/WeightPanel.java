package com.github.jannled.ocr;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.github.jannled.lib.math.Matrix;
import com.github.noahdi.ocr.Interface_2;

public class WeightPanel extends JPanel
{
	private static final long serialVersionUID = 1887140080210736456L;
	JPanel layers = new JPanel(new GridLayout(0, 2));
	private ANN ann;
	
	public WeightPanel(ANN ann) 
	{
		this.ann = ann;
		//TODO layers.;
		rebuild();
	}
	
	public void rebuild()
	{
		Matrix[] data = Interface_2.intf.getANN().getWeights();
		for(int i=0; i<data.length; i++)
		{
			new JButton("Layer: " + i+1);
		}
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		
	}
}

