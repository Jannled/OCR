package com.github.jannlednoah.ocr.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import com.github.jannled.lib.Print;
import com.github.jannled.lib.math.Maths;
import com.github.jannled.lib.math.Matrix;

/**
 * A canvas where the user can draw on, the input will then be rasterized into a matrix with lower resolution than the canvas and later on read by the ANN.
 * @author Noah
 * @author Jannled
 */
public class Lettercanvas extends JPanel implements MouseMotionListener, MouseListener
{
	private static final long serialVersionUID = -4818018993244417798L;
	private boolean wasPressed = false;
	protected Matrix raster;
	
	/**
	 * Create a canvas where the user can draw on, the input will then be rasterized into a matrix with lower resolution than the canvas.
	 * @param rasterSize The dimension the underlying pixel matrix should have.
	 */
	public Lettercanvas(Dimension rasterSize)
	{
		init(rasterSize);
	}
	
	/**
	 * Create a canvas where the user can draw on, the input will then be rasterized into a matrix with lower resolution than the canvas.
	 * @param rasterSize The dimension the underlying pixel matrix should have.
	 * @param isDubleBuffered If the frame should have two frame buffers.
	 */
	public Lettercanvas(boolean isDubleBuffered, Dimension rasterSize)
	{
		super(isDubleBuffered);
		init(rasterSize);
	}
	
	/**
	 * Create a canvas where the user can draw on, the input will then be rasterized into a matrix with lower resolution than the canvas.
	 * @param rasterSize The dimension the underlying pixel matrix should have.
	 * @param layoutManager The LayoutManager that should be used by this component.
	 */
	public Lettercanvas(LayoutManager layoutManager, Dimension rasterSize)
	{
		super(layoutManager);
		init(rasterSize);
	}
	
	/**
	 * Create a canvas where the user can draw on, the input will then be rasterized into a matrix with lower resolution than the canvas.
	 * @param rasterSize The dimension the underlying pixel matrix should have
	 * @param isDubleBuffered If the frame should have two frame buffers.
	 * @param layoutManager The LayoutManager that should be used by this component.
	 */
	public Lettercanvas(LayoutManager layoutManager, boolean isDoubleBuffered, Dimension rasterSize)
	{
		super(layoutManager, isDoubleBuffered);
		init(rasterSize);
	}
	
	private void init(Dimension rasterSize)
	{
		raster = new Matrix(rasterSize.width, rasterSize.height);
		addMouseMotionListener(this);
		addMouseListener(this);
	}
	
	/**
	 * Map the pixel values to the pixels of the matrix
	 * @param p The Pixel space coordinates
	 * @return The Matrix space coordinates
	 */
	protected Point rasterize(Point p)
	{
		int x = Math.max(Math.min(Maths.map((int) p.getX(), 0, getWidth(), 0, raster.getWidth()), raster.getWidth()-1), 0);
		int y = Math.max(Math.min(Maths.map((int) p.getY(), 0, getHeight(), 0, raster.getHeight()), raster.getHeight()-1), 0);
		Print.m("Coords: " + x + "|" + y + ".");
		return new Point(x, y);
	}

	/**
	 * Called when the mouse is dragged over this component
	 */
	@Override
	public void mouseDragged(MouseEvent e)
	{
		wasPressed = true;
		Point rasterCoord = rasterize(e.getPoint());
		raster.set(rasterCoord.x, rasterCoord.y, 1);
		repaint();
	}

	/**
	 * Called when the mouse is moved over this component
	 */
	@Override
	public void mouseMoved(MouseEvent e)
	{
		
	}
	
	/**
	 * Draws the component, especially draws the matrix to the display
	 */
	@Override
	protected void paintComponent(Graphics g)
	{
		//TODO Fix that 1s are not drawed white
		super.paintComponent(g);
		for(int i=0; i<raster.getHeight()*raster.getWidth(); i++)
		{
			if(raster.getValues()[i]==0)
				g.setColor(Color.GREEN);
			else
				g.setColor(Color.WHITE);
			
			int xpos = i%raster.getWidth()*(getWidth()/raster.getWidth());
			int ypos = ((int) i/raster.getHeight())*(getHeight()/raster.getHeight());
			int width = getWidth()/raster.getWidth();
			int height = getHeight()/raster.getHeight();
			g.fillRect(xpos, ypos, width, height);
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e)
	{
		if(wasPressed)
		{
			wasPressed = false;
			Mainframe.mf.getANN().forward(raster);
			System.out.println("Sending input to ANN");
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

}
