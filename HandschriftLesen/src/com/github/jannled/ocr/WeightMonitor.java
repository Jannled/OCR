package com.github.jannled.ocr;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.github.jannled.lib.Print;
import com.github.jannled.lib.math.Matrix;
import com.github.noahdi.ocr.Interface_2;

public class WeightMonitor extends JFrame implements ChangeListener 
{
	private static final long serialVersionUID = -7578507167226329829L;
	private WeightPanel pnlWeights;
	private ANN ann;
	private JSpinner spnLayers;
	private JSpinner spnNodes;

	private Matrix[] deltaref;
	
	/**
	 * Create the frame.
	 */
	public WeightMonitor()
	{	
		this.ann = Interface_2.intf.getANN();
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		pnlWeights = new WeightPanel();
		getContentPane().add(pnlWeights);
		
		JPanel pnlSelecter = new JPanel();
		getContentPane().add(pnlSelecter, BorderLayout.NORTH);
		
		JLabel lblLayers = new JLabel("Layers");
		pnlSelecter.add(lblLayers);
		
		spnLayers = new JSpinner();
		spnLayers.setModel(new SpinnerNumberModel(0, 0, ann.getWeights().length - 1, 1));
		spnLayers.addChangeListener(this);
		pnlSelecter.add(spnLayers);
		
		JLabel lblNewLabel = new JLabel("Left Node");
		pnlSelecter.add(lblNewLabel);
		
		spnNodes = new JSpinner();
		spnNodes.setModel(new SpinnerNumberModel(0, 0, ann.getWeights()[0].getHeight(), 1));
		spnNodes.addChangeListener(this);
		pnlSelecter.add(spnNodes);
		
		deltaref = ann.getWeights().clone();
		update(true);
	}
	
	public void update(boolean recalculateDeltas)
	{
		Matrix[] alldeltas = new Matrix[deltaref.length];
		if(recalculateDeltas)
		{
			//Calculate Deltas
			
			for(int i=0; i<deltaref.length; i++)
			{
				alldeltas[i] = deltaref[i].subtract(ann.getWeights()[i]);
			}
			deltaref = ann.getWeights().clone();
		}
		else alldeltas = ann.getWeights();
		
		int layer = ((Integer) spnLayers.getModel().getValue()).intValue();
		
		int lnode = ((Integer) spnNodes.getModel().getValue()).intValue();
		
		double[] weights = new double[ann.getWeights()[layer].getHeight()];
		System.arraycopy(ann.getWeights()[layer].transpose().getValues(), lnode * (ann.getWeights()[layer].getHeight()-1), weights, 0, ann.getWeights()[layer].getHeight());
		
		double[] deltas = new double[ann.getWeights()[layer].getHeight()];
		System.arraycopy(alldeltas[layer].transpose().getValues(), lnode * (ann.getWeights()[layer].getHeight()-1), deltas, 0, ann.getWeights()[layer].getHeight());
		
		pnlWeights.show(layer, lnode, weights, deltas);
		
		Print.d("Selected Layer " + layer + ", Node " + lnode);
	}

	@Override
	public void stateChanged(ChangeEvent e)
	{
		update(false);
		
		if(e.getSource() != spnNodes)
		{
			spnNodes.setModel(new SpinnerNumberModel(0, 0, ann.getWeights()[((Integer) spnLayers.getModel().getValue()).intValue()].getWidth(), 1));
		}
	}
}