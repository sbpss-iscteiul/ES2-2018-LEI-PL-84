package extras;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JARexec {
	public static String runJAR(String dir,String solutionString) throws IOException {
		Process p = Runtime.getRuntime().exec("java -jar "+ dir+" "+solutionString);
		BufferedReader brinput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		return brinput.readLine();
		
	}
}
