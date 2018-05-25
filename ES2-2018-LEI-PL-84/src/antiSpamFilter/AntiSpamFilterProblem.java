package antiSpamFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleToLongFunction;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

import extras.JARexec;

public class AntiSpamFilterProblem extends AbstractDoubleProblem {
	
		int contador=0;
	
	  public AntiSpamFilterProblem() {
	    // 10 variables (anti-spam filter rules) by default 
	    this(335);
	  }

	  public AntiSpamFilterProblem(Integer numberOfVariables) {
	    setNumberOfVariables(numberOfVariables);
	    setNumberOfObjectives(2);
	    setName("AntiSpamFilterProblem");

	    List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables()) ;
	    List<Double> upperLimit = new ArrayList<>(getNumberOfVariables()) ;

	    for (int i = 0; i < getNumberOfVariables(); i++) {
	      lowerLimit.add(-5.0);
	      upperLimit.add(5.0);
	    }

	    setLowerLimit(lowerLimit);
	    setUpperLimit(upperLimit);
	  }

	  public void evaluate(DoubleSolution solution){
		  /*preencher string cenas com a solution*/
		  String cenas = "";
		  for (int i = 0; i < getNumberOfVariables(); i++) {
			  if (i==0) {
				cenas += ""+solution.getVariableValue(i);
			} else {
				cenas += " "+solution.getVariableValue(i);
			}
		  }
		  /*chamar o jar dando o cenas como atributo*/
		  try {
			  ArrayList<String> x = JARexec.runJAR("C:\\Users\\Sergio-PC\\Desktop\\AntiSpamProblemEvaluate.jar", cenas, 2);
			  for (int i = 0; i < getNumberOfObjectives(); i++) {
				  solution.setObjective(i, Double.parseDouble(x.get(i)));
			  }
		  } catch (IOException e) {
			  for (int i = 0; i < getNumberOfObjectives(); i++) {
				  solution.setObjective(i, 0.0);
			  }
		  }
		  
	  }
}
