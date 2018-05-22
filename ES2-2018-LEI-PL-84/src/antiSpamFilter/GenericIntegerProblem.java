package antiSpamFilter;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.ConstrainedProblem;
import org.uma.jmetal.problem.impl.AbstractIntegerProblem;
import org.uma.jmetal.solution.IntegerSolution;

public class GenericIntegerProblem extends AbstractIntegerProblem implements ConstrainedProblem<IntegerSolution>{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GenericIntegerProblem(int numberOfVariables,int numberOfObjectives,int numberOfConstraints,String problemName) {
	    setNumberOfVariables(numberOfVariables);
	    setNumberOfObjectives(numberOfObjectives);
	    setNumberOfConstraints(numberOfConstraints);
	    setName(problemName);
	}
	
	public void setLimits(Integer lLimit,Integer uLimit) {
		List<Integer> lowerLimit = new ArrayList<>(getNumberOfVariables()) ;
	    List<Integer> upperLimit = new ArrayList<>(getNumberOfVariables()) ;

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
	public void evaluate(IntegerSolution solution) {
		int approximationToN;
	    int approximationToM ;

	    approximationToN = 0;
	    approximationToM = 0;

	    for (int i = 0; i < solution.getNumberOfVariables(); i++) {
	      int value = solution.getVariableValue(i) ;
	      approximationToN += Math.abs(getNumberOfVariables() - value) ;
	      approximationToM += Math.abs((0-getNumberOfVariables()) - value) ;
	    }

	    solution.setObjective(0, approximationToN);
	    solution.setObjective(1, approximationToM);
	}

	@Override
	public void evaluateConstraints(IntegerSolution arg0) {
		// TODO Auto-generated method stub
		
	}


}