package com.github.jannled.ocr.debug;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.github.jannled.ocr.ANN;
import com.github.noahdi.ocr.Interface_2;

public abstract class DebugPanel extends JPanel implements ChangeListener
{
	private static final long serialVersionUID = 6129164144790346879L;
	protected ANN ann;
	protected JPanel pnlSelecter;
	protected JTable table;
	protected WeightTableModel tableValues;

	public DebugPanel(String[] columnNames)
	{
		this.ann = Interface_2.intf.getANN();
		
		setLayout(new BorderLayout());
		
		pnlSelecter = new JPanel();
		add(pnlSelecter, BorderLayout.NORTH);
		
		tableValues = new WeightTableModel(columnNames);
		table = new JTable(tableValues);
		add(table, BorderLayout.CENTER);
		
		JScrollPane scroll = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scroll);
	}
	
	public abstract void update(boolean recalculateDeltas);
	
	@Override
	public void stateChanged(ChangeEvent e)
	{
		update(false);
		table.revalidate();
		table.repaint();
	}
}
