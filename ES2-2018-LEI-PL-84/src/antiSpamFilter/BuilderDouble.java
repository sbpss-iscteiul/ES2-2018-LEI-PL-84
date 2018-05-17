package antiSpamFilter;

import java.util.List;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.gde3.GDE3Builder;
import org.uma.jmetal.algorithm.multiobjective.ibea.IBEABuilder;
import org.uma.jmetal.algorithm.multiobjective.mocell.MOCellBuilder;
import org.uma.jmetal.algorithm.multiobjective.moead.MOEADBuilder;
import org.uma.jmetal.algorithm.multiobjective.moead.MOEADBuilder.Variant;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.algorithm.multiobjective.paes.PAESBuilder;
import org.uma.jmetal.algorithm.multiobjective.randomsearch.RandomSearchBuilder;
import org.uma.jmetal.algorithm.multiobjective.smsemoa.SMSEMOABuilder;
import org.uma.jmetal.operator.impl.crossover.SBXCrossover;
import org.uma.jmetal.operator.impl.mutation.PolynomialMutation;
import org.uma.jmetal.problem.DoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;

public class BuilderDouble extends Builder<DoubleSolution> {

	public BuilderDouble(ExperimentProblem<DoubleSolution> experimentProblem,int mE) {
		super(experimentProblem,mE);
	}

	@Override
	public Algorithm<List<DoubleSolution>> getAlgorithm(int i) {
		if(i>0&&i<9) {
			Algorithm<List<DoubleSolution>> algorithm=null;
			switch (i) {
			case 1:
				algorithm = getter_NSGAII();
				return algorithm;
			case 2:	
				algorithm = getter_SMSEMOA() ;
				return algorithm;
			case 3:
				algorithm = getter_GDE3();
				return algorithm;
			case 4:
				algorithm = getter_IBEA();
				return algorithm;
			case 5:
				algorithm = getter_MOCell();
				return algorithm;
			case 6:
				algorithm = getter_MOEAD();
				return algorithm;
			case 7:
				algorithm = getter_PAES() ;
				return algorithm;
			case 8:
				algorithm = getter_RandomSearch() ;
				return algorithm;
			}	
		}
		return null;
	}

	private Algorithm<List<DoubleSolution>> getter_RandomSearch() {
		Algorithm<List<DoubleSolution>> algorithm = new RandomSearchBuilder<DoubleSolution>(
				(DoubleProblem) experimentProblem.getProblem())
				.setMaxEvaluations(maxEvaluations)
				.build();
		return algorithm;
	}

	private Algorithm<List<DoubleSolution>> getter_PAES() {
		Algorithm<List<DoubleSolution>> algorithm = new PAESBuilder<DoubleSolution>(
				(DoubleProblem) experimentProblem.getProblem())
				.setMaxEvaluations(maxEvaluations)
				.setArchiveSize(100)
				.setBiSections(2)
				.setMutationOperator(new PolynomialMutation(1.0 / experimentProblem.getProblem().getNumberOfVariables(), 10.0))
				.build();
		return algorithm;
	}

	private Algorithm<List<DoubleSolution>> getter_MOEAD() {
		Algorithm<List<DoubleSolution>> algorithm = new MOEADBuilder(
				(DoubleProblem) experimentProblem.getProblem(),Variant.MOEAD)
				.setMaxEvaluations(maxEvaluations)
				.build();
		return algorithm;
	}

	private Algorithm<List<DoubleSolution>> getter_MOCell() {
		Algorithm<List<DoubleSolution>> algorithm = new MOCellBuilder<DoubleSolution>(
				(DoubleProblem) experimentProblem.getProblem(),
				new SBXCrossover(1.0, 5), 
				new PolynomialMutation(1.0 / experimentProblem.getProblem().getNumberOfVariables(), 10.0))
				.setMaxEvaluations(maxEvaluations)
				.build();
		return algorithm;
	}

	private Algorithm<List<DoubleSolution>> getter_IBEA() {
		Algorithm<List<DoubleSolution>> algorithm = new IBEABuilder(
				(DoubleProblem) experimentProblem.getProblem())
				.setMaxEvaluations(maxEvaluations)
				.build();
		return algorithm;
	}

	private Algorithm<List<DoubleSolution>> getter_GDE3() {
		Algorithm<List<DoubleSolution>> algorithm = new GDE3Builder(
				(DoubleProblem) experimentProblem.getProblem())
				.setMaxEvaluations(maxEvaluations)
				.build();
		return algorithm;
	}

	private Algorithm<List<DoubleSolution>> getter_SMSEMOA() {
		Algorithm<List<DoubleSolution>> algorithm = new SMSEMOABuilder<DoubleSolution>(
				(DoubleProblem) experimentProblem.getProblem(), 
				new SBXCrossover(1.0, 5), 
				new PolynomialMutation(1.0 / experimentProblem.getProblem().getNumberOfVariables(), 10.0))
				.setMaxEvaluations(maxEvaluations)
				.build();
		return algorithm;
	}

	private Algorithm<List<DoubleSolution>> getter_NSGAII() {
	      Algorithm<List<DoubleSolution>> algorithm = new NSGAIIBuilder<DoubleSolution>(
	    		  (DoubleProblem) experimentProblem.getProblem(),
	              new SBXCrossover(1.0, 5),
	              new PolynomialMutation(1.0 / experimentProblem.getProblem().getNumberOfVariables(), 10.0))
	              .setMaxEvaluations(maxEvaluations)
	              .setPopulationSize(100)
	              .build();
		return algorithm;
	}
}
