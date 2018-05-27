package antiSpamFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.ConstrainedProblem;
import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

import extras.JARexec;

public class GenericDoubleProblem extends AbstractDoubleProblem implements ConstrainedProblem<DoubleSolution>{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pathName;
 

	public GenericDoubleProblem(int numberOfVariables,int numberOfObjectives,int numberOfConstraints,String problemName,String Path) {
	    setNumberOfVariables(numberOfVariables);
	    setNumberOfObjectives(numberOfObjectives);
	    setNumberOfConstraints(numberOfConstraints);
	    setName(problemName);
	    this.pathName=Path;
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
		  /*preencher string cenas com a solution*/
		  String cenas = getNumberOfObjectives()+" ";
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
	public void evaluateConstraints(DoubleSolution solution) {
		
	}

	public String getPath() {
		return this.pathName;
	}

}