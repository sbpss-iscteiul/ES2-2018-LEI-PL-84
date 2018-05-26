package antiSpamFilter;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.problem.multiobjective.OneZeroMax;
import org.uma.jmetal.solution.BinarySolution;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;

public class BuilderBinaryTest {

	@Test
	public void maxEvaluationTest() {
		BuilderBinary tmp = new BuilderBinary(100);
		assertEquals(100, tmp.getMaxEvaluations());
	}
	
	@Test
	public void setExperimentProblemTest() {
		OneZeroMax problem = new OneZeroMax();
		BuilderBinary tmp = new BuilderBinary(100);
		tmp.setExperimentProblem(new ExperimentProblem<>(problem));
		assertEquals(problem, tmp.geExperimentProblem().getProblem());
	}
	
	@Test
	public void notNullAlgorithmNull() {
		OneZeroMax problem = new OneZeroMax();
		BuilderBinary builder = new BuilderBinary(250);
		builder.setExperimentProblem(new ExperimentProblem<>(problem));
		for (int i = 1; i <8 ; i++) {
			Algorithm<List<BinarySolution>> algorithm = builder.getAlgorithm(i);
			assertNotNull(algorithm);
		}
	}
	
	@Test
	public void nullAlgorithm() {
		OneZeroMax problem = new OneZeroMax();
		BuilderBinary builder = new BuilderBinary(250);
		builder.setExperimentProblem(new ExperimentProblem<>(problem));
		for (int i = 0; i <2 ; i++) {
			if(i==0) {
				Algorithm<List<BinarySolution>> algorithm = builder.getAlgorithm(i);
				assertNull(algorithm);
			}else {
				Algorithm<List<BinarySolution>> algorithm = builder.getAlgorithm(i+8);
				assertNull(algorithm);
			}
		}
	}

}
