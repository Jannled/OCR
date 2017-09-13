package com.github.jannled.ocr;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.github.jannled.lib.FileUtils;

public class ReadSave implements ActionListener
{
	JFileChooser fileChooser;
	ANN ann;
	
	public ReadSave(ANN ann)
	{
		this.ann = ann;
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("Training data", "csv"));
		fileChooser.addActionListener(this);
	}
	
	public void trainCSV(File file)
	{
		fileChooser.showOpenDialog(null);
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(fileChooser.getSelectedFile()==null)
			return;
		
		String[] file = FileUtils.readTextFile(fileChooser.getSelectedFile());
	}
}
