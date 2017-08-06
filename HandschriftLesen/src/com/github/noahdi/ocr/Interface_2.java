package com.github.noahdi.ocr;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.github.jannled.lib.math.Matrix;
import com.github.jannlednoah.ocr.ann.Annone;
import javax.swing.JTextField;


public class Interface_2 
{

	private JFrame frame;
	JLabel zeichen[][] = new JLabel[6][5];
	boolean feldB[][] = new boolean[6][5];
	int feldI[][] = new int[6][5];
	int feldF[][] = new int[6][5];
	float feld[] = new float[30];
	float alpha[] = new float[26];
	float ja = 0.5f, nein = -0.5f;
	char alphabet[] = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	int min;
	boolean leer;
	FreihandZeichnen panel;
	Annone ann = new Annone(30, 30, 26);
	private JTextField textField;
	int sss;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface_2 window = new Interface_2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface_2() {
   
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.scrollbar);
		frame.setBounds(100, 100, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		panel = new FreihandZeichnen();
		
		for(int q=0;q<6;q++)
		{
			for(int w=0;w<5;w++)
			{
				zeichen[q][w] = new JLabel("");
				zeichen[q][w].setBounds(800+50*w, 100+50*q, 50, 50);
				zeichen[q][w].setOpaque(true);
				zeichen[q][w].setBackground(Color.WHITE);
				frame.getContentPane().add(zeichen[q][w]);
			}
		}
		
		for(int i=0;i<26;i++)
		{
			alpha[i]=-0.5f;
		}

		
		JButton btn0 = new JButton("Holen");
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					for(int i=0;i<6;i++)
					{
						for(int j=0;j<5;j++)
						{
							feldI[i][j]= panel.counter[i][j];
							if(feldI[i][j]>0)
							{
								leer = false;
								feld[i*5+j] = ja;
								feldB[i][j] = true;
							}else
							{
								feld[i*5+j] = nein;
								feldB[i][j] = false;
							}
						}
					}
					
					
				}catch(Exception ee)
				{
					
				}
			}
		});
		btn0.setBounds(410, 100, 250, 50);
		frame.getContentPane().add(btn0);
		
		panel.setBackground(new Color(51, 102, 102));
		panel.setBounds(100, 100, 250, 300);
		frame.getContentPane().add(panel);
		
		JButton btn1 = new JButton("Zeigen");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					
					System.out.println("");
					System.out.println("");
					Color temp;
					for(int i=0; i<6; i++)
					{
						for(int j=0; j<5; j++)
						{
							System.out.print(" "+feld[i*5+j]);
							//
							if(feldB[i][j]==true)
							{
								temp = new Color(0,(255-getF(i,j)),0);
								zeichen[i][j].setBackground(temp);
							}
						}
						System.out.println("");
					}
					
					for(int i=0; i<30; i++)
					{
						System.out.print(feld[i]+"");
					}
					
					
				}catch(Exception ee)
				{
					
				}
			}
		});
		btn1.setBounds(410, 161, 250, 50);
		frame.getContentPane().add(btn1);
		
		JButton btn2 = new JButton("Reset");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					reset();
					
				}catch(Exception ee)
				{
					
				}
			}
		});
		btn2.setBounds(410, 222, 250, 50);
		frame.getContentPane().add(btn2);
		
		
		JButton btnNewButton = new JButton("Lernen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					String tf;
					
					tf = textField.getText().toLowerCase();
					
					
					
					for(int w=0;w<26;w++)
					{
						if(tf.equals(alphabet[w]))
						{
							alpha[w]=0.5f;
							
						}
					}
					
					
					ann.backpropagate(new Matrix(feld, 5,6),new Matrix(alpha, 1, 26));
					
				}catch(Exception ee)
				{
					
					
				}
			}
		});
		btnNewButton.setBounds(410, 350, 250, 50);
		frame.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(410, 283, 250, 50);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Erkennen");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					
					
				}catch(Exception ee)
				{
					ann.forward(new Matrix(feld, 5,6));
				}
			}
		});
		btnNewButton_1.setBounds(410, 411, 250, 50);
		frame.getContentPane().add(btnNewButton_1);
		reset();
	}
	
	public void reset()
	{
		panel.back();
		leer = true;
		for(int i =0;i<6;i++)	
		{
			for(int j=0; j<5; j++)
			{
				feldB[i][j]= false;
				zeichen[i][j].setBackground(Color.WHITE);
				feld[i*5+j] = nein;
			}
		}
		
	}
	
	public void min()
	{
		if(leer==false)
		{
		int temp=1000000000;
		int tempi[] = new int[30];
		
		for(int c =0;c<6;c++)	
		{
			for(int v=0; v<5; v++)
			{
				tempi[c*5+v]=feldI[c][v];
			}		
		}
		
		for(int b=0;b<30;b++)
		{
			if(tempi[b]<temp&&!(tempi[b]==0))
			{
				temp =tempi[b];
			}
		}
		min =temp;
		}
	}
	public int getF(int a,int b)
	{
		if(feldI[a][b]<255)
		{
			return feldI[a][b];
		}
		else
		{
			return 255;
		}
		
			
	}
}
