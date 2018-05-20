package antiSpamFilter;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.ConstrainedProblem;
import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

public class GenericDoubleProblem extends AbstractDoubleProblem implements ConstrainedProblem<DoubleSolution>{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public GenericDoubleProblem(int numberOfVariables,int numberOfObjectives,int numberOfConstraints,String problemName) {
	    setNumberOfVariables(numberOfVariables);
	    setNumberOfObjectives(numberOfObjectives);
	    setNumberOfConstraints(numberOfConstraints);
	    setName(problemName);
	}
	
	public void setLimits(double lLimit,double uLimit) {
		List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables()) ;
	    List<Double> upperLimit = new ArrayList<>(getNumberOfVariables()) ;

	    for (int i = 0; i < getNumberOfVariables(); i++) {
	      lowerLimit.add(lLimit);
	      upperLimit.add(uLimit);
	    }

	    setLowerLimit(lowerLimit);
	    setUpperLimit(upperLimit);
	}

	public void setRestrictions() {
		
	}
	
	@Override
	public void evaluate(DoubleSolution solution) {
		  
//		double[] x = new double[getNumberOfVariables()];
//		for (int i = 0; i < solution.getNumberOfVariables(); i++) {
//		x[i] = solution.getVariableValue(i) ;
//		}
//		
//		/*
//		 *FN e FP têm de vir de jars para o problema AntiSpamFilter 
//		 */
//		double FN = 0;
//		double FP = 0;
		
//		solution.setObjective(0, FN);
//		solution.setObjective(1, FP);
		/*Caso de Teste*/
	    double[] fx = new double[getNumberOfObjectives()];
	    double[] x = new double[getNumberOfVariables()];
	    for (int i = 0; i < solution.getNumberOfVariables(); i++) {
	      x[i] = solution.getVariableValue(i) ;
	    }

	    fx[0] = 0.0;
	    for (int var = 0; var < solution.getNumberOfVariables() - 1; var++) {
		  fx[0] += Math.abs(x[0]); // Example for testing
	    }
	    
	    fx[1] = 0.0;
	    for (int var = 0; var < solution.getNumberOfVariables(); var++) {
	    	fx[1] += Math.abs(x[1]); // Example for testing
	    }

	    solution.setObjective(0, fx[0]);
	    solution.setObjective(1, fx[1]);
		/*-------------*/		
	}

	
	@Override
	public void evaluateConstraints(DoubleSolution solution) {
		
	}

}