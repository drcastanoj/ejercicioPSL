import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Test;

public class ImpresorLCDTest {

	@Test
	public void testImprimoCorrectamenteLosNumeros() {
		// preparaci�n del test 
		ByteArrayOutputStream baos = new ByteArrayOutputStream();		
		PrintStream printStream = new PrintStream(baos);
		System.setOut(printStream);		
		ImpresorLCD impresora = new ImpresorLCD();
		
		// ejecuci�n 
		impresora.procesar("2,12345", 3);
		impresora.procesar("3,67890", 3);
		
		// verificaci�n 
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("        --     --            --    \r\n");
		stringBuffer.append("   |      |      |   |  |   |      \r\n");
		stringBuffer.append("   |      |      |   |  |   |      \r\n");
		stringBuffer.append("        --     --     --     --    \r\n");
		stringBuffer.append("   |   |         |      |      |   \r\n");
		stringBuffer.append("   |   |         |      |      |   \r\n");
		stringBuffer.append("        --     --            --    \r\n");
		stringBuffer.append(" ---     ---     ---     ---     ---    \r\n");
		stringBuffer.append("|           |   |   |   |   |   |   |   \r\n");
		stringBuffer.append("|           |   |   |   |   |   |   |   \r\n");
		stringBuffer.append("|           |   |   |   |   |   |   |   \r\n");
		stringBuffer.append(" ---             ---     ---            \r\n");
		stringBuffer.append("|   |       |   |   |       |   |   |   \r\n");
		stringBuffer.append("|   |       |   |   |       |   |   |   \r\n");
		stringBuffer.append("|   |       |   |   |       |   |   |   \r\n");
		stringBuffer.append(" ---             ---     ---     ---    \r\n");
		Assert.assertEquals(stringBuffer.toString(), baos.toString());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_IngresanValoresEnCero() {
		// preparaci�n del test 		
		ImpresorLCD impresora = new ImpresorLCD();
		
		// ejecuci�n 
		impresora.procesar("0,0", 3);		
		// verificaci�n 		
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void test_ParametroNoContienceComa() {
		// preparaci�n del test 		
		ImpresorLCD impresora = new ImpresorLCD();
		
		// ejecuci�n 
		impresora.procesar("21545", 3);		
		// verificaci�n 		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_ParametrosConMasDeUnaComa() {
		// preparaci�n del test 		
		ImpresorLCD impresora = new ImpresorLCD();
		
		// ejecuci�n 
		impresora.procesar("21,5,45", 3);		
		// verificaci�n 		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_CadenaLeFaltanParametros() {
		// preparaci�n del test 		
		ImpresorLCD impresora = new ImpresorLCD();
		
		// ejecuci�n 
		impresora.procesar("21,", 3);		
		// verificaci�n 		
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void test_ParametroMayorQue10() {
		// preparaci�n del test 		
		ImpresorLCD impresora = new ImpresorLCD();
		
		// ejecuci�n 
		impresora.procesar("11,10", 3);		
		// verificaci�n 		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_ParametrosNoEsUnNumero() {
		// preparaci�n del test 		
		ImpresorLCD impresora = new ImpresorLCD();
		
		// ejecuci�n 
		impresora.procesar("A,A", 3);		
		// verificaci�n 		
	}
}
