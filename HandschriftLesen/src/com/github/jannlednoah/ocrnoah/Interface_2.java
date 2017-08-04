package com.github.jannlednoah.ocrnoah;

	import java.awt.EventQueue;
	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import java.awt.SystemColor;
	import java.awt.Color;
	import javax.swing.JLabel;
	import javax.swing.JButton;
	import java.awt.event.ActionListener;
	import java.awt.event.ActionEvent;
	import javax.swing.JEditorPane;
	import javax.swing.JTextField;
	import javax.swing.JTree;
	import javax.swing.JToggleButton;

	public class Interface_2 {

		private JFrame frame;
		JLabel zeichen[][] = new JLabel[6][5];
		boolean feldB[][] = new boolean[6][5];
		int feldI[][] = new int[6][5];
		int feldF[][] = new int[6][5];
		double feld[] = new double[30];
		double ja = 0.5, nein = -0.5;
		int min;
		boolean leer;
		FreihandZeichnen panel;
		
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
					zeichen[q][w].setBounds(500+50*w, 100+50*q, 50, 50);
					zeichen[q][w].setOpaque(true);
					zeichen[q][w].setBackground(Color.WHITE);
					frame.getContentPane().add(zeichen[q][w]);
				}
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
			btn0.setBounds(421, 460, 250, 50);
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
			btn1.setBounds(421, 510, 250, 50);
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
			btn2.setBounds(100, 510, 250, 50);
			frame.getContentPane().add(btn2);
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
