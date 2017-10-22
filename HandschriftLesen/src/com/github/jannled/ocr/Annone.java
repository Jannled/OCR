package com.github.jannled.ocr;

import com.github.jannled.lib.math.Maths;
import com.github.jannled.lib.math.Matrix;
import com.github.noahdi.ocr.Interface_2;

/**
 * The first version of an artificial neuronal network containing one deep layer.
 * @author Jannled
 * @version 1
 */
public class Annone extends ANN
{
	final protected int inputNodes, middleNodes, outputNodes;
	final protected int[] layers;
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
		Matrix err = null;
		
		//Calculate delta weights for each layer
		for(int layer=weights.length-1; layer>-1; layer--)
		{
			//Initialize variables for this layer
			deltaw[layer] = new Matrix(nodes[layer].getHeight(), nodes[layer+1].getHeight());
			Matrix leftlayer = new Matrix(1, deltaw[layer].getHeight());
			
			//Calculate error values
			if(layer==weights.length-1)
				err = result.subtract(output);
			else
				err = weights[layer+1].transpose().multiply(err);
			
			//Calculate left side of the equation, node by node
			for(int node=0; node<err.getValues().length; node++)
			{
				final double ok = nodes[layer+1].getValues()[node];
				leftlayer.set(node, err.getValues()[node] * ok * (1 - ok));
			}
			
			//Multiply with the right side of the equation
			deltaw[layer] = leftlayer.multiply(nodes[layer].transpose()).multiply(0.1);
		}
		
		for(int l=0; l<weights.length; l++)
		{
			weights[l] = weights[l].add(deltaw[l]);
		}
		
		Interface_2.wm.updateData(true);
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
	 * Set the current learn progress.
	 * @param weights All weights for the different layers. 
	 */
	public void setWeights(Matrix[] weights)
	{
		this.weights = weights;
	}
}
