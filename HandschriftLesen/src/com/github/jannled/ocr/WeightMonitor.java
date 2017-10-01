package com.github.jannled.ocr;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.github.jannled.lib.Print;
import com.github.jannled.lib.math.Matrix;
import com.github.noahdi.ocr.Interface_2;

public class WeightMonitor extends JFrame 
{
	private static final long serialVersionUID = -7578507167226329829L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public WeightMonitor()
	{	
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new WeightPanel(Interface_2.intf.getANN());
		setContentPane(contentPane);
		Matrix[] data = Interface_2.intf.getANN().getWeights();
	}
}