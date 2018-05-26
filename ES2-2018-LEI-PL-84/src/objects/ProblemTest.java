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
<<<<<<< HEAD
//		vars.add(new Variable("V1","Integer","-5","5"));
//		vars.add(new Variable("V2","Integer","-5","5"));
=======
		vars.add(new Variable("V1","Integer","-5","5",8));
		vars.add(new Variable("V2","Integer","-5","5",8));
>>>>>>> branch 'master' of https://github.com/sbpss-iscteiul/ES2-2018-LEI-PL-84.git
		a.setVars(vars);
		assertEquals(a.getProblemName(), "ProblemName");
		assertEquals(a.getDescription(), "Descrição do problema");
		assertEquals(a.getAlgorithms(), algoritmos);
		assertEquals(a.getEmail(), "email@iscte.pt");
		assertEquals(a.getPaths(), paths);
		assertEquals(a.getRestrictions(), restrictions);
		assertEquals(a.getVars(), vars);
		assertEquals(a.getTempoDeEspera(), "45");
		assertEquals(a.toString(), "Problem [email=email@iscte.pt, problemName=ProblemName, description=Descrição do problema, paths={[NomeA,AlgoA],[NomeB,AlgoB],}, tempoDeEspera=45, restrictions=[Restriction [varName=V1, operation==, value=2], Restriction [varName=V2, operation=!=, value=0]], vars=[Variable [name=V1, type=Integer, minValue=-5, maxValue=5, nrBits=8], Variable [name=V2, type=Integer, minValue=-5, maxValue=5, nrBits=8]], algotihm=[Alg1, Alg2]]");
		restrictions.get(0).setOperation("=");
		restrictions.get(0).setValue("2");
		restrictions.get(0).setVarName("V1");
		assertEquals(restrictions.get(0).getOperation(), "=");
		assertEquals(restrictions.get(0).getValue(), "2");
		assertEquals(restrictions.get(0).getVarName(), "V1");
		vars.get(0).setMaxValue("5");
		vars.get(0).setMinValue("-5");
		vars.get(0).setType("Integer");
		vars.get(0).setNumberBits(8);
		vars.get(0).setName("V1");
		assertEquals(vars.get(0).getMaxValue(), "5");
		assertEquals(vars.get(0).getMinValue(), "-5");
		assertEquals(vars.get(0).getType(), "Integer");
		assertEquals(vars.get(0).getNumberBits(), 8);
		assertEquals(vars.get(0).getName(), "V1");
	}

}
