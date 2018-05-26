package antiSpamFilter;

import java.util.List;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.mocell.MOCellBuilder;
import org.uma.jmetal.algorithm.multiobjective.mochc.MOCHCBuilder;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.algorithm.multiobjective.paes.PAESBuilder;
import org.uma.jmetal.algorithm.multiobjective.randomsearch.RandomSearchBuilder;
import org.uma.jmetal.algorithm.multiobjective.smsemoa.SMSEMOABuilder;
import org.uma.jmetal.algorithm.multiobjective.spea2.SPEA2Builder;
import org.uma.jmetal.operator.impl.crossover.HUXCrossover;
import org.uma.jmetal.operator.impl.crossover.SinglePointCrossover;
import org.uma.jmetal.operator.impl.mutation.BitFlipMutation;
import org.uma.jmetal.operator.impl.selection.RandomSelection;
import org.uma.jmetal.operator.impl.selection.RankingAndCrowdingSelection;
import org.uma.jmetal.problem.BinaryProblem;
import org.uma.jmetal.solution.BinarySolution;
import org.uma.jmetal.util.evaluator.impl.SequentialSolutionListEvaluator;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;

public class BuilderBinary {
	
	protected ExperimentProblem<BinarySolution> experimentProblem;
	protected int maxEvaluations;
	
	public BuilderBinary(int mE) {
		this.maxEvaluations=mE;
	}

	public Algorithm<List<BinarySolution>> getAlgorithm(int i) {
		Algorithm<List<BinarySolution>> algorithm=null;
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
			algorithm = getter_MOCH();
			return algorithm;
		case 5:
			algorithm = getter_PAES();
			return algorithm;
		case 6:
			algorithm = getter_RandomSearch();
			return algorithm;
		case 7:
			algorithm = getter_SPEA2() ;
			return algorithm;
		default:
			return null;
		}
	}
	
	public void setExperimentProblem(ExperimentProblem<BinarySolution> eP) {
		this.experimentProblem = eP;
	}
	
	private Algorithm<List<BinarySolution>> getter_SPEA2() {
		 Algorithm<List<BinarySolution>> algorithm = new SPEA2Builder<BinarySolution>(
				 (BinaryProblem) experimentProblem.getProblem(),
				 new SinglePointCrossover(1.0),
				 new BitFlipMutation(1.0 / ((BinaryProblem) experimentProblem.getProblem()).getNumberOfBits(0)))
				 .setMaxIterations(maxEvaluations)
				 .build();
		return algorithm;
	}

	private Algorithm<List<BinarySolution>> getter_RandomSearch() {
		Algorithm<List<BinarySolution>> algorithm = new RandomSearchBuilder<BinarySolution>(
				(BinaryProblem) experimentProblem.getProblem())
				.setMaxEvaluations(maxEvaluations)
				.build();
		return algorithm;
	}
	
	private Algorithm<List<BinarySolution>> getter_PAES() {
		Algorithm<List<BinarySolution>> algorithm = new PAESBuilder<BinarySolution>(
				(BinaryProblem) experimentProblem.getProblem())
				.setMaxEvaluations(maxEvaluations)
				.setArchiveSize(100).setBiSections(2)
				.setMutationOperator( new BitFlipMutation(1.0 / ((BinaryProblem) experimentProblem.getProblem()).getNumberOfBits(0)))
				.build();
		return algorithm;
	}
	
	private Algorithm<List<BinarySolution>> getter_MOCH() {
		Algorithm<List<BinarySolution>> algorithm = new MOCHCBuilder(
				(BinaryProblem) experimentProblem.getProblem())
				.setMaxEvaluations(maxEvaluations)
				.setCrossover(new HUXCrossover(1.0))
				.setNewGenerationSelection(new RankingAndCrowdingSelection<BinarySolution>(100))
				.setCataclysmicMutation(new BitFlipMutation(0.35))
				.setParentSelection(new RandomSelection<BinarySolution>())
				.setEvaluator(new SequentialSolutionListEvaluator<BinarySolution>())
				.build();
		return algorithm;
	}
	
	private Algorithm<List<BinarySolution>> getter_MOCell() {
		Algorithm<List<BinarySolution>> algorithm = new MOCellBuilder<BinarySolution>(
				(BinaryProblem) experimentProblem.getProblem(), 
				new SinglePointCrossover(1.0), 
				new BitFlipMutation(1.0 / ((BinaryProblem) experimentProblem.getProblem()).getNumberOfBits(0)))
				.setMaxEvaluations(maxEvaluations)
				.build();
		return algorithm;
	}
	
	private Algorithm<List<BinarySolution>> getter_SMSEMOA() {
		Algorithm<List<BinarySolution>> algorithm = new SMSEMOABuilder<BinarySolution>(
				(BinaryProblem) experimentProblem.getProblem(), 
				new SinglePointCrossover(1.0), 
				new BitFlipMutation(1.0 / ((BinaryProblem) experimentProblem.getProblem()).getNumberOfBits(0)))
				.setMaxEvaluations(maxEvaluations).build();
		return algorithm;
	}
	
	private Algorithm<List<BinarySolution>> getter_NSGAII() {
	     Algorithm<List<BinarySolution>> algorithm = new NSGAIIBuilder<BinarySolution>(
	             (BinaryProblem) experimentProblem.getProblem(),
	             new SinglePointCrossover(1.0),
	             new BitFlipMutation(1.0 / ((BinaryProblem) experimentProblem.getProblem()).getNumberOfBits(0)))
	             .setMaxEvaluations(maxEvaluations)
	             .setPopulationSize(100)
	             .build();
		return algorithm;
	}

	public ExperimentProblem<BinarySolution> geExperimentProblem() {
		return this.experimentProblem;
	}

	public int getMaxEvaluations() {
		return this.maxEvaluations;
	}
}
