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
public class FitnessTree {

    private int dimension;
    private int leafCount;
    private FitnessNode rootNode;
    private int revisitCount = 0;
    private double[][] interval;
    private double[][] currentInterval;

    public FitnessTree(int dimension) {
        this.dimension = dimension;
        this.leafCount = 0;
        this.interval = new double[dimension][2];
        this.currentInterval = new double[dimension][2];
    }

    public FitnessNode Search(Individual indy) {
        FitnessNode currentNode;
        double[] currentData;

        currentNode = this.rootNode;
        while (currentNode.getDimension() > -1) {
            currentData = indy.getData();
            if (currentData[currentNode.getDimension()] < currentNode.getThreshold()) {
                this.interval[currentNode.getDimension()][1] = currentNode.getThreshold();
                currentNode = currentNode.getLeftNode();
            } else {
                this.interval[currentNode.getDimension()][0] = currentNode.getThreshold();
                currentNode = currentNode.getRightNode();
            }
        }

        return currentNode;
    }
    
    public FitnessNode getRootNode() {
        return this.rootNode;
    }

    public void Insert(Individual indy) {
        int dimension;
        double interval_diff = 0.0;
        /* The current node */
        FitnessNode currentNode;
        /* The parent node */
        FitnessNode parentNode;
        /* The data[] of the current node's individual */
        double[] currentData;
        /* The data[] if the new indy */
        double[] indyData;

        dimension = this.dimension;

        if (rootNode == null) {
            // Create root node
            this.rootNode = new FitnessNode(null, indy);
        } else {
            currentNode = this.Search(indy);
            currentData = currentNode.getIndividual().getData();
            indyData = indy.getData();
            for (int i = 0; i < dimension; ++i) {
                if (currentData[i] != indyData[i]
                        && (currentNode.getDimension() == -1 
                        || interval_diff < Math.abs(currentData[i] - indyData[i]))) {
                    currentNode.setDimension(i);
                    currentNode.setThreshold(0.5 * (currentData[i] + indyData[i]));
                    interval_diff = Math.abs(currentData[i] - indyData[i]);
                }
            }
            
            //FitnessNode_Contruction(cur_Node, currentNode.Child_Left, cur_Dimension);
            //FitnessNode_Contruction(cur_Node, currentNode.Child_Right, cur_Dimension);
            FitnessNode leftChild, rightChild;

            this.leafCount += 2;

            if (currentNode.getIndividual().data[currentNode.getDimension()] < indy.data[currentNode.getDimension()]) {
                leftChild = new FitnessNode(currentNode, currentNode.getIndividual().clone());
                rightChild = new FitnessNode(currentNode, indy);
                /*
                for (int i = 0; i < dimension; ++i) {
                    ( * currentNode.Child_Left).Optimal_Data[i] = currentNode.Optimal_Data[i];
                    ( * currentNode.Child_Right).Optimal_Data[i] = cur_Data[i];
                }
                ( * currentNode.Child_Left).Optimal_Fitness = currentNode.Optimal_Fitness;
                ( * currentNode.Child_Right).Optimal_Fitness = cur_Fitness;
                */
            } else {
                leftChild = new FitnessNode(currentNode, indy);
                rightChild = new FitnessNode(currentNode, currentNode.getIndividual().clone());
                /*
                for (int i = 0; i < dimension; ++i) {
                    ( * currentNode.Child_Left).Optimal_Data[i] = cur_Data[i];
                    ( * currentNode.Child_Right).Optimal_Data[i] = currentNode.Optimal_Data[i];
                }
                ( * currentNode.Child_Left).Optimal_Fitness = cur_Fitness;
                ( * currentNode.Child_Right).Optimal_Fitness = currentNode.Optimal_Fitness;
                */
            }
            
            currentNode.setLeftNode(leftChild);
            currentNode.setRightNode(rightChild);

            //============ Path Back-tracking for optimal fitness
            parentNode = currentNode;
            do {
                if (parentNode.getIndividual().fitness > indy.getFitness()) {
                    parentNode.getIndividual().fitness = indy.getFitness();                    
                    parentNode.getIndividual().data = indy.data.clone();
                    parentNode = parentNode.getParentNode();
                } else {
                    break;
                }
            } while (parentNode != null);
        }
    }
}
