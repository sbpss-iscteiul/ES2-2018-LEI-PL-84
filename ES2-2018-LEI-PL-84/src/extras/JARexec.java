package extras;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class JARexec {
	public static ArrayList<String> runJAR(String dir,String solutionString,int nObjectives) throws IOException {
		Process p = Runtime.getRuntime().exec("java -jar "+ dir+" "+solutionString);
		BufferedReader brinput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		ArrayList<String> reader = new ArrayList<>();
		for (int i = 0; i < nObjectives; i++) {
			reader.add(brinput.readLine());
		}
		brinput.close();
		p.destroy();
		return reader;
	} 
}
