package com.github.jannled.ocr;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class WeightMonitor extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {

	}

	/**
	 * Create the frame.
	 */
	public WeightMonitor() 
	{
		openFrame();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	private void openFrame()
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WeightMonitor frame = new WeightMonitor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
