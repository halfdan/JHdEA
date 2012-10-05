/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.geekproject.problem;

/**
 *
 * @author halfdan
 */
public class AckleyProblem implements OptimizationProblem {

    @Override
    public double evaluate(double[] data) {
        int dimension = data.length;
        double sum_x, sum_cosx, fitness_value;
        double sum_sinx;

        sum_x = 0.0;
        sum_cosx = 0.0;
        for(int i = 0; i < dimension; i++)
        {
                sum_x += Math.pow(data[i], 2);
                sum_cosx += Math.cos(2.0 * Math.PI * data[i]);
        }

        fitness_value = -20.0 * Math.exp(-0.2 * Math.sqrt(sum_x / dimension)) -
                                        Math.exp(sum_cosx / dimension) + 20.0 + Math.exp(1);

        sum_sinx = 0.0;
        for(int i = 0; i < dimension; i++) {
                sum_sinx += (0.5 * (1.0 - Math.cos(data[i] * 1.0)));
        }
        sum_sinx /= dimension;

        return fitness_value + 0.1 * sum_sinx * fitness_value;
    }
    
    
}
