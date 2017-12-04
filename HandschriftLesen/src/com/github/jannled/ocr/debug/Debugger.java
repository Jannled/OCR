package com.github.jannled.ocr.debug;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class Debugger extends JFrame
{
	private static final long serialVersionUID = -7578507167226329829L;
	
	private JTabbedPane modes;
	
	//Weight Monitor
	private WeightPanel pnlWeightMonitor;
	
	//Activation Monitor 
	private ActivationPanel pnlActivationMonitor; 
	
	/**
	 * Create the frame.
	 */
	public Debugger()
	{	
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		modes = new JTabbedPane();
		getContentPane().add(modes);
		
		pnlWeightMonitor = new WeightPanel();
		modes.addTab("Weights", pnlWeightMonitor);
		
		pnlActivationMonitor = new ActivationPanel();
		modes.addTab("Activation", pnlActivationMonitor);
	}
	
	public void updateData(boolean recalculateDeltas)
	{
		pnlWeightMonitor.update(recalculateDeltas);
		pnlActivationMonitor.update(recalculateDeltas);
	}
}