package com.github.jannled.ocr;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.github.noahdi.ocr.Interface_2;

public class Debugger extends JFrame
{
	private static final long serialVersionUID = -7578507167226329829L;
	
	private ANN ann;
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
		this.ann = Interface_2.intf.getANN();
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
	}
}