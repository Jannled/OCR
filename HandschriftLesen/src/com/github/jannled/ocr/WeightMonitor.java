package com.github.jannled.ocr;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class WeightMonitor extends JFrame 
{
	
	private static final long serialVersionUID = -7578507167226329829L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public WeightMonitor() 
	{	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
}
