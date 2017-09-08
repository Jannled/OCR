package com.github.jannled.ocr;

import com.github.jannled.lib.math.Maths;
import com.github.jannled.lib.math.Matrix;

/**
 * The first version of an artificial neuronal network containing one deep layer.
 * @author Jannled
 * @version 1
 */
public class Annone extends ANN
{
	final protected int inputNodes, middleNodes, outputNodes;
	final protected int[] nodes;
	protected Matrix[] weights;
	
	/**
	 * Create the ANN with the specified amounts of nodes and 0.5 as start weights.
	 * @param inputNodes The amount of input neurons.
	 * @param middleNodes The amount of deep neurons.
	 * @param outputNodes The amount of output neurons.
	 * @param learningrate The rate the weights should be updated with each step.
	 */
	public Annone(int inputNodes, int middleNodes, int outputNodes, float learningrate)
	{
		this.inputNodes = inputNodes;
		this.middleNodes = middleNodes;
		this.outputNodes = outputNodes;
		nodes = new int[] {inputNodes, middleNodes, outputNodes};
		
		weights = new Matrix[2];
		for(int i=0; i<weights.length; i++)
		{
			weights[i] = new Matrix(generateWeights(nodes[i], nodes[i+1]), nodes[i], nodes[i+1]);
		}
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
			Matrix inputs = weights[i-1].multiply(layers[i-1]);
			layers[i] = ANN.sigmoid(inputs);
		}
		
		return layers[layers.length-1];
	}

	/**
	 * Use the backpropagation algorithm to adjust the weights of the matrix, by resolving the error value created by the data and result. 
	 * @param data The input data to adjust the weights on.
	 * @param result The expected result for this set of data.
	 */
	@Override
	public void backpropagate(Matrix data, Matrix result)
	{
		
	}
	
	/**
	 * Calculate some start weights.
	 * @param nodes1 The amount of nodes in the first layer.
	 * @param nodes2 The amount of nodes in the second layer.
	 * @return A matrix containing all the weights for the artificial neuronal network.
	 */
	private double[] generateWeights(int nodes1, int nodes2)
	{
		double[] out = new double[nodes1*nodes2];
		float weightrange = (float) (1/Math.sqrt(nodes1));

		for(int i=0; i<nodes1*nodes2; i++)
		{
			out[i] = (float) Maths.map(Math.random(), 0, 1, -weightrange, weightrange);
		}
		
		return out;
	}
	
	/**
	 * Get the current learn progress.
	 * @return All weights for the different layers. 
	 */
	public Matrix[] getWeights()
	{
		return weights;
	}
	
	/**
	 * Set the current learn progress.
	 * @param weights All weights for the different layers. 
	 */
	public void setWeights(Matrix[] weights)
	{
		this.weights = weights;
	}
}
