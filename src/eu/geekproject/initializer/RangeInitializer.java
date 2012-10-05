/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.geekproject.initializer;

import eu.geekproject.Individual;

/**
 * Takes a range of doubles and randomly initializes an individual with values
 * in that range.
 *
 * @author halfdan
 */
public class RangeInitializer implements Initializer {

    private double min;
    private double max;

    public RangeInitializer(double min, double max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public void initialize(Individual indy) {
        int dimension = indy.getDimension();
        double[] data = new double[dimension];
        for(int i = 0; i < dimension; i++) {
            data[i] = min + (Math.random() * max);
        }
        indy.setData(data);
    }

    /**
     * <p>Gets the range as a
     * <code>String</code>.</p>
     *
     * <p>The format of the String is
     * 'RangeInitializer[<i>min</i>,<i>max</i>]'.</p>
     *
     * @return the <code>String</code> representation of this range
     */
    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer(32);
        buf.append("RangeInitializer[");
        buf.append(min);
        buf.append(',');
        buf.append(max);
        buf.append(']');
        return buf.toString();
    }
}
