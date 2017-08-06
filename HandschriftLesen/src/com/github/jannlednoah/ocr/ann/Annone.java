package com.github.jannlednoah.ocr.ann;

import com.github.jannled.lib.math.Matrix;

/**
 * The first version of an artificial neuronal network containing one deep layer.
 * @author Jannled
 * @version 1
 */
public class Annone extends ANN
{
	final int inputNodes, middleNodes, outputNodes;
	protected Matrix[] weights;
	
	/**
	 * Create the ANN with the specified amounts of nodes and 0.5 as start weights.
	 * @param inputNodes The amount of input neurons.
	 * @param middleNodes The amount of deep neurons.
	 * @param outputNodes The amount of output neurons.
	 */
	public Annone(int inputNodes, int middleNodes, int outputNodes)
	{
		this.inputNodes = inputNodes;
		this.middleNodes = middleNodes;
		this.outputNodes = outputNodes;
		weights = new Matrix[2];
		weights[0] = new Matrix(startWeights(inputNodes, middleNodes));
		weights[1] = new Matrix(startWeights(middleNodes, outputNodes));
	}

	@Override
	public Matrix forward(Matrix data)
	{
		Matrix output[] = new Matrix[3]; 
		output[0] = data;
		
		for(int i=1; i<output.length; i++)
		{
			output[i] = ANN.sigmoid(output[i].multiply(weights[i-1]));
		}
		
		return output[output.length-1];
	}

	@Override
	public void backpropagate(Matrix data, Matrix result)
	{
		
	}
	
	private float[][] startWeights(int nodes1, int nodes2)
	{
		float[][] out = new float[nodes1][nodes2];
		for(int a=0; a<nodes1; a++)
		{
			for(int b=0; b<nodes2; b++)
			{
				out[a][b] = 0.5f;
			}
		}
		return out;
	}
}
