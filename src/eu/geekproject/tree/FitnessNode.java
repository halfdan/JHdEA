/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.geekproject.tree;

import eu.geekproject.Individual;

/**
 *
 * @author halfdan
 */
public class FitnessNode {
    private FitnessNode leftNode;
    private FitnessNode rightNode;
    private FitnessNode parentNode;
    private int dimension;
    private double threshold;
    private int visitCount;
    private Individual individual;
    
    
    public FitnessNode(FitnessNode parent, Individual indy) {
	this.dimension = -1;
	this.threshold = 0.0;
	this.parentNode = parent;
	this.visitCount = 1;
	this.individual = indy;
    }
    
    public int getDimension() {
        return dimension;
    }
    
    public void setDimension(int dimension) {
        this.dimension = dimension;
    }
    
    public FitnessNode getLeftNode() {
        return this.leftNode;
    }
    
    public FitnessNode getRightNode() {
        return this.rightNode;
    }
    
    public void setLeftNode(FitnessNode node) {
        this.leftNode = node;
    }
    
    public void setRightNode(FitnessNode node) {
        this.rightNode = node;
    }
    
    public FitnessNode getParentNode() {
        return this.parentNode;
    }
    
    public int getVisitCount() {
        return this.visitCount;
    }
    
    public Individual getIndividual() {
        return this.individual;
    }
    
    public double getThreshold() {
        return this.threshold;
    }
    
    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }
}
