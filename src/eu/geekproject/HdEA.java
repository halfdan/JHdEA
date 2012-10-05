/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.geekproject;

import eu.geekproject.problem.OptimizationProblem;
import eu.geekproject.initializer.Initializer;
import eu.geekproject.tree.FitnessTree;
import eu.geekproject.tree.FitnessNode;
import java.util.Random;

/**
 *
 * @author halfdan
 */
public class HdEA {

    private OptimizationProblem optimizationProblem;
    private Individual[] population;
    private int dimension;
    private int currentGeneration;
    private int populationSize;
    private double crossoverRate;
    private Initializer initializer;
    private FitnessTree fitnessTree;

    public HdEA(OptimizationProblem function, int dimension, int populationSize, double crossoverRate) {
        this.optimizationProblem = function;
        this.population = new Individual[populationSize];
        this.dimension = dimension;
        this.populationSize = populationSize;
        this.crossoverRate = crossoverRate;
    }

    public void setInitializer(Initializer init) {
        this.initializer = init;
    }

    public void setCrossoverRate(double crossoverRate) {
        this.crossoverRate = crossoverRate;
    }

    public void setOptimizationProblem(OptimizationProblem opt) {
        this.optimizationProblem = opt;
    }

    public void initializePopulation() {
        for (int i = 0; i < this.populationSize; i++) {
            Individual indy = new Individual(this.dimension);
            this.initializer.initialize(indy);
            double fitness = this.optimizationProblem.evaluate(indy.getData());
            indy.setFitness(fitness);
            this.population[i] = indy;
            fitnessTree.Insert(indy);
        }
    }

    private void mutate(Individual indy) {        
        double individualDistance;
        double targetFitness, Ap, currentGene;
        FitnessNode targetNode;

        //============ Search the leaf node
        targetNode = this.fitnessTree.getRootNode();
        while (targetNode.getDimension() > -1) {
            if (indy.data[targetNode.getDimension()] < targetNode.getThreshold()) {
                targetNode = targetNode.getLeftNode();
            } else {
                targetNode = targetNode.getRightNode();
            }
        }

        targetFitness = targetNode.getIndividual().getFitness();

        while (targetNode.getParentNode() != null && targetNode.getParentNode().getIndividual().getFitness() < targetFitness) {
            targetNode = targetNode.getParentNode();
        }

        FitnessNode_Interval(tgr_Node, cur_Tree);

        individualDistance = 0.0;
        double[] targetData = targetNode.getIndividual().getData();
        double[] indyData = indy.getData();
        for (int i = 0; i < this.dimension; ++i) {
            individualDistance += Math.abs(indyData[i] - targetData[i]);
        }

        if (individualDistance < 1e-8) {
            for (i = 0; i < this.dimension; ++i) {
                Ap = (double) (rand() + 1.0) / (RAND_MAX + 1.0);
                cur_gene = ( * cur_Individual)[i];
                ( * cur_Individual)[i] = cur_gene + Ap * 2.0 * ((double) (rand() % 2) - 0.5) * ((double) (rand() + 1) / (RAND_MAX + 1.0)) * (( * cur_Tree).cur_Interval[i][1] - ( * cur_Tree).cur_Interval[i][0]);
                while (( * cur_Individual)[i] < Search_Space[i][0] || ( * cur_Individual)[i] > Search_Space[i][1]) {
                    ( * cur_Individual)[i] = cur_gene + Ap * 2.0 * ((double) (rand() % 2) - 0.5) * ((double) (rand() + 1) / (RAND_MAX + 1.0)) * (( * cur_Tree).cur_Interval[i][1] - ( * cur_Tree).cur_Interval[i][0]);
                }
            }
        } else {
            Ap = (double) (rand() + 1.0) / (RAND_MAX + 1.0);
            for (i = 0; i < this.dimension; ++i) {
                ( * cur_Individual)[i] = Ap * ( * cur_Individual)[i] + (1.0 - Ap) * ( * tgr_Node).Optimal_Data[i];
            }
        }

    }

    public void optimize() {
        Random rand = new Random();
        fitnessTree = new FitnessTree(this.dimension);
        // Initialize the Population
        initializePopulation();

        // Next generation
        currentGeneration++;
        /*
         // Add current best fitness to array
         (*cur_EA).EA_Info.Convergence = (double*)realloc((*cur_EA).EA_Info.Convergence, sizeof(double) * (*cur_EA).EA_Info.no_Generation);
         (*cur_EA).EA_Info.Convergence[(*cur_EA).EA_Info.no_Generation - 1] = (*cur_EA).EA_Info.Optimal_Fitness;

         // Add time for this generation to array.
         (*cur_EA).EA_Info.Processing_Time = (double*)realloc((*cur_EA).EA_Info.Processing_Time, sizeof(double) * (*cur_EA).EA_Info.no_Generation);
         (*cur_EA).EA_Info.Processing_Time[(*cur_EA).EA_Info.no_Generation - 1] = (double) (clock() - clock_begin) / CLOCKS_PER_SEC;
         */

        Individual[] newPopulation = new Individual[populationSize];

        do {
            //----------- Mutation
            for (int i = 0; i < this.populationSize; ++i) {
                // Copy individual from current population to temporary population
                newPopulation[i] = population[i].clone();

                // Mutate current ind. in temporary population
                this.Mutate(newPopulation[i], this.dimension, ( * cur_EA).EA_Info.SearchSpace);
            }

            int index, index2;
            //------------ Cross-Over
            for (int i = 0; i < this.populationSize; ++i) {
                index = rand.nextInt() % this.populationSize;
                index2 = rand.nextInt() % this.populationSize;
                while (index2 == index) {
                    index2 = rand.nextInt() % this.populationSize;
                }

                CrossOver(( * cur_EA).EA_Info.cur_Population[i],
                        ( * cur_EA).EA_Info.tmp_Population[individual_index_2],
                         & ( * cur_EA).EA_Info.nxt_Population[i],
                         & unused_individual,
                        ( * cur_EA).EA_Info.no_Dimension,
                        1);
            }

            //------------ Fitness Measurement
            for (int i = 0; i < this.populationSize; ++i) {
                ( * cur_EA).EA_Info.nxt_Fitness[i] = (1.0 - 2.0 * optimization_mode) * ( * fn_ptr) (( * cur_EA).EA_Info.nxt_Population[i]);

                FitnessTree_Insertion( & ( * cur_EA).Fitness_Tree, ( * cur_EA).EA_Info.nxt_Population[i], ( * cur_EA).EA_Info.nxt_Fitness[i]);
            }

            //------------ Selection
            Parallel1Plus1_Selection( & ( * cur_EA).EA_Info);

            if (terminate_mode == 2) {
                if (( * cur_EA).EA_Info.no_Generation > 0) {
                    if ((prev_optimum - ( * cur_EA).EA_Info.Optimal_Fitness) / prev_optimum < terminate_parameter) {
                        ++no_stopping_generation;
                    } else {
                        no_stopping_generation = 0;
                    }
                }
                prev_optimum = ( * cur_EA).EA_Info.Optimal_Fitness;
            }

            this.currentGeneration++;

            ( * cur_EA).EA_Info.Convergence = (double *) realloc(( * cur_EA).EA_Info.Convergence, sizeof(double) * ( * cur_EA).EA_Info.no_Generation);
            ( * cur_EA).EA_Info.Convergence[( * cur_EA).EA_Info.no_Generation - 1] = ( * cur_EA).EA_Info.Optimal_Fitness;

            ( * cur_EA).EA_Info.Processing_Time = (double *) realloc(( * cur_EA).EA_Info.Processing_Time, sizeof(double) * ( * cur_EA).EA_Info.no_Generation);
            ( * cur_EA).EA_Info.Processing_Time[( * cur_EA).EA_Info.no_Generation - 1] = (double) (clock() - clock_begin) / CLOCKS_PER_SEC;

            if (this.currentGeneration % 200 == 0) {
                System.out.printf("%dth iteration = %1.10lf\n", this.currentGeneration, ( * cur_EA).EA_Info.Optimal_Fitness);
            }

        } while ((terminate_mode == 0 && terminate_parameter > this.currentGeneration
                || (terminate_mode == 1 && ((1.0 - 2.0 * optimization_mode) * terminate_parameter < ( * cur_EA).EA_Info.Optimal_Fitness) && ( * cur_EA).EA_Info.no_Generation < HDEA_MAX_GENERATION)
                || (terminate_mode == 2 && no_stopping_generation < HDEA_STOPPING_GENERATION));

    }
}
