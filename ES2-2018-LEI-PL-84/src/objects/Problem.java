package objects;

import java.util.ArrayList;

public class Problem {
	private String email="";
	private String problemName="";
	private String description="";
	private ArrayList<String[]> paths= new ArrayList<String[]>();
	private String tempoDeEspera="";
	private ArrayList<Restriction> restrictions= new ArrayList<Restriction>();
	private ArrayList<Variable> vars= new ArrayList<Variable>();
	private ArrayList<String> algorithms = new ArrayList<String>();
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProblemName() {
		return problemName;
	}
	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ArrayList<String[]> getPaths() {
		return paths;
	}
	public void setPaths(ArrayList<String[]> paths) {
		this.paths = paths;
	}
	public String getTempoDeEspera() {
		return tempoDeEspera;
	}
	public void setTempoDeEspera(String tempoDeEspera) {
		this.tempoDeEspera = tempoDeEspera;
	}
	public ArrayList<Restriction> getRestrictions() {
		return restrictions;
	}
	public void setRestrictions(ArrayList<Restriction> restrictions) {
		this.restrictions = restrictions;
	}
	public ArrayList<Variable> getVars() {
		return vars;
	}
	public void setVars(ArrayList<Variable> vars) {
		this.vars = vars;
	}

	@Override
	public String toString() {
		String pathToPrint="{";
		for (String[] it : paths) {
			pathToPrint= pathToPrint+"["+it[0]+","+it[1]+"],";
		}
		pathToPrint= pathToPrint+"}";
		return "Problem [email=" + email + ", problemName=" + problemName + ", description=" + description + ", paths="
				+ pathToPrint + ", tempoDeEspera=" + tempoDeEspera + ", restrictions=" + restrictions + ", vars=" + vars
				+ ", algotihm=" + algorithms + "]";
	}
	public ArrayList<String> getAlgorithms() {
		return algorithms;
	}
	public void setAlgorithms(ArrayList<String> algorithms) {
		this.algorithms = algorithms;
	}
}
