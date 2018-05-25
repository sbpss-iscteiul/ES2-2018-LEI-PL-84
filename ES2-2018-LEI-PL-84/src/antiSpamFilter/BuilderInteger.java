package antiSpamFilter;

import java.util.List;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.mocell.MOCellBuilder;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.algorithm.multiobjective.paes.PAESBuilder;
import org.uma.jmetal.algorithm.multiobjective.randomsearch.RandomSearchBuilder;
import org.uma.jmetal.algorithm.multiobjective.smsemoa.SMSEMOABuilder;
import org.uma.jmetal.operator.impl.crossover.IntegerSBXCrossover;
import org.uma.jmetal.operator.impl.mutation.IntegerPolynomialMutation;
import org.uma.jmetal.problem.IntegerProblem;
import org.uma.jmetal.solution.IntegerSolution;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;

public class BuilderInteger {
	
	protected ExperimentProblem<IntegerSolution> experimentProblem;
	protected int maxEvaluations;
	
	public BuilderInteger(int mE) {
		this.maxEvaluations=mE;
	}
	
	public Algorithm<List<IntegerSolution>> getAlgorithm(int i) {
		if(i>0&&i<6) {
			Algorithm<List<IntegerSolution>> algorithm=null;
			switch (i) {
			case 1:
				algorithm = getter_NSGAII();
				return algorithm;
			case 2:	
				algorithm = getter_SMSEMOA() ;
				return algorithm;
			case 3:
				algorithm = getter_MOCell();
				return algorithm;
			case 4:
				algorithm = getter_PAES();
				return algorithm;
			case 5:
				algorithm = getter_RandomSearch();
				return algorithm;
			}	
		}
		return null;
	}

	public void setExperimentProblem(ExperimentProblem<IntegerSolution> eP) {
		this.experimentProblem = eP;
	}
	
	private Algorithm<List<IntegerSolution>> getter_RandomSearch() {
		Algorithm<List<IntegerSolution>> algorithm = new RandomSearchBuilder<IntegerSolution>(
				(IntegerProblem) experimentProblem.getProblem())
				.setMaxEvaluations(maxEvaluations)
				.build();
		return algorithm;
	}

	private Algorithm<List<IntegerSolution>> getter_PAES() {
		Algorithm<List<IntegerSolution>> algorithm = new PAESBuilder<IntegerSolution>(
				(IntegerProblem) experimentProblem.getProblem())
				.setMaxEvaluations(maxEvaluations)
				.setArchiveSize(100)
				.setBiSections(2)
				.setMutationOperator(new IntegerPolynomialMutation(1/experimentProblem.getProblem().getNumberOfVariables(), 20.0))
				.build();
		return algorithm;
	}

	private Algorithm<List<IntegerSolution>> getter_MOCell() {
		Algorithm<List<IntegerSolution>> algorithm = new MOCellBuilder<IntegerSolution>(
				(IntegerProblem) experimentProblem.getProblem(),
				new IntegerSBXCrossover(0.9, 20.0), 
				new IntegerPolynomialMutation(1/experimentProblem.getProblem().getNumberOfVariables(), 20.0))
				.setMaxEvaluations(maxEvaluations)
				.build();
		return algorithm;
	}

	private Algorithm<List<IntegerSolution>> getter_SMSEMOA() {
		Algorithm<List<IntegerSolution>> algorithm = new SMSEMOABuilder<IntegerSolution>(
				(IntegerProblem) experimentProblem.getProblem(), 
				new IntegerSBXCrossover(0.9, 20.0),
				new IntegerPolynomialMutation(1/experimentProblem.getProblem().getNumberOfVariables(), 20.0))
				.setMaxEvaluations(maxEvaluations)
				.build();
		return algorithm;
	}

	private Algorithm<List<IntegerSolution>> getter_NSGAII() {
		Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
	              (IntegerProblem) experimentProblem.getProblem(),
	              new IntegerSBXCrossover(0.9, 20.0),
	              new IntegerPolynomialMutation(1/(experimentProblem.getProblem().getNumberOfVariables()), 20.0))
	              .setMaxEvaluations(maxEvaluations)
	              .setPopulationSize(100)
	              .build();
		return algorithm;
	}
	
}
