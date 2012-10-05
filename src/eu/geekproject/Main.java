/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.geekproject;

import eu.geekproject.initializer.Initializer;
import eu.geekproject.initializer.RangeInitializer;
import eu.geekproject.problem.AckleyProblem;
import eu.geekproject.problem.OptimizationProblem;

/**
 *
 * @author halfdan
 */
public class Main {
    public static void main(String[] args) {
        Initializer init = new RangeInitializer(0.0, 1.0);
        OptimizationProblem opt = new AckleyProblem();
        
        HdEA hdea = new HdEA(opt, 40, 20, 0.95);
        hdea.setInitializer(init);
        hdea.optimize();
    }    
}
