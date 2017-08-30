package com.github.jannled.ocr;

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
	
	private static final int RANDOM = 0x11111111;
	
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
		weights[0] = new Matrix(startWeights(1, inputNodes*middleNodes, 0.5f));
		weights[1] = new Matrix(startWeights(1, middleNodes*outputNodes, 0.5f));
	}

	/**
	 * 
	 * @param data The image data to pass through the neuronal network.
	 */
	@Override
	public Matrix forward(Matrix data)
	{
		Matrix layers[] = new Matrix[3]; 
		layers[0] = data;
		
		for(int i=1; i<layers.length; i++)
		{
			Matrix mul = layers[i-1].multiply(weights[i-1]);
			layers[i] = ANN.sigmoid(mul);
		}
		
		return layers[layers.length-1];
	}

	/**
	 * Use the backpropagation algorithm to adjust the weights of the matrix, by resolving the error value created by the data and result. 
	 * @param data The input data to adjust the weights on
	 * @param result The expected result for this set of data
	 */
	@Override
	public void backpropagate(Matrix data, Matrix result)
	{
		
	}
	
	/**
	 * Calculate some start weights.
	 * @param nodes1 The amount of nodes in the first layer.
	 * @param nodes2 The amount of nodes in the second layer.
	 * @param value  The start value for each weight, or use the constant <code>RANDOM</code> to use random start weights.
	 * @return A matrix containing all the weights for the artificial neuronal network.
	 */
	private float[][] startWeights(int nodes1, int nodes2, float value)
	{
		float[][] out = new float[nodes1][nodes2];
		
		if(value != RANDOM)
		{
			for(int a=0; a<nodes1; a++)
			{
				for(int b=0; b<nodes2; b++)
				{
					out[a][b] = value;
				}
			}
		}
		else
		{
			for(int a=0; a<nodes1; a++)
			{
				for(int b=0; b<nodes2; b++)
				{
					out[a][b] = (float) Math.random();
				}
			}
		}
		
		return out;
	}
}
