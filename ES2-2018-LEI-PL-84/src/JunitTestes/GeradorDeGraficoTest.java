package JunitTestes;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import extras.GeradorDeGraficos;

class GeradorDeGraficoTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() throws IOException {
		String File="BEST_HV_FUN";
		String File1="FUN0";
		String File2="X";
		String titulo="Soluções ótimas geradas pelo processo de otimização";
		GeradorDeGraficos demo = new GeradorDeGraficos(titulo,File);
		GeradorDeGraficos demo1 = new GeradorDeGraficos(titulo,File1);
		GeradorDeGraficos demo2 = new GeradorDeGraficos(titulo,File2);
		assertEquals("Soluções ótimas geradas pelo processo de otimização", demo.getTitle());
		assertEquals("BEST_HV_FUN", demo.getAlg());
		assertEquals("FUN0", demo1.getAlg());
		assertEquals("X", demo2.getAlg());
	}

}
