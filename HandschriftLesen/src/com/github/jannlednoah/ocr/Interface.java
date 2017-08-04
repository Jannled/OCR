package com.github.jannlednoah.ocr;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Interface extends JFrame 
{
	private static final long serialVersionUID = -9085778293331266060L;
	
	private JPanel contentPane;
	JButton feld[][] = new JButton[5][6];
	double felder[] = new double[30];
	double feldI[] = new double[30];
	boolean feldB[] = new boolean[30];
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface frame = new Interface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Interface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Willkommen bei Janniks und Noahs KI");
		lblNewLabel.setBackground(Color.YELLOW);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		lblNewLabel.setBounds(100, 50, 500, 75);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("");
				System.out.println("");
				
				for(int i=0; i<6; i++)
				{
					for(int j=0; j<5; j++)
					{
						System.out.print(" "+feldI[i*5+j]);
					}
					System.out.println("");
				}
			}
		});
		btnNewButton.setBounds(97, 449, 89, 23);
		contentPane.add(btnNewButton);
		
		
		for(int i=0; i<6; i++)
		{
			for(int j=0; j<5; j++)
			{
				feld[j][i] = new JButton("");
				feld[j][i].setBounds(100+j*30, 150+i*30, 30, 30);
				contentPane.add(feld[j][i]);
				evt(j,i);
			}
		}
		
		
	}
	
	public void evt(int a, int b)
	{
		feld[a][b].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					if(feldB[b+a*5]==false)
					{
						feldI[b+a*5]= 0.5;
						feld[a][b].setBackground(Color.GREEN);
						feldB[b+a*5]= true;
					}
					else
					{
						feldI[b+a*5]= -0.5;
						feld[a][b].setBackground(Color.WHITE);
						feldB[b+a*5]= false;
					}
					
				}catch(Exception y)
				{
					System.out.println("Kevin");
				}
			}
		});
	}
}
