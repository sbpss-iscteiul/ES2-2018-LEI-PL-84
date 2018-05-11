package antiSpamFilter;

import java.util.List;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.abyss.ABYSSBuilder;
import org.uma.jmetal.algorithm.multiobjective.dmopso.DMOPSOBuilder;
import org.uma.jmetal.algorithm.multiobjective.gde3.GDE3Builder;
import org.uma.jmetal.algorithm.multiobjective.ibea.IBEABuilder;
import org.uma.jmetal.algorithm.multiobjective.mocell.MOCellBuilder;
import org.uma.jmetal.algorithm.multiobjective.moead.MOEAD;
import org.uma.jmetal.algorithm.multiobjective.moead.MOEADBuilder;
import org.uma.jmetal.algorithm.multiobjective.moead.MOEADBuilder.Variant;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder.NSGAIIVariant;
import org.uma.jmetal.algorithm.multiobjective.nsgaiii.NSGAIIIBuilder;
import org.uma.jmetal.algorithm.multiobjective.omopso.OMOPSOBuilder;
import org.uma.jmetal.algorithm.multiobjective.paes.PAESBuilder;
import org.uma.jmetal.algorithm.multiobjective.pesa2.PESA2Builder;
import org.uma.jmetal.algorithm.multiobjective.randomsearch.RandomSearchBuilder;
import org.uma.jmetal.algorithm.multiobjective.rnsgaii.RNSGAIIBuilder;
import org.uma.jmetal.algorithm.multiobjective.smpso.SMPSOBuilder;
import org.uma.jmetal.algorithm.multiobjective.smsemoa.SMSEMOABuilder;
import org.uma.jmetal.algorithm.multiobjective.spea2.SPEA2Builder;
import org.uma.jmetal.operator.impl.crossover.SBXCrossover;
import org.uma.jmetal.operator.impl.mutation.PolynomialMutation;
import org.uma.jmetal.problem.DoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.solution.impl.DefaultBinarySolution;
import org.uma.jmetal.util.archive.impl.CrowdingDistanceArchive;
import org.uma.jmetal.util.evaluator.impl.SequentialSolutionListEvaluator;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;

public class Builders {
	
	public Builders() {
		
	}
	
	public Algorithm<List<DoubleSolution>> getAlgorithm(ExperimentProblem<DoubleSolution> experimentProblem, int i){
		if(i>=0&&i<15) {
			Algorithm<List<DoubleSolution>> algorithm=null;
			switch (i) {
			case 1:
				algorithm = getter_ABYSS(experimentProblem);
				return algorithm;
			case 2:	
				algorithm = getter_DMOPSO(experimentProblem) ;
				return algorithm;
			case 3:
				algorithm = getter_GDE3(experimentProblem);
				return algorithm;
			case 4:
				algorithm = getter_IBEA(experimentProblem);
				return algorithm;
			case 5:
				algorithm = getter_MOCell(experimentProblem);
				return algorithm;
			case 6:
				algorithm = getter_MOEAD(experimentProblem);
				return algorithm;
			case 7:
				algorithm = getter_NSGAII(experimentProblem) ;
				return algorithm;
//			case 8:
//				algorithm = getter_NSGAIII(experimentProblem);
//				return algorithm;
//			case 9:
//				algorithm = getter_OMOPSO(experimentProblem);
//				return algorithm;
//			case 10:
//				algorithm = getter_PAES(experimentProblem);
//				return algorithm;
//			case 11:
//				algorithm = getter_PESA2(experimentProblem);
//				return algorithm;
			case 12:
				algorithm = getter_RandomSearch(experimentProblem);
				return algorithm;
			case 13:
				algorithm = getter_RNSGAII(experimentProblem);
				return algorithm;
			case 14:
				algorithm = getter_SMPSO(experimentProblem);
				return algorithm;
			case 15:
				algorithm = getter_SMSEMOA(experimentProblem);
				return algorithm;
//			case 16:
//				algorithm = getter_SPEA2(experimentProblem);
//				return algorithm;
			}	
		}
		return null;
	}
	
	/*
	 * Aviso
	 * Cada algoritmo recebe uma lista de problemas e um iterador que é usado no 'get' do problema 
	 * estava a haver probs na passagem do problema em si logo foi improvisada uma solução.
	 * */
	private Algorithm<List<DoubleSolution>> getter_NSGAII(ExperimentProblem<DoubleSolution> problem){
		  Algorithm<List<DoubleSolution>> algorithm = new NSGAIIBuilder<>(
	              problem.getProblem(),
	              new SBXCrossover(1.0, 5),
	              new PolynomialMutation(1.0 / problem.getProblem().getNumberOfVariables(), 10.0))
	              .setMaxEvaluations(500)
	              .setPopulationSize(100)
	              .build();
		  return algorithm;
	  }
	  
	  private Algorithm<List<DoubleSolution>> getter_MOCell(ExperimentProblem<DoubleSolution> problem){
		  Algorithm<List<DoubleSolution>> algorithm = new MOCellBuilder<>(
	            problem.getProblem(),
	            new SBXCrossover(1.0, 5),
	            new PolynomialMutation(1.0 / problem.getProblem().getNumberOfVariables(), 10.0))
	            .setMaxEvaluations(500)
	            .setPopulationSize(100)
	            .build();
		  return algorithm;
	  }
	  
	  private Algorithm<List<DoubleSolution>> getter_SPEA2 (ExperimentProblem<DoubleSolution> problem){
		  Algorithm<List<DoubleSolution>> algorithm = new SPEA2Builder<DoubleSolution>(
	              problem.getProblem(),
	              new SBXCrossover(1.0, 10.0),
	              new PolynomialMutation(1.0 / problem.getProblem().getNumberOfVariables(), 20.0))
	              .setMaxIterations(500)
	              .setPopulationSize(100)
	              .build();
		  return algorithm;
	  }
	  
	  private Algorithm<List<DoubleSolution>> getter_SMPSO (ExperimentProblem<DoubleSolution> problem){
	      Algorithm<List<DoubleSolution>> algorithm = new SMPSOBuilder((DoubleProblem) problem.getProblem(),
	              new CrowdingDistanceArchive<DoubleSolution>(100))
	              .setMaxIterations(500)
	              .setSwarmSize(100)
	              .build();
		  return algorithm;
	  }
	  
	  private Algorithm<List<DoubleSolution>> getter_GDE3 (ExperimentProblem<DoubleSolution> problem){
	      Algorithm<List<DoubleSolution>> algorithm = new GDE3Builder((DoubleProblem) problem.getProblem())
	              .setMaxEvaluations(500)
	              .setPopulationSize(100)
	              .build();
		  return algorithm;
	  }
	  
	  private Algorithm<List<DoubleSolution>> getter_ABYSS (ExperimentProblem<DoubleSolution> problem){
	      Algorithm<List<DoubleSolution>> algorithm = new ABYSSBuilder((DoubleProblem) problem.getProblem(),
	              new CrowdingDistanceArchive<DoubleSolution>(100))
	              .setMaxEvaluations(500)
	              .setPopulationSize(100)
	              .build();
		  return algorithm;
	  }
	  
	  private Algorithm<List<DoubleSolution>> getter_DMOPSO (ExperimentProblem<DoubleSolution> problem){
	      Algorithm<List<DoubleSolution>> algorithm = new DMOPSOBuilder((DoubleProblem) problem.getProblem())
	    		  .setMaxIterations(500)
	    		  .setSwarmSize(100)
	              .build();
		  return algorithm;
  }
	  	  
	  private Algorithm<List<DoubleSolution>> getter_IBEA(ExperimentProblem<DoubleSolution> problem){
	      Algorithm<List<DoubleSolution>> algorithm = new IBEABuilder((DoubleProblem) problem.getProblem())
	    		  .setMaxEvaluations(500)
	    		  .setPopulationSize(100)
	              .build();
		  return algorithm;
	  }
	  
	  private Algorithm<List<DoubleSolution>> getter_MOEAD (ExperimentProblem<DoubleSolution> problem){
	      Algorithm<List<DoubleSolution>> algorithm = new MOEADBuilder((DoubleProblem) problem.getProblem(), Variant.MOEAD)
    		  .setMaxEvaluations(500)
    		  .setPopulationSize(100)
              .build();
	      return algorithm;
 	  }
	 
	  private Algorithm<List<DoubleSolution>> getter_NSGAIII (ExperimentProblem<DoubleSolution> problem){
	      Algorithm<List<DoubleSolution>> algorithm = new NSGAIIIBuilder<DoubleSolution>((DoubleProblem) problem.getProblem())
	    		  .setMaxIterations(500)
	    		  .setPopulationSize(100)
	              .build();
		  return algorithm;
	  }
	  
	  private Algorithm<List<DoubleSolution>> getter_OMOPSO (ExperimentProblem<DoubleSolution> problem){
			Algorithm<List<DoubleSolution>> algorithm = new OMOPSOBuilder((DoubleProblem) problem.getProblem(),new SequentialSolutionListEvaluator<DoubleSolution>())
					.setMaxIterations(500)
					.setSwarmSize(100)
					.build();
			return algorithm;
	  }

	  /*
	   * Carefull ArchiveSize? biSections)
	   */
	  private Algorithm<List<DoubleSolution>> getter_PAES (ExperimentProblem<DoubleSolution> problem){
	      Algorithm<List<DoubleSolution>> algorithm = new PAESBuilder<DoubleSolution>((DoubleProblem) problem.getProblem())
	    		  .setMaxEvaluations(500)
	              .build();
		  return algorithm;	  
	  }
	  
	  private Algorithm<List<DoubleSolution>> getter_PESA2 (ExperimentProblem<DoubleSolution> problem){
		  Algorithm<List<DoubleSolution>> algorithm = new PESA2Builder<>(
	              problem.getProblem(),
	              new SBXCrossover(1.0, 5),
	              new PolynomialMutation(1.0 / problem.getProblem().getNumberOfVariables(), 10.0))
	              .setMaxEvaluations(500)
	              .setPopulationSize(100)
	              .build();
		  return algorithm;
	  }
	  
	  private Algorithm<List<DoubleSolution>> getter_RandomSearch (ExperimentProblem<DoubleSolution> problem){
	      Algorithm<List<DoubleSolution>> algorithm = new RandomSearchBuilder<DoubleSolution>((DoubleProblem) problem.getProblem())
	    		  .setMaxEvaluations(500)
	              .build();
		  return algorithm;
	  }
	  
	  /* 
	   *Não Funciona 
	   */
	  private Algorithm<List<DoubleSolution>> getter_RNSGAII (ExperimentProblem<DoubleSolution> problem){
//	      Algorithm<List<DoubleSolution>> algorithm = new RNSGAIIBuilder<DoubleSolution>((DoubleProblem) problem.getProblem(), null, null, null, (Double) null)
//	    		  .setMaxEvaluations(500)
//	              .build();
//		  return algorithm;
		  return null;
	  }
	  
	  private Algorithm<List<DoubleSolution>> getter_SMSEMOA (ExperimentProblem<DoubleSolution> problem){
		  Algorithm<List<DoubleSolution>> algorithm = new SMSEMOABuilder<>(
	              problem.getProblem(),
	              new SBXCrossover(1.0, 5),
	              new PolynomialMutation(1.0 / problem.getProblem().getNumberOfVariables(), 10.0))
	              .setMaxEvaluations(500)
	              .setPopulationSize(100)
	              .build();
		  return algorithm;
	  }

}