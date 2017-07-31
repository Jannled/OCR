package com.github.jannlednoah.ocr.ann;

import com.github.jannled.lib.math.Matrix;

/**
 * An <b>A</b>rtificial <b>N</b>euronal <b>N</b>etwork using backpropagation.
 * @author Jannled
 */
public interface ANN
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
}
