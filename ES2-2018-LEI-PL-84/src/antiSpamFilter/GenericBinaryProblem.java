package antiSpamFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;

import org.uma.jmetal.problem.ConstrainedProblem;
import org.uma.jmetal.problem.impl.AbstractBinaryProblem;
import org.uma.jmetal.solution.BinarySolution;
import org.uma.jmetal.solution.impl.DefaultBinarySolution;
import org.uma.jmetal.util.JMetalException;

import extras.JARexec;

public class GenericBinaryProblem extends AbstractBinaryProblem implements ConstrainedProblem<BinarySolution>{
	
	private static final long serialVersionUID = 1L;
	private int bits;
	private String pathName;

	public GenericBinaryProblem(int numberOfVariables,int numberOfObjectives,int numberOfConstraints,String problemName,int numberOfBits,String Path) {
	    setNumberOfVariables(numberOfVariables);
	    setNumberOfObjectives(numberOfObjectives);
	    setNumberOfConstraints(numberOfConstraints);
	    setName(problemName);
	    this.bits = numberOfBits;
	    this.pathName=Path;
	}

	public void setRestrictions() {
		
	}

	@Override
	public BinarySolution createSolution() {
		// TODO Auto-generated method stub
		return new DefaultBinarySolution(this);
	}

	public void evaluate(BinarySolution solution) {	    
		  /*preencher string cenas com a solution*/
		  String cenas = "";
		  for (int i = 0; i < getNumberOfVariables(); i++) {
			  if (i==0) {
				cenas += ""+solution.getVariableValue(i);
			  } else {
				cenas += " "+solution.getVariableValue(i);
			  }
		  } 
		  /*chamar o jar dando a String como atributo*/
		  try {
			  ArrayList<String> x = JARexec.runJAR(this.pathName, cenas, getNumberOfObjectives());
			  for (int i = 0; i < getNumberOfObjectives(); i++) {
				  solution.setObjective(i, Double.parseDouble(x.get(i)));
			  }
		  } catch (IOException e) {
			  for (int i = 0; i < getNumberOfObjectives(); i++) {
				  solution.setObjective(i, 0.0);
			  }
		  }	
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
	
	public int getNBits() {
		return this.bits;
	}
	
	public String getPath() {
		return this.pathName;
	}

}