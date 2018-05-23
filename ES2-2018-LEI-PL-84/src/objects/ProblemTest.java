package objects;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ProblemTest {
	Problem a;
	@Test
	void test() {
		ArrayList<String>  algoritmos= new ArrayList<>();
		algoritmos.add("Alg1");
		algoritmos.add("Alg2");
		a = new Problem();
		a.setAlgorithms(algoritmos);
		a.setDescription("Descrição do problema");
		a.setEmail("email@iscte.pt");
		ArrayList<String[]>  paths= new ArrayList<>();
		String[] pathA = new String[2];
		String[] pathB = new String[2];
		pathA[0]="NomeA";
		pathB[0]="NomeB";
		pathA[1]="AlgoA";
		pathB[1]="AlgoB";
		paths.add(pathA);
		paths.add(pathB);
		a.setPaths(paths);
		a.setProblemName("ProblemName");
		ArrayList<Restriction> restrictions= new ArrayList<>();
		restrictions.add(new Restriction("V1","=", "2"));
		restrictions.add(new Restriction("V2","!=", "0"));
		a.setRestrictions(restrictions);
		a.setTempoDeEspera("45");
		ArrayList<Variable> vars= new ArrayList<>();
		vars.add(new Variable("V1","Integer","-5","5"));
		vars.add(new Variable("V2","Integer","-5","5"));
		a.setVars(vars);
		assertEquals(a.getProblemName(), "ProblemName");
		assertEquals(a.getDescription(), "Descrição do problema");
		assertEquals(a.getAlgorithms(), algoritmos);
		assertEquals(a.getEmail(), "email@iscte.pt");
		assertEquals(a.getPaths(), paths);
		assertEquals(a.getRestrictions(), restrictions);
		assertEquals(a.getVars(), vars);
		assertEquals(a.getTempoDeEspera(), "45");
		assertEquals(a.toString(), "Problem [email=email@iscte.pt, problemName=ProblemName, description=Descrição do problema, paths={[NomeA,AlgoA],[NomeB,AlgoB],}, tempoDeEspera=45, restrictions=[Restriction [varName=V1, operation==, value=2], Restriction [varName=V2, operation=!=, value=0]], vars=[Variable [name=V1, type=Integer, minValue=-5, maxValue=5], Variable [name=V2, type=Integer, minValue=-5, maxValue=5]], algotihm=[Alg1, Alg2]]");
	}

}
