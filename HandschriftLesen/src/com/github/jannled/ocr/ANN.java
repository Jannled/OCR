package com.github.jannled.ocr;

import com.github.jannled.lib.math.Matrix;

/**
 * An <b>A</b>rtificial <b>N</b>euronal <b>N</b>etwork using backpropagation.
 * @author Jannled
 */
public abstract class ANN
{
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
	 */
	public static Matrix sigmoid(Matrix m)
	{
		Matrix out = new Matrix(m.getWidth(), m.getHeight());
		
		for(int i=0; i<m.getValues().length; i++)
		{
			out.getValues()[i] = (float) (1/(1 + Math.pow(Math.E, -m.getValues()[i])));
		}
		
		return out;
	}
	
	/**
	 * Add all weights in a row together for the next neurons.
	 * @param m The result weights.
	 * @return 
	 */
	public static Matrix rowsum(Matrix m)
	{
		Matrix out = new Matrix(1, m.getHeight());
		
		for(int y=0; y<m.getHeight(); y++)
		{
			float row = 0;
			for(int x=0; x<m.getWidth(); x++)
			{
				row += m.get(x, y);
			}
			out.set(0, y, row);
		}
		return out;
	}
}
