package com.github.jannlednoah.ocr.ann;

import com.github.jannled.lib.math.Matrix;

/**
 * The abstract class for an artificial network with backpropagation
 * @author Jannled
 */
public abstract class ANN
{
	protected int inputLayers, middleLayers, outputLayers;
	protected Matrix[] weights;
	
	public ANN(int inputLayers, int middleLayers, int outputLayers)
	{
		this.inputLayers = inputLayers;
		this.middleLayers = middleLayers;
		this.outputLayers = outputLayers;
	}
	
	public abstract Matrix forward(Matrix data);
	
	public abstract void backpropagate(Matrix data, Matrix result);
}
