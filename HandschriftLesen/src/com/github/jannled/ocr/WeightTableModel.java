package com.github.jannled.ocr;

import java.text.DecimalFormat;

import javax.swing.table.AbstractTableModel;

public class WeightTableModel extends AbstractTableModel
{
	private static final long serialVersionUID = 694391558532565770L;
	
	private double[] weights;
	private String[] descriptors;
	private double[] deltas;
	private String[] columNames;
	
	public WeightTableModel(String[] columnNames)
	{
		this.columNames = columnNames;
		weights = new double[] {0};
		descriptors = new String[] {"- empty -"};
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