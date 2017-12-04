package com.github.jannled.ocr.debug;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;

import com.github.jannled.lib.Print;
import com.github.jannled.lib.math.Matrix;

/**
 * 
 * @author Jannled
 * Note: Might only work for a three layer ANN
 */
public class WeightPanel extends DebugPanel
{
	private static final long serialVersionUID = 1887140080210736456L;
	
	//Weight Monitor
	private JSpinner spnLayers;
	private JSpinner spnNodes;
	private Matrix[] weightdeltaref;
	
	public WeightPanel()
	{
		super(new String[] {"Node", "Weight", "Last Delta"});
		
		JLabel lblLayers = new JLabel("Layers");
		pnlSelecter.add(lblLayers);
		
		spnLayers = new JSpinner();
		spnLayers.setModel(new SpinnerNumberModel(0, 0, ann.getWeights().length - 1, 1));
		spnLayers.addChangeListener(this);
		pnlSelecter.add(spnLayers);
		
		JLabel lblNodes = new JLabel("Left Node");
		pnlSelecter.add(lblNodes);
		
		spnNodes = new JSpinner();
		spnNodes.setModel(new SpinnerNumberModel(0, 0, ann.getWeights()[0].getHeight(), 1));
		spnNodes.addChangeListener(this);
		pnlSelecter.add(spnNodes);
		
		weightdeltaref = ann.getWeights().clone();
		
		update(true);
	}
	
	public void update(boolean recalculateDeltas)
	{
		//Calculate deltas
		Matrix[] alldeltas = new Matrix[weightdeltaref.length];
		if(recalculateDeltas)
		{
			for(int i=0; i<weightdeltaref.length; i++)
			{
				alldeltas[i] = weightdeltaref[i].subtract(ann.getWeights()[i]);
			}
			weightdeltaref = ann.getWeights().clone();
		}
		else alldeltas = ann.getWeights();
		
		int layer = ((Integer) spnLayers.getModel().getValue()).intValue();
		
		int lnode = ((Integer) spnNodes.getModel().getValue()).intValue();
		
		double[] weights = new double[ann.getWeights()[layer].getHeight()];
		System.arraycopy(ann.getWeights()[layer].transpose().getValues(), lnode * (ann.getWeights()[layer].getHeight()-1), weights, 0, ann.getWeights()[layer].getHeight());
		
		double[] deltas = new double[ann.getWeights()[layer].getHeight()];
		System.arraycopy(alldeltas[layer].transpose().getValues(), lnode * (ann.getWeights()[layer].getHeight()-1), deltas, 0, ann.getWeights()[layer].getHeight());
		
		show(layer, lnode, weights, deltas);
		
		Print.d("Selected Layer " + layer + ", Node " + lnode);
	}
	
	public void show(int layer, int leftNode, double[] weights, double[] deltas)
	{
		String[] nodeDescriptors = new String[weights.length];
		for(int i=0; i<weights.length; i++)
		{
			nodeDescriptors[i] = "Layer " + layer + "; W " + leftNode + "," + i;
		}
		
		tableValues.setValues(nodeDescriptors, new double[][] {weights, deltas});
		table.revalidate();
		table.repaint();
	}
	
	@Override
	public void stateChanged(ChangeEvent e)
	{	
		if(e.getSource() != spnNodes)
		{
			spnNodes.setModel(new SpinnerNumberModel(0, 0, ann.getWeights()[((Integer) spnLayers.getModel().getValue()).intValue()].getWidth(), 1));
		}
		
		super.stateChanged(e);
	}
}