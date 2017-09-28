package com.github.jannled.ocr;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.github.noahdi.ocr.Interface_2;

public class WeightMonitor extends JFrame 
{
	private static final long serialVersionUID = -7578507167226329829L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public WeightMonitor(Interface_2 buhu)
	{	
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new WeightPanel(buhu.getANN());
		setContentPane(contentPane);
	}
}