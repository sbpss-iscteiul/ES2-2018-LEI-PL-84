package antiSpamFilter;

import java.util.List;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;

public abstract class Builder<E> {

	protected ExperimentProblem<?> experimentProblem;
	protected int maxEvaluations;
	
	public Builder(ExperimentProblem<?> eP,int mE) {
		this.experimentProblem=eP;
		this.maxEvaluations=mE;
	}
	
	public Algorithm<List<E>> getAlgorithm(int i){
		return null;
	} 
	
	public void setExperimentProblem(ExperimentProblem<?> eP) {
		this.experimentProblem = eP;
	}
}
