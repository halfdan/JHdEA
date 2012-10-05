/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.geekproject;

/**
 *
 * @author halfdan
 */
public class Individual {
    private int dimension;
    /* Well, .. fuck the police? */
    public double[] data;
    public double fitness;
    
    public Individual(int dimension) {
        this.dimension = dimension;
    }
    
    public int getDimension() {
        return dimension;
    }

    public void setData(double[] data) {
        this.data = data;
    }
    
    public double[] getData() {
        return data;
    }
    
    public double getFitness() {
        return this.fitness;
    }  
    
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
    
    @Override
    public Individual clone() {
        Individual clone = new Individual(this.dimension);
        clone.setFitness(this.fitness);
        clone.setData(this.data.clone());
        return clone;
    }
}
