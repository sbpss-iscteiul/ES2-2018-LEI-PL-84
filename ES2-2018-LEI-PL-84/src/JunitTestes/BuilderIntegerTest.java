package JunitTestes;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.problem.multiobjective.NMMin;
import org.uma.jmetal.problem.multiobjective.zdt.ZDT1;
import org.uma.jmetal.solution.BinarySolution;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.solution.IntegerSolution;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;

import antiSpamFilter.BuilderInteger;

public class BuilderIntegerTest {

	@Test
	public void maxEvaluationTest() {
		BuilderInteger tmp = new BuilderInteger(100);
		assertEquals(100, tmp.getMaxEvaluations());
	}
	
	@Test
	public void setExperimentProblemTest() {
		NMMin problem = new NMMin();
		BuilderInteger tmp = new BuilderInteger(100);
		tmp.setExperimentProblem(new ExperimentProblem<>(problem));
		assertEquals(problem, tmp.geExperimentProblem().getProblem());
	}
	
	@Test
	public void notNullAlgorithmNull() {
		NMMin problem = new NMMin();
		BuilderInteger builder = new BuilderInteger(250);
		builder.setExperimentProblem(new ExperimentProblem<>(problem));
		for (int i = 1; i <6 ; i++) {
			Algorithm<List<IntegerSolution>> algorithm = builder.getAlgorithm(i);
			assertNotNull(algorithm);
		}
	}
	
	@Test
	public void nullAlgorithm() {
		NMMin problem = new NMMin();
		BuilderInteger builder = new BuilderInteger(250);
		builder.setExperimentProblem(new ExperimentProblem<>(problem));
		for (int i = 0; i <2 ; i++) {
			if(i==0) {
				Algorithm<List<IntegerSolution>> algorithm = builder.getAlgorithm(i);
				assertNull(algorithm);
			}else {
				Algorithm<List<IntegerSolution>> algorithm = builder.getAlgorithm(i+8);
				assertNull(algorithm);
			}
		}
	}

}
