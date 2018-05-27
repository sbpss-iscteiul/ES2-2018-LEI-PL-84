package JunitTestes;

import static org.junit.Assert.*;

import org.junit.Test;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.solution.Solution;

import antiSpamFilter.AntiSpamFilterProblem;

public class AntiSpamFilterProblemTest {
 
	@Test
	public void constructerTest() {
		AntiSpamFilterProblem dProblem = new AntiSpamFilterProblem();
		assertEquals(335, dProblem.getNumberOfVariables());
		assertEquals(2, dProblem.getNumberOfObjectives());
		assertEquals("AntiSpamFilterProblem", dProblem.getName());
		assertEquals(""+(-5.0), ""+dProblem.getLowerBound(0).doubleValue());
		assertEquals(""+(5.0), ""+dProblem.getUpperBound(0).doubleValue());
	}
	
	@Test 
	public void evaluateTest() {
		AntiSpamFilterProblem dProblem = new AntiSpamFilterProblem();
		DoubleSolution tmp = createDS();
		dProblem.evaluate(tmp);
		assertEquals(""+104.0, ""+tmp.getObjective(0));
		assertEquals(""+0.0, ""+tmp.getObjective(1));
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
				return ""+0.5;
			}
			
			@Override
			public Double getVariableValue(int index) {
				// TODO Auto-generated method stub
				return 0.5;
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
				return 335;
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
				return 5.0;
			}
			
			@Override
			public Double getLowerBound(int index) {
				// TODO Auto-generated method stub
				return -5.0;
			}
		};
		return tmp;
	}

}
