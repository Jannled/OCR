package com.github.noahdi.ocr;

import java.awt.Color;
	import java.awt.Graphics; 
	import java.awt.Graphics2D; 
	import java.awt.event.MouseEvent; 
	import java.awt.event.MouseListener; 
	import java.awt.event.MouseMotionListener;
	import javax.swing.JFrame; 
	import javax.swing.JPanel;

import com.github.jannled.lib.Print; 



@SuppressWarnings("serial") 
public class FreihandZeichnen extends JPanel implements MouseListener, MouseMotionListener 
{ 
	int hoehE =300;
	int breitE = 250;
	static int breite;
	static int hoehe;
	boolean feldB[];
	int counter[][];
    private int startX, startY, tempX, tempY; 
    private Graphics2D g2; 
    int f=0;

    
    public FreihandZeichnen(int h, int b)
    {
    	Print.m("Freihandzeichnen aufgerufen...");
        this.addMouseListener(this); 
        this.addMouseMotionListener(this);
        breite= b;
        hoehe= h;
        feldB =new boolean[breite*hoehe];
        counter = new int[hoehe][breite];
    }
    
    public FreihandZeichnen() { 
        Print.m("Freihandzeichnen aufgerufen...");
        this.addMouseListener(this); 
        this.addMouseMotionListener(this); 
    } 

    protected void paintComponent(Graphics g) { 
        // hier darf kein super.paintComponent() aufgerufen werden! 
        g2 = (Graphics2D) g;
        color();
        g2.fillRect(0, 0, 250, 300);
        g2.setPaintMode();
        g2.setColor(Color.BLACK);
        g2.drawLine(startX, startY, tempX, tempY); 
        startX = tempX; 
        startY = tempY; 
    } 

    public static void main(String[] args) { 
        JFrame frame = new JFrame(); 
        frame.add(new FreihandZeichnen(hoehe, breite)); 
 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setVisible(true); 
    } 

    public void mouseClicked(MouseEvent e) { 
    } 

    public void mouseEntered(MouseEvent e) { 
    } 

    public void mouseExited(MouseEvent e) { 
    } 

    public void mouseReleased(MouseEvent e) { 
    }
    
    public void mouseMoved(MouseEvent e) { 
    }
    
    public void mousePressed(MouseEvent e) { 
        startX = e.getX(); 
        startY = e.getY();
        count(startX, startY);
    } 

    public void mouseDragged(MouseEvent e) { 
        tempX = e.getX(); 
        tempY = e.getY();
        count(tempX, tempY);
        repaint(); 
    }
    
    public void count(int x, int y)
    {
    		x =(int) x/(breitE/breite);
    		y =(int) y/(hoehE/hoehe);
    	
    		counter[y][x]++;
    		Print.d(x+", " +y  +", "+counter[y][x]);
 
    }
    public void back()
    {
		this.removeAll();
		this.validate();
		repaint();
		for(int i=0;i<hoehe;i++)
		{
			for(int j=0;j<breite;j++)
			{
				counter[i][j]=0;
			}
		}
    }
    
    public void color()
    {
    	if(f==0)
    	{
    		g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, 250, 300);
            //g2.setColor(Color.BLACK);
            f++;
    	}
    }
}
