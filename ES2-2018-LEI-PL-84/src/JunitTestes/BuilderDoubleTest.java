package JunitTestes;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.problem.multiobjective.zdt.ZDT1;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;

import antiSpamFilter.BuilderDouble;

public class BuilderDoubleTest {
	
	@Test
	public void maxEvaluationTest() {
		BuilderDouble tmp = new BuilderDouble(100);
		assertEquals(100, tmp.getMaxEvaluations());
	}
	@Test
	public void setExperimentProblemTest() {
		ZDT1 problem = new ZDT1();
		BuilderDouble tmp = new BuilderDouble(100);
		tmp.setExperimentProblem(new ExperimentProblem<>(problem));
		assertEquals(problem, tmp.geExperimentProblem().getProblem());
	}
	
	@Test
	public void notNullAlgorithmNull() {
		ZDT1 problem = new ZDT1();
		BuilderDouble builder = new BuilderDouble(250);
		builder.setExperimentProblem(new ExperimentProblem<>(problem));
		for (int i = 1; i <9 ; i++) {
			Algorithm<List<DoubleSolution>> algorithm = builder.getAlgorithm(i);
			assertNotNull(algorithm);
		}
	}
	
	@Test
	public void nullAlgorithm() {
		ZDT1 problem = new ZDT1();
		BuilderDouble builder = new BuilderDouble(250);
		builder.setExperimentProblem(new ExperimentProblem<>(problem));
		for (int i = 0; i <2 ; i++) {
			if(i==0) {
				Algorithm<List<DoubleSolution>> algorithm = builder.getAlgorithm(i);
				assertNull(algorithm);
			}else {
				Algorithm<List<DoubleSolution>> algorithm = builder.getAlgorithm(i+8);
				assertNull(algorithm);
			}
		}
	}
	
	

}
