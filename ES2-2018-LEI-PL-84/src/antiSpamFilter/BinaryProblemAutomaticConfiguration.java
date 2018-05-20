package antiSpamFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.problem.impl.AbstractBinaryProblem;
import org.uma.jmetal.qualityindicator.impl.hypervolume.PISAHypervolume;
import org.uma.jmetal.solution.BinarySolution;
import org.uma.jmetal.util.experiment.Experiment;
import org.uma.jmetal.util.experiment.ExperimentBuilder;
import org.uma.jmetal.util.experiment.component.ComputeQualityIndicators;
import org.uma.jmetal.util.experiment.component.ExecuteAlgorithms;
import org.uma.jmetal.util.experiment.component.GenerateBoxplotsWithR;
import org.uma.jmetal.util.experiment.component.GenerateLatexTablesWithStatistics;
import org.uma.jmetal.util.experiment.component.GenerateReferenceParetoFront;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;

public class BinaryProblemAutomaticConfiguration {
  private static final int INDEPENDENT_RUNS = 5 ;

  public static void main(AbstractBinaryProblem [] args, ArrayList<Integer>algs) throws IOException {
    String experimentBaseDirectory = "experimentBaseDirectory";

    List<ExperimentProblem<BinarySolution>> problemList = new ArrayList<>();
    for (int i = 0; i < args.length; i++) {
    		problemList.add(new ExperimentProblem<>(args[i]));
	}
    
    List<ExperimentAlgorithm<BinarySolution, List<BinarySolution>>> algorithmList =
            configureAlgorithmList(problemList,algs);

    Experiment<BinarySolution, List<BinarySolution>> experiment =
        new ExperimentBuilder<BinarySolution, List<BinarySolution>>("BinaryProblemAutomaticConfigurationStudy")
            .setAlgorithmList(algorithmList)
            .setProblemList(problemList)
            .setExperimentBaseDirectory(experimentBaseDirectory)
            .setOutputParetoFrontFileName("FUN")
            .setOutputParetoSetFileName("VAR")
            .setReferenceFrontDirectory(experimentBaseDirectory+"/referenceFronts")
            .setIndicatorList(Arrays.asList(new PISAHypervolume<BinarySolution>()))
            .setIndependentRuns(INDEPENDENT_RUNS)
            .setNumberOfCores(8)
            .build();

    new ExecuteAlgorithms<>(experiment).run();
    new GenerateReferenceParetoFront(experiment).run();
    new ComputeQualityIndicators<>(experiment).run() ;
    new GenerateLatexTablesWithStatistics(experiment).run() ;
    new GenerateBoxplotsWithR<>(experiment).setRows(1).setColumns(1).run() ;
  }

  static List<ExperimentAlgorithm<BinarySolution, List<BinarySolution>>> configureAlgorithmList(List<ExperimentProblem<BinarySolution>> problemList,ArrayList<Integer>algs) {
   
	  List<ExperimentAlgorithm<BinarySolution, List<BinarySolution>>> algorithms = new ArrayList<>();
	  
	  BuilderBinary test = new BuilderBinary(2500);
	  
	  for (int i = 0; i < problemList.size(); i++) {
		  test.setExperimentProblem(problemList.get(i));
		  for (int j = 0; j < algs.size(); j++) {
				Algorithm<List<BinarySolution>> algorithm = test.getAlgorithm(algs.get(j));
				if(algorithm!=null)
					algorithms.add(new ExperimentAlgorithm<>(algorithm, algorithm.getName(), problemList.get(i).getTag()));
		  }
	  }
	  
	  return algorithms;
    
  }
  
}