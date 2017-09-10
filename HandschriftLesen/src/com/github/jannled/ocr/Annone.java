package com.github.jannled.ocr;

import java.beans.FeatureDescriptor;

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
	final protected int[] layers;
	protected Matrix[] weights;
	protected Matrix[] nodes;
	protected float learningrate;
	
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
		this.learningrate = learningrate;
		layers = new int[] {inputNodes, middleNodes, outputNodes};
		
		weights = new Matrix[2];
		for(int i=0; i<weights.length; i++)
		{
			weights[i] = new Matrix(generateWeights(layers[i], layers[i+1]), layers[i], layers[i+1]);
		}
	}

	/**
	 * 
	 * @param data The image data to pass through the neuronal network.
	 */
	@Override
	public Matrix forward(Matrix data)
	{
		nodes = new Matrix[3];
		nodes[0] = data;
		
		for(int i=1; i<nodes.length; i++)
		{
			Matrix inputs = weights[i-1].multiply(nodes[i-1]);
			nodes[i] = ANN.sigmoid(inputs);
		}
		
		return nodes[nodes.length-1];
	}

	/**
	 * Use the backpropagation algorithm to adjust the weights of the matrix, by resolving the error value created by the data and result. 
	 * @param data The input data to adjust the weights on.
	 * @param result The expected result for this set of data.
	 */
	@Override
	public void backpropagate(Matrix data, Matrix result)
	{
		Matrix[] deltaw = new Matrix[weights.length];
		Matrix output = forward(data);
		
		//Calculate delta weights for each layer
		for(int i=weights.length-1; i>0; i--)
		{
			deltaw[i] = new Matrix(1, weights[i].getHeight());
			Matrix err = result.subtract(output); 
			Matrix ones = new Matrix(1D, 1, err.getHeight());
			
			Matrix tmpsub = ones.subtract(nodes[i]);
			Matrix tmpmul1 = nodes[i].multiply(tmpsub);
			Matrix tmpmul2 = tmpmul1.multiply(nodes[i-1]);
			deltaw[i] = tmpmul2.multiply(learningrate);
		}
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
