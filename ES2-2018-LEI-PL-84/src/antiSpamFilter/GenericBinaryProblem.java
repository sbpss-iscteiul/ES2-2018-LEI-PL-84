package antiSpamFilter;

import java.util.BitSet;

import org.uma.jmetal.problem.ConstrainedProblem;
import org.uma.jmetal.problem.impl.AbstractBinaryProblem;
import org.uma.jmetal.solution.BinarySolution;
import org.uma.jmetal.solution.impl.DefaultBinarySolution;
import org.uma.jmetal.util.JMetalException;

public class GenericBinaryProblem extends AbstractBinaryProblem implements ConstrainedProblem<BinarySolution>{
	
	private static final long serialVersionUID = 1L;
	private int bits;

	public GenericBinaryProblem(int numberOfVariables,int numberOfObjectives,int numberOfConstraints,String problemName,int numberOfBits) {
	    setNumberOfVariables(numberOfVariables);
	    setNumberOfObjectives(numberOfObjectives);
	    setNumberOfConstraints(numberOfConstraints);
	    setName(problemName);
	    
	    bits = numberOfBits;
	}

	public void setRestrictions() {
		
	}

	@Override
	public BinarySolution createSolution() {
		// TODO Auto-generated method stub
		return new DefaultBinarySolution(this);
	}

	public void evaluate(BinarySolution solution) {
		int counterOnes;
	    int counterZeroes;

	    counterOnes = 0;
	    counterZeroes = 0;

	    BitSet bitset = solution.getVariableValue(0) ;

	    for (int i = 0; i < bitset.length(); i++) {
	      if (bitset.get(i)) {
	        counterOnes++;
	      } else {
	        counterZeroes++;
	      }
	    }

	    // OneZeroMax is a maximization problem: multiply by -1 to minimize
	    solution.setObjective(0, -1.0 * counterOnes);
	    solution.setObjective(1, -1.0 * counterZeroes);
	  }

	@Override
	protected int getBitsPerVariable(int arg0) {
//	  	if (arg0 != 0) {
//	  		throw new JMetalException("Problem GenericBinaryProblem has only a variable. Index = " + arg0) ;
//	  	}
		return this.bits;
	}

	@Override
	public void evaluateConstraints(BinarySolution arg0) {
		// TODO Auto-generated method stub
		
	}

}