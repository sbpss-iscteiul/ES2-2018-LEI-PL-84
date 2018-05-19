package antiSpamFilter;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.ConstrainedProblem;
import org.uma.jmetal.problem.impl.AbstractBinaryProblem;
import org.uma.jmetal.solution.BinarySolution;

public class GenericBinaryProblem extends AbstractBinaryProblem implements ConstrainedProblem<BinarySolution>{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GenericBinaryProblem(int numberOfVariables,int numberOfObjectives,int numberOfConstraints,String problemName) {
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
	}

	public void setRestrictions() {
		
	}

	@Override
	public BinarySolution createSolution() {
		// TODO Auto-generated method stub
		return null;
	}

	public void evaluate(BinarySolution arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected int getBitsPerVariable(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void evaluateConstraints(BinarySolution arg0) {
		// TODO Auto-generated method stub
		
	}

}