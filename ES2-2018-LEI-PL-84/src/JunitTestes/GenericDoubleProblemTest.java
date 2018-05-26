package JunitTestes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.solution.Solution;

import antiSpamFilter.GenericDoubleProblem;

public class GenericDoubleProblemTest {

	@Test
	public void constructerTest() {
		GenericDoubleProblem dProblem = new GenericDoubleProblem(10, 2, 1, "ProblemaTeste", "C:\\Users\\Sergio-PC\\Desktop\\Universidade\\Semestre 2\\Engenharia de software II\\Projecto\\JarsTeste\\testeEvaluate.jar");
		dProblem.setLimits(-5.0, 5.0);
		assertEquals(10, dProblem.getNumberOfVariables());
		assertEquals(2, dProblem.getNumberOfObjectives());
		assertEquals(1, dProblem.getNumberOfConstraints());
		assertEquals("ProblemaTeste", dProblem.getName());
		assertEquals("C:\\Users\\Sergio-PC\\Desktop\\Universidade\\Semestre 2\\Engenharia de software II\\Projecto\\JarsTeste\\testeEvaluate.jar", dProblem.getPath());
		assertEquals(""+(-5.0), ""+dProblem.getLowerBound(0).doubleValue());
		assertEquals(""+(5.0), ""+dProblem.getUpperBound(0).doubleValue());
	}
	
	@Test 
	public void evaluateTest() {
		GenericDoubleProblem dProblem = new GenericDoubleProblem(10, 2, 1, "ProblemaTeste", "C:\\Users\\Sergio-PC\\Desktop\\testeEvaluate.jar");
		dProblem.setLimits(-5.0, 5.0);
		DoubleSolution tmp = createDS();
		dProblem.evaluate(tmp);
		assertEquals(""+1.0, ""+tmp.getObjective(0));
		assertEquals(""+2.0, ""+tmp.getObjective(1));
	}
	
	
	private DoubleSolution createDS() {
		DoubleSolution tmp = new DoubleSolution() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private double obj1= 0.0;
			private double obj2= 0.0;
			
			@Override
			public void setVariableValue(int index, Double value) {
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
			public Double getVariableValue(int index) {
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
			public Solution<Double> copy() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Double getUpperBound(int index) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Double getLowerBound(int index) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		return tmp;
	}
}
