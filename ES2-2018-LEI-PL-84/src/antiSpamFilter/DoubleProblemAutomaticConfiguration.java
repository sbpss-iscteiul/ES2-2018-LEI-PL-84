package antiSpamFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.problem.multiobjective.zdt.ZDT1;
import org.uma.jmetal.qualityindicator.impl.hypervolume.PISAHypervolume;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.experiment.Experiment;
import org.uma.jmetal.util.experiment.ExperimentBuilder;
import org.uma.jmetal.util.experiment.component.ComputeQualityIndicators;
import org.uma.jmetal.util.experiment.component.ExecuteAlgorithms;
import org.uma.jmetal.util.experiment.component.GenerateBoxplotsWithR;
import org.uma.jmetal.util.experiment.component.GenerateLatexTablesWithStatistics;
import org.uma.jmetal.util.experiment.component.GenerateReferenceParetoSetAndFrontFromDoubleSolutions;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;

public class DoubleProblemAutomaticConfiguration {
  private static final int INDEPENDENT_RUNS = 1 ;

  public static void main(AbstractDoubleProblem [] args, ArrayList<Integer>algs) throws IOException {
    String experimentBaseDirectory = "experimentBaseDirectory";

    List<ExperimentProblem<DoubleSolution>> problemList = new ArrayList<>();
    for (int i = 0; i < args.length; i++) {
    		problemList.add(new ExperimentProblem<>(args[i]));
	}
   
    List<ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>> algorithmList =
            configureAlgorithmList(problemList,algs);

    Experiment<DoubleSolution, List<DoubleSolution>> experiment =
        new ExperimentBuilder<DoubleSolution, List<DoubleSolution>>("DoubleProblemStudy")
            .setAlgorithmList(algorithmList)
            .setProblemList(problemList)
            .setExperimentBaseDirectory(experimentBaseDirectory)
            .setOutputParetoFrontFileName("FUN")
            .setOutputParetoSetFileName("VAR")
            .setReferenceFrontDirectory(experimentBaseDirectory+"/referenceFronts")
            .setIndicatorList(Arrays.asList(new PISAHypervolume<DoubleSolution>()))
            .setIndependentRuns(INDEPENDENT_RUNS)
            .setNumberOfCores(8)
            .build();

    new ExecuteAlgorithms<>(experiment).run();
    new GenerateReferenceParetoSetAndFrontFromDoubleSolutions(experiment).run();
    new ComputeQualityIndicators<>(experiment).run() ;
    new GenerateLatexTablesWithStatistics(experiment).run() ;
    new GenerateBoxplotsWithR<>(experiment).setRows(1).setColumns(1).run() ;
  }

  static List<ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>> configureAlgorithmList(List<ExperimentProblem<DoubleSolution>> problemList,ArrayList<Integer>algs) {
   
	  List<ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>> algorithms = new ArrayList<>();
	  
	  BuilderDouble test = new BuilderDouble(120);
	  
	  for (int i = 0; i < problemList.size(); i++) {
		  test.setExperimentProblem(problemList.get(i));
		  for (int j = 0; j < algs.size(); j++) {
				Algorithm<List<DoubleSolution>> algorithm = test.getAlgorithm(algs.get(j));
				if(algorithm!=null)
					algorithms.add(new ExperimentAlgorithm<>(algorithm, algorithm.getName(), problemList.get(i).getTag()));
		  }
	  }
	  
	  return algorithms;
    
  }
  
}