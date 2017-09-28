package com.github.jannled.ocr;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WeightMonitor extends JFrame 
{
	
	private static final long serialVersionUID = -7578507167226329829L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public WeightMonitor(ANN ann) 
	{	
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new WeightPanel(ann);
		setContentPane(contentPane);
	}
	
}

class WeightPanel extends JPanel
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
		g.drawString(ann.getWeights()[0].toString(), 10, 10);
	}
}
