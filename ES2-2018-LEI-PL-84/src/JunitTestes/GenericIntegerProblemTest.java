package antiSpamFilter;

import static org.junit.Assert.*;

import org.junit.Test;
import org.uma.jmetal.solution.BinarySolution;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.solution.IntegerSolution;
import org.uma.jmetal.solution.Solution;

public class GenericIntegerProblemTest {

	@Test
	public void constructerTest() {
		GenericIntegerProblem iProblem = new GenericIntegerProblem(10, 2, 1, "ProblemaTeste","C:\\Users\\Sergio-PC\\Desktop\\Universidade\\Semestre 2\\Engenharia de software II\\Projecto\\JarsTeste\\testeEvaluate.jar");
		iProblem.setLimits(-5, 5);
		assertEquals(10, iProblem.getNumberOfVariables());
		assertEquals(2, iProblem.getNumberOfObjectives());
		assertEquals(1, iProblem.getNumberOfConstraints());
		assertEquals("ProblemaTeste", iProblem.getName());
		assertEquals("C:\\Users\\Sergio-PC\\Desktop\\Universidade\\Semestre 2\\Engenharia de software II\\Projecto\\JarsTeste\\testeEvaluate.jar", iProblem.getPath());
		assertEquals(""+(-5), ""+iProblem.getLowerBound(0).intValue());
		assertEquals(""+(5), ""+iProblem.getUpperBound(0).intValue());
	}
	
	@Test
	public void evaluateTest() {
		GenericIntegerProblem iProblem = new GenericIntegerProblem(10, 2, 1, "ProblemaTeste","C:\\Users\\Sergio-PC\\Desktop\\testeEvaluate.jar");
		iProblem.setLimits(-5, 5);
		IntegerSolution tmp = createDS();
		iProblem.evaluate(tmp);
		assertEquals(""+1.0, ""+tmp.getObjective(0));
		assertEquals(""+2.0, ""+tmp.getObjective(1));
	}
	
	private IntegerSolution createDS() {
		IntegerSolution tmp = new IntegerSolution() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private double obj1= 0.0;
			private double obj2= 0.0;
			@Override
			public void setVariableValue(int index, Integer value) {
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
			public Integer getVariableValue(int index) {
				// TODO Auto-generated method stub
				return null;
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
			public Solution<Integer> copy() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Integer getUpperBound(int index) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Integer getLowerBound(int index) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		return tmp;
	}
}
