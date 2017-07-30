package com.github.jannlednoah.ocr.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import com.github.jannled.lib.Print;
import com.github.jannled.lib.math.Maths;
import com.github.jannled.lib.math.Matrix;

/**
 * The canvas to draw the letters on
 * @author Noah
 * @author Jannled
 */
public class Lettercanvas extends JPanel implements MouseMotionListener
{
	private static final long serialVersionUID = -4818018993244417798L;
	
	protected Matrix raster;
	
	public Lettercanvas(Dimension rasterSize)
	{
		raster = new Matrix(rasterSize.width, rasterSize.height);
		addMouseMotionListener(this);
	}
	
	public Lettercanvas(boolean isDubleBuffered, Dimension rasterSize)
	{
		super(isDubleBuffered);
		raster = new Matrix(rasterSize.width, rasterSize.height);
		addMouseMotionListener(this);
	}
	
	public Lettercanvas(LayoutManager layoutManager, Dimension rasterSize)
	{
		super(layoutManager);
		raster = new Matrix(rasterSize.width, rasterSize.height);
		addMouseMotionListener(this);
	}
	
	public Lettercanvas(LayoutManager layoutManager, boolean isDoubleBuffered, Dimension rasterSize)
	{
		super(layoutManager, isDoubleBuffered);
		raster = new Matrix(rasterSize.width, rasterSize.height);
		addMouseMotionListener(this);
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

	@Override
	public void mouseDragged(MouseEvent e)
	{
		Point rasterCoord = rasterize(e.getPoint()); 
		raster.set(rasterCoord.x, rasterCoord.y, 1);
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for(int i=0; i<raster.getHeight()*raster.getWidth(); i++)
		{
			//TODO Implement the visual representation of the matrix
			raster.getValues()[i]==0 ? g.setColor(Color.GREEN) : g.setColor(Color.WHITE);
			g.fillRect(0, 0, getWidth(), getHeight());
		}
	}
}
