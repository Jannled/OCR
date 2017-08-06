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
import java.awt.Font;
import javax.swing.SwingConstants;


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
	private JTextField letter;
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
				zeichen[q][w].setBounds(720+50*w, 100+50*q, 50, 50);
				zeichen[q][w].setOpaque(true);
				zeichen[q][w].setBackground(Color.WHITE);
				frame.getContentPane().add(zeichen[q][w]);
			}
		}
		
		for(int i=0;i<26;i++)
		{
			alpha[i]=-0.5f;
		}

		
		JButton getter = new JButton("Holen");
		getter.setBackground(new Color(0, 100, 0));
		getter.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		getter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					holen();
					zeigen();
					
				}catch(Exception ee)
				{
					
				}
			}
		});
		getter.setBounds(410, 100, 250, 145);
		frame.getContentPane().add(getter);
		
		panel.setBackground(new Color(51, 102, 102));
		panel.setBounds(100, 100, 250, 300);
		frame.getContentPane().add(panel);
		
		JButton reset = new JButton("Reset");
		reset.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					reset();
					
				}catch(Exception ee)
				{
					
				}
			}
		});
		reset.setBounds(410, 255, 250, 145);
		frame.getContentPane().add(reset);
		
		
		JButton lern = new JButton("Lernen");
		lern.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		lern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					lernen();
					reset();
					
				}catch(Exception ee)
				{
					
					
				}
			}
		});
		lern.setBounds(100, 522, 250, 84);
		frame.getContentPane().add(lern);
		
		letter = new JTextField();
		letter.setHorizontalAlignment(SwingConstants.CENTER);
		letter.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		letter.setBounds(100, 460, 250, 50);
		frame.getContentPane().add(letter);
		letter.setColumns(10);
		
		JButton know = new JButton("Erkennen");
		know.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		know.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ann.forward(new Matrix(feld, 5,6));
			}
		});
		know.setBounds(410, 521, 250, 84);
		frame.getContentPane().add(know);
		
		JLabel lblNewLabel = new JLabel("Erkannter Buchstabe:");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(410, 460, 250, 50);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel titel = new JLabel("<html>J&N Handschrifterkennung<sup><FONT SIZE=\"4\">TM</sup></html>");
		titel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		titel.setBounds(100, 11, 560, 90);
		frame.getContentPane().add(titel);
		
		JLabel lblNewLabel_1 = new JLabel("a product powered by JannLed und NoahDi");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(100, 617, 560, 39);
		frame.getContentPane().add(lblNewLabel_1);
		reset();
	}
	public void lernen()
	{
		String tf;
		
		tf = letter.getText().toLowerCase();
		
		
		
		for(int w=0;w<26;w++)
		{
			if(tf.equals("" + alphabet[w]))
			{
				alpha[w]=0.5f;
				
			}
		}
		
		
		ann.backpropagate(new Matrix(feld, 5,6),new Matrix(alpha, 1, 26));
	}
	
	
	public void holen()
	{
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
	}
	
	public void zeigen()
	{
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
		letter.setText("");
		
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
