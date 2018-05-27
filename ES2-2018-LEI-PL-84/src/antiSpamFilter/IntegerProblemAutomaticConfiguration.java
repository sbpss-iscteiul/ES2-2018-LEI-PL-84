package antiSpamFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.problem.impl.AbstractIntegerProblem;
import org.uma.jmetal.problem.multiobjective.NMMin;
import org.uma.jmetal.qualityindicator.impl.hypervolume.PISAHypervolume;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.solution.IntegerSolution;
import org.uma.jmetal.util.experiment.Experiment;
import org.uma.jmetal.util.experiment.ExperimentBuilder;
import org.uma.jmetal.util.experiment.component.ComputeQualityIndicators;
import org.uma.jmetal.util.experiment.component.ExecuteAlgorithms;
import org.uma.jmetal.util.experiment.component.GenerateBoxplotsWithR;
import org.uma.jmetal.util.experiment.component.GenerateLatexTablesWithStatistics;
import org.uma.jmetal.util.experiment.component.GenerateReferenceParetoFront;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;

public class IntegerProblemAutomaticConfiguration {
  private static final int INDEPENDENT_RUNS = 1 ;

  public static void main(AbstractIntegerProblem [] args, ArrayList<Integer>algs) throws IOException {
    String experimentBaseDirectory = "experimentBaseDirectory";

    List<ExperimentProblem<IntegerSolution>> problemList = new ArrayList<>();
    for (int i = 0; i < args.length; i++) {
    		problemList.add(new ExperimentProblem<>(args[0]));
	}
    
    List<ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>> algorithmList =
            configureAlgorithmList(problemList,algs);

    Experiment<IntegerSolution, List<IntegerSolution>> experiment =
        new ExperimentBuilder<IntegerSolution, List<IntegerSolution>>("IntegerProblemStudy")
            .setAlgorithmList(algorithmList)
            .setProblemList(problemList)
            .setExperimentBaseDirectory(experimentBaseDirectory)
            .setOutputParetoFrontFileName("FUN")
            .setOutputParetoSetFileName("VAR")
            .setReferenceFrontDirectory(experimentBaseDirectory+"/referenceFronts")
            .setIndicatorList(Arrays.asList(new PISAHypervolume<IntegerSolution>()))
            .setIndependentRuns(INDEPENDENT_RUNS)
            .setNumberOfCores(8)
            .build();

    new ExecuteAlgorithms<>(experiment).run();
    new GenerateReferenceParetoFront(experiment).run();
    new ComputeQualityIndicators<>(experiment).run() ;
    new GenerateLatexTablesWithStatistics(experiment).run() ;
    new GenerateBoxplotsWithR<>(experiment).setRows(1).setColumns(1).run() ;
  }

  static List<ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>> configureAlgorithmList(List<ExperimentProblem<IntegerSolution>> problemList,ArrayList<Integer>algs) {
   
	  List<ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>> algorithms = new ArrayList<>();
	  
	  BuilderInteger test = new BuilderInteger(120);
	  
	  for (int i = 0; i < problemList.size(); i++) {
		  test.setExperimentProblem(problemList.get(i));
		  for (int j = 0; j < algs.size(); j++) {
				Algorithm<List<IntegerSolution>> algorithm = test.getAlgorithm(algs.get(j));
				if(algorithm!=null)
					algorithms.add(new ExperimentAlgorithm<>(algorithm, algorithm.getName(), problemList.get(i).getTag()));
				else {
					System.out.println("isNull");
				}
		  }
	  }
	  
	  return algorithms;
    
  }
  
}