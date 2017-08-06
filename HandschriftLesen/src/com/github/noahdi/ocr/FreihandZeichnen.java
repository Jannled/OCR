package com.github.noahdi.ocr;

import java.awt.Color;
	import java.awt.Graphics; 
	import java.awt.Graphics2D; 
	import java.awt.event.MouseEvent; 
	import java.awt.event.MouseListener; 
	import java.awt.event.MouseMotionListener;
	import javax.swing.JFrame; 
	import javax.swing.JPanel; 



@SuppressWarnings("serial") 
public class FreihandZeichnen extends JPanel implements MouseListener, MouseMotionListener 
{ 

	boolean feldB[] = new boolean[30];
	int counter[][] = new int[6][5];
    private int startX, startY, tempX, tempY; 
    private Graphics2D g2; 
    int f=0;

    public FreihandZeichnen() { 
        System.out.println("Freihandzeichnen aufgerufen...");
        this.addMouseListener(this); 
        this.addMouseMotionListener(this); 
    } 

    protected void paintComponent(Graphics g) { 
        // hier darf kein super.paintComponent() aufgerufen werden! 
        g2 = (Graphics2D) g;
        ff();
        g2.fillRect(0, 0, 250, 300);
        g2.setPaintMode();
        g2.setColor(Color.BLACK);
        g2.drawLine(startX, startY, tempX, tempY); 
        startX = tempX; 
        startY = tempY; 
    } 

    public static void main(String[] args) { 
        JFrame frame = new JFrame(); 
        frame.add(new FreihandZeichnen()); 
        //frame.setSize(500, 600); 
        //frame.setTitle("FreihandZeichnen");
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
    		x =(int) x/50;
    		y =(int) y/50;
    	
    		counter[y][x]++;
    		System.out.println(x+", " +y  +", "+counter[y][x]);
 
    }
    public void back()
    {
		this.removeAll();
		this.validate();
		repaint();
		for(int i=0;i<6;i++)
		{
			for(int j=0;j<5;j++)
			{
				counter[i][j]=0;
			}
		}
    }
    
    public void ff()
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
