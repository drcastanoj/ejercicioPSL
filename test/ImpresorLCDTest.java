import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Test;

public class ImpresorLCDTest {

	@Test
	public void testImprimoCorrectamenteLosNumeros() {
		// preparación del test 
		ByteArrayOutputStream baos = new ByteArrayOutputStream();		
		PrintStream printStream = new PrintStream(baos);
		System.setOut(printStream);		
		ImpresorLCD impresora = new ImpresorLCD();
		
		// ejecución 
		impresora.procesar("2,12345", 3);
		impresora.procesar("3,67890", 3);
		
		// verificación 
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
		// preparación del test 		
		ImpresorLCD impresora = new ImpresorLCD();
		
		// ejecución 
		impresora.procesar("0,0", 3);		
		// verificación 		
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void test_ParametroNoContienceComa() {
		// preparación del test 		
		ImpresorLCD impresora = new ImpresorLCD();
		
		// ejecución 
		impresora.procesar("21545", 3);		
		// verificación 		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_ParametrosConMasDeUnaComa() {
		// preparación del test 		
		ImpresorLCD impresora = new ImpresorLCD();
		
		// ejecución 
		impresora.procesar("21,5,45", 3);		
		// verificación 		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_CadenaLeFaltanParametros() {
		// preparación del test 		
		ImpresorLCD impresora = new ImpresorLCD();
		
		// ejecución 
		impresora.procesar("21,", 3);		
		// verificación 		
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void test_ParametroMayorQue10() {
		// preparación del test 		
		ImpresorLCD impresora = new ImpresorLCD();
		
		// ejecución 
		impresora.procesar("11,10", 3);		
		// verificación 		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_ParametrosNoEsUnNumero() {
		// preparación del test 		
		ImpresorLCD impresora = new ImpresorLCD();
		
		// ejecución 
		impresora.procesar("A,A", 3);		
		// verificación 		
	}
}
