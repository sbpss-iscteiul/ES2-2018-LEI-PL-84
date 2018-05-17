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
	public void evaluate(IntegerSolution arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void evaluateConstraints(IntegerSolution arg0) {
		// TODO Auto-generated method stub
		
	}


}