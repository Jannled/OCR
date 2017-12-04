package com.github.jannled.ocr.debug;

import java.text.DecimalFormat;

import javax.swing.table.AbstractTableModel;

public class WeightTableModel extends AbstractTableModel
{
	private static final long serialVersionUID = 694391558532565770L;
	
	private double[][] values;
	private String[] descriptors;
	private String[] columNames;
	
	public WeightTableModel(String[] columnNames)
	{
		this.columNames = columnNames;
		values = new double[columnNames.length][1];
		descriptors = new String[] {"- empty -"};
	}
	
	@Override
	public int getRowCount()
	{
		return values[0].length;
	}

	@Override
	public int getColumnCount()
	{
		return values.length;
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
		else if(columnIndex > 0 && columnIndex-1 < values.length && rowIndex < values[columnIndex-1].length)
		{
			return df.format(values[columnIndex-1][rowIndex]);
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
	
	public void setValues(String[] descriptors, double[][] values)
	{
		this.descriptors = descriptors;
		this.values = values;
	}
}