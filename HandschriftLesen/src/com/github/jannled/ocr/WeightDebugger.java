package com.github.jannled.ocr;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.github.jannled.lib.math.Matrix;

public class WeightDebugger 
{
	boolean isVisible;
	JFrame frame;
	WeightMonitor monitor;
	
	public WeightDebugger(Matrix[] weights)
	{
		frame = new JFrame("ANN Weights");
		monitor = new WeightMonitor(weights);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.add(monitor);
		monitor.repaint();
		show(true);
	}
	
	public void show(boolean show)
	{
		this.isVisible = show;
		frame.setVisible(show);
	}

	public boolean isVisible() 
	{
		return isVisible;
	}
}

class WeightMonitor extends JPanel
{
	private static final long serialVersionUID = -7130325169778360063L;
	Matrix[] weights;
	
	public WeightMonitor(Matrix[] weights) 
	{
		this.weights = weights;
	}
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		String s = weights[0].toString();
		g.drawString("Hallo Welt", 10, 10);
	}
}
