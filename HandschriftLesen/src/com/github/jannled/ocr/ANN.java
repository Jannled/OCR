package com.github.jannled.ocr;

import com.github.jannled.lib.math.Matrix;

/**
 * An <b>A</b>rtificial <b>N</b>euronal <b>N</b>etwork using backpropagation.
 * @author Jannled
 */
public abstract class ANN
{
	protected Matrix[] weights;
	
	/**
	 * Let the neuronal network calculate the output.
	 * @param data The data to be processed.
	 * @return The data, processed with the current weights.
	 */
	public abstract Matrix forward(Matrix data);
	
	/**
	 * Let the network adjust its values.
	 * @param data The input values that should be processed by the ANN.
	 * @param result The expected output for the given input values.
	 */
	public abstract void backpropagate(Matrix data, Matrix result);
	
	/**
	 * Sigmoid activation function
	 * @param m The matrix to apply the sigmoid function to.
	 * @return The matrix with the applied sigmoid function.
	 */
	public static Matrix sigmoid(Matrix m)
	{
		Matrix out = new Matrix(m.getWidth(), m.getHeight());
		
		for(int i=0; i<m.getValues().length; i++)
		{
			double pow = Math.pow(Math.E, -m.getValues()[i]);
			out.getValues()[i] =  (1/(1 + pow));
		}
		
		return out;
	}
	
	/**
	 * Get the sum of all elements in the matrix.
	 * @param m The matrix to build the sum.
	 * @return The sum of the matrix.
	 */
	public static double sum(Matrix m)
	{
		double out = 0;
		
		for(int i=0; i<m.getHeight(); i++)
		{
			out += m.getValues()[i];
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
}
