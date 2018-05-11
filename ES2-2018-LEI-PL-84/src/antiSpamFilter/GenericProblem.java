package antiSpamFilter;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.ConstrainedProblem;
import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

public class GenericProblem extends AbstractDoubleProblem implements ConstrainedProblem<DoubleSolution>{
	
	
	public GenericProblem(int numberOfVariables,int numberOfObjectives,int numberOfConstraints,String problemName) {
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
		  
		double[] x = new double[getNumberOfVariables()];
		for (int i = 0; i < solution.getNumberOfVariables(); i++) {
		x[i] = solution.getVariableValue(i) ;
		}
		
		/*
		 *FN e FP têm de vir de jars para o problema AntiSpamFilter 
		 */
		double FN = 0;
		double FP = 0;
		
		solution.setObjective(0, FN);
		solution.setObjective(1, FP);
				
	}

	
	@Override
	public void evaluateConstraints(DoubleSolution solution) {
		
	}

}