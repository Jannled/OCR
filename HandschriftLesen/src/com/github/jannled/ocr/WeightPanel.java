package com.github.jannled.ocr;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.github.jannled.lib.math.Matrix;

public class WeightPanel extends JPanel
{
	private static final long serialVersionUID = 1887140080210736456L;
	private ANN ann;
	
	public WeightPanel(ANN ann) 
	{
		this.ann = ann;
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		g.setColor(Color.GREEN);
		g.drawString("Hallo Welt", 10, 10);
		//Matrix[] test = ann.getWeights();
		//System.out.println(test[0]);
	}
}

