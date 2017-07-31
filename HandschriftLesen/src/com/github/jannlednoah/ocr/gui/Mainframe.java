package com.github.jannlednoah.ocr.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.github.jannlednoah.ocr.ann.ANN;
import com.github.jannlednoah.ocr.ann.Annone;

/**
 * The Main class and window manager. All window elements are found inside
 * @author Jannled
 * @author Noah
 */
public class Mainframe
{
	private Annone annone = new Annone(64, 64, 26);
	public static Mainframe mf;
	
	private JFrame frmOcrByJN;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				try
				{
					Mainframe window = new Mainframe();
					window.frmOcrByJN.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Mainframe()
	{
		initialize();
		mf = this;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frmOcrByJN = new JFrame();
		frmOcrByJN.setTitle("OCR by Jannik & Noah");
		frmOcrByJN.setBounds(100, 100, 450, 300);
		frmOcrByJN.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel drawContainer = new JPanel();
		drawContainer.setBorder(new TitledBorder(null, "Draw a letter", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmOcrByJN.getContentPane().add(drawContainer, BorderLayout.CENTER);
		drawContainer.setLayout(new BorderLayout(0, 0));
		
		Lettercanvas panel = new Lettercanvas(new Dimension(8, 8));
		drawContainer.add(panel);
	}

	public ANN getANN()
	{
		return annone;
	}
}
