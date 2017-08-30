package com.github.noahdi.ocr;

/**
 * imports
 */

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;

import com.github.jannled.lib.Print;
import com.github.jannled.lib.math.Matrix;
import com.github.jannled.ocr.Annone;




public class Interface_2 
{
	/**
	 * attributes
	 */
	
	private JFrame frame;
	JLabel zeichen[][];
	boolean feldB[][];
	int feldI[][];
	int feldF[][];
	float feld[];
	float alpha[] = new float[26];
	/** All chars the ANN can learn, currently 26 */
	char alphabet[] = new char[]{'a','b','c','d','e','f',
								 'g','h','i','j','k','l','m',
								 'n','o','p','q','r','s','t',
								 'u','v','w','x','y','z'};
	float ja = 0.5f, nein = -0.5f;
	boolean leer;
	FreihandZeichnen panel;
	Annone ann;
	private JTextField letter;
	/**
	 * Very very important!!!!
	 */
	
				/* +++important!!!!+++ +++important!!!!+++ +++important!!!!+++*/	int breite =5, hoehe = 6;	/* +++important!!!!+++ +++important!!!!+++ +++important!!!!+++*/
	int breitE =250, hoehE = 300;
				
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
		
		/**
		 * filling the attributes
		 */
		zeichen	= 	new JLabel[hoehe][breite];
		feldB 	=	new boolean[hoehe][breite];
		feldI	=	new int[hoehe][breite];
		feldF 	= 	new int[hoehe][breite];
		feld 	= 	new float[(hoehe*breite)];
		ann 	= 	new Annone((hoehe*breite), (hoehe+breite), alphabet.length);
		
		
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.scrollbar);
		frame.setBounds(100, 100, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		panel = new FreihandZeichnen(hoehe, breite);
		
		for(int q=0;q<hoehe;q++)
		{
			for(int w=0;w<breite;w++)
			{
				zeichen[q][w] = new JLabel("");
				zeichen[q][w].setBounds(720+(breitE/breite)*w, 100+(hoehE/hoehe)*q, (breitE/breite), (hoehE/hoehe));
				zeichen[q][w].setOpaque(true);
				zeichen[q][w].setBackground(Color.WHITE);
				frame.getContentPane().add(zeichen[q][w]);
			}
		}
		
		for(int i=0;i<26;i++)
		{
			alpha[i]=nein;
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
				ann.forward(new Matrix(feld, 1, feld.length));
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
				alpha[w]=ja;
				
			}
		}
		
		
		ann.backpropagate(new Matrix(feld, 1, feld.length),new Matrix(alpha, 1, alpha.length));
	}
	
	public void holen()
	{
		for(int i=0;i<hoehe;i++)
		{
			for(int j=0;j<breite;j++)
			{
				feldI[i][j]= panel.counter[i][j];
				if(feldI[i][j]>0)
				{
					leer = false;
					feld[i*breite+j] = ja;
					feldB[i][j] = true;
				}else
				{
					feld[i*breite+j] = nein;
					feldB[i][j] = false;
				}
			}
		}
	}
	
	public void zeigen()
	{
		Print.d("");
		Print.d("");
		Color temp;
		for(int i=0; i<hoehe; i++)
		{
			for(int j=0; j<breite; j++)
			{
				Print.d(" "+feld[i*breite+j]);
				//
				if(feldB[i][j]==true)
				{
					temp = new Color(0,(255-getF(i,j)),0);
					zeichen[i][j].setBackground(temp);
				}
			}
			Print.d("");
		}
		
		for(int i=0; i<(hoehe*breite); i++)
		{
			Print.d(feld[i]+"");
		}
	}
	
	public void reset()
	{
		panel.back();
		leer = true;
		for(int i =0;i<hoehe;i++)	
		{
			for(int j=0; j<breite; j++)
			{
				feldB[i][j]= false;
				zeichen[i][j].setBackground(Color.WHITE);
				feld[i*breite+j] = nein;
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
