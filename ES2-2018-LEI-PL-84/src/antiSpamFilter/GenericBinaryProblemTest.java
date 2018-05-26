package antiSpamFilter;

import static org.junit.Assert.*;

import org.junit.Test;
import org.uma.jmetal.solution.BinarySolution;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.solution.IntegerSolution;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.solution.impl.DefaultBinarySolution;
import org.uma.jmetal.util.binarySet.BinarySet;

public class GenericBinaryProblemTest {

	@Test
	public void constructerTest() {
		GenericBinaryProblem bProblem = new GenericBinaryProblem(10, 2, 1, "ProblemaTeste",500 ,"C:\\Users\\Sergio-PC\\Desktop\\Universidade\\Semestre 2\\Engenharia de software II\\Projecto\\JarsTeste\\testeEvaluate.jar");
		BinarySolution solutionTeste = bProblem.createSolution();
		assertEquals(10, bProblem.getNumberOfVariables());
		assertEquals(2, bProblem.getNumberOfObjectives());
		assertEquals(1, bProblem.getNumberOfConstraints());
		assertEquals("ProblemaTeste", bProblem.getName());
		assertEquals("C:\\Users\\Sergio-PC\\Desktop\\Universidade\\Semestre 2\\Engenharia de software II\\Projecto\\JarsTeste\\testeEvaluate.jar", bProblem.getPath());
		assertEquals(500, bProblem.getNBits());
		assertNotNull(solutionTeste);
	}
	
	@Test
	public void evaluateTest() {
		GenericBinaryProblem bProblem = new GenericBinaryProblem(10, 2, 1, "ProblemaTeste",500 ,"C:\\Users\\Sergio-PC\\Desktop\\testeEvaluate.jar");
		BinarySolution tmp = createDS();
		bProblem.evaluate(tmp);
		assertEquals(""+1.0, ""+tmp.getObjective(0));
		assertEquals(""+2.0, ""+tmp.getObjective(1));
	}
	
	private BinarySolution createDS() {
		BinarySolution tmp = new BinarySolution() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private double obj1= 0.0;
			private double obj2= 0.0;
			
			@Override
			public void setVariableValue(int index, BinarySet value) {
				// TODO Auto-generated method stub
				
			}
			 
			@Override
			public void setObjective(int index, double value) {
				switch (index) {
				case 0:
					obj1=value;
					break;
				case 1:
					obj2=value;
					break;
				default:
					break;
				}
				
			}
			
			@Override
			public void setAttribute(Object id, Object value) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public String getVariableValueString(int index) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public BinarySet getVariableValue(int index) {
				BinarySet set = new BinarySet(500);
				return set;
			}
			
			@Override
			public double getObjective(int index) {
				switch (index) {
				case 0:
					return this.obj1;
				case 1:
					return this.obj2;
				default:
					return -1.0;
				}
			}
			
			@Override
			public int getNumberOfVariables() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public int getNumberOfObjectives() {
				// TODO Auto-generated method stub
				return 2;
			}
			
			@Override
			public Object getAttribute(Object id) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Solution<BinarySet> copy() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getTotalNumberOfBits() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public int getNumberOfBits(int index) {
				// TODO Auto-generated method stub
				return 500;
			}
		};
		return null; 
	}

}
