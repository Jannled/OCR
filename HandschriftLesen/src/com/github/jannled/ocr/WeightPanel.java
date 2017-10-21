package com.github.jannled.ocr;

import java.awt.BorderLayout;
import java.text.DecimalFormat;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.AbstractTableModel;

/**
 * 
 * @author Jannled
 * Note: Might only work for a three layer ANN
 */
public class WeightPanel extends JPanel
{
	private static final long serialVersionUID = 1887140080210736456L;
	private JTable table;
	private WeightTableModel tableValues;
	
	public WeightPanel()
	{
		tableValues = new WeightTableModel();
		
		setLayout(new BorderLayout());
		table = new JTable(tableValues);
		add(table, BorderLayout.CENTER);
		
		JScrollPane scroll = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scroll);
	}
	
	public void show(int layer, int leftNode, double[] weights, double[] deltas)
	{
		String[] nodeDescriptors = new String[weights.length];
		for(int i=0; i<weights.length; i++)
		{
			nodeDescriptors[i] = "Layer " + layer + "; W " + leftNode + "," + i;
		}
		
		tableValues.setValues(nodeDescriptors, weights, deltas);
		table.revalidate();
		table.repaint();
	}
}

class WeightTableModel extends AbstractTableModel
{
	private static final long serialVersionUID = 694391558532565770L;
	
	private double[] weights;
	private String[] descriptors;
	private double[] deltas;
	private String[] columNames = new String[] {"Node", "Weight", "Last Delta"};
	
	public WeightTableModel()
	{
		weights = new double[] {0};
		descriptors = new String[] {"Bitte wähle einen Layer und einen Linken Node aus!"};
	}
	
	public WeightTableModel(double[] weights, String[] descriptors, double[] deltas)
	{
		setValues(descriptors, weights, deltas);
	}
	
	@Override
	public int getRowCount()
	{
		return weights.length;
	}

	@Override
	public int getColumnCount()
	{
		return 3;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(10);
		
		if(columnIndex == 0 && rowIndex < descriptors.length)
		{
			return descriptors[rowIndex];
		}
		else if(columnIndex == 1 && rowIndex < weights.length)
		{
			return df.format(weights[rowIndex]);
		}
		else if(columnIndex == 2 && rowIndex < deltas.length)
		{
			return df.format(deltas[rowIndex]);
		}
		return null;
	}
	
	@Override
	public String getColumnName(int column)
	{
		if(column >= columNames.length)
		{
			return null;
		}
		return columNames[column];
	}
	
	public void setValues(String[] descriptors, double[] weights, double[] deltas)
	{
		this.descriptors = descriptors;
		this.weights = weights;
		this.deltas = deltas;
	}
}

