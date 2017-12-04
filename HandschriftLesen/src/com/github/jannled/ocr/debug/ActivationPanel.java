package com.github.jannled.ocr.debug;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import com.github.jannled.lib.math.Matrix;
import com.github.jannled.ocr.Annone;
import com.github.noahdi.ocr.Interface_2;

public class ActivationPanel extends DebugPanel
{
	private static final long serialVersionUID = 5440731293902991156L;
	private Annone ann;
	private JSpinner spnLayers;
	private Matrix[] activationdeltaref;
	
	public ActivationPanel()
	{
		super(new String[] {"Node", "Activation", "Last Delta"});
		
		if(Interface_2.intf.getANN() instanceof Annone) ann = (Annone) Interface_2.intf.getANN();
		
		JLabel lblLayer = new JLabel("Layer");
		pnlSelecter.add(lblLayer);
		
		spnLayers = new JSpinner();
		spnLayers.setModel(new SpinnerNumberModel(0, 0, ann.getWeights().length - 1, 1));
		spnLayers.addChangeListener(this);
		pnlSelecter.add(spnLayers);
		
		if(ann.getNodes() != null) activationdeltaref = ann.getNodes().clone();
		
		update(false);
	}

	@Override
	public void update(boolean recalculateDeltas)
	{
		//Calculate deltas
		Matrix[] alldeltas = new Matrix[activationdeltaref.length];
		if(recalculateDeltas)
		{
			for(int i=0; i<activationdeltaref.length; i++)
			{
				alldeltas[i] = activationdeltaref[i].subtract(ann.getNodes()[i]);
			}
			activationdeltaref = ann.getNodes().clone();
		}
		
		int layer = ((Integer) spnLayers.getModel().getValue()).intValue();
		
		//Write node names
		String[] names = new String[ann.getNodes()[layer].getValues().length];
		for(int i=0; i<names.length; i++)
		{
			names[i] = "Layer " + layer + ", Node " + i;
		}
		
		if(ann.getNodes() != null && alldeltas != null && alldeltas[layer] != null)
		{
			tableValues.setValues(names, new double[][] {ann.getNodes()[layer].getValues(), alldeltas[layer].getValues()});
		}
		
		table.revalidate();
		table.repaint();
	}
}
