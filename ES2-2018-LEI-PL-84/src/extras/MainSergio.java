package extras;

public class MainSergio {

	public static void main(String[] args) {
		Parser parser = new Parser();
		parser.addLimitations("y!=10");

		parser.addEmail("Cenas e Situações");

		parser.addVariables("Regras", "Int", "-5", "5");

		parser.addChosenAlgorithm(1, "Algoritmo 1");

		parser.addPaths(1, "blahblahblah");

		parser.addDescription("Isto é uma descrição de cenas e situações");

		parser.addWaitingTime("2.0");
		parser.write_XML();
	}

}
