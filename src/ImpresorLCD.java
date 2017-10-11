import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Imprime los digitos  en consola 
 * @author dcastanoj
 *
 */
public class ImpresorLCD {

	/** Puntos fijos de  los  digitos  */ 
	private final int[] puntoFijo1;
	private final int[] puntoFijo2;
	private final int[] puntoFijo3;
	private final int[] puntoFijo4;
	private final int[] puntoFijo5;

	/** matriz a imprimir */
	private String[][] matrizImpresión;
	
	static final String CARACTER_VERTICAL = "|";
	static final String CARACTER_HORIZONTAL = "-";
	static final String POSICION_X = "X";
	static final String POSICION_Y = "Y";
	
	/** Tamaño Segmento Digitos */
	private int size;

	/** lista de numeros **/
	private Map<Integer, Integer[]> valoresNumeros;

	public ImpresorLCD() {
		// Inicializa variables
		this.puntoFijo1 = new int[2];
		this.puntoFijo2 = new int[2];
		this.puntoFijo3 = new int[2];
		this.puntoFijo4 = new int[2];
		this.puntoFijo5 = new int[2];
		this.crearListaNumeros();
	}

	/**
	 *
	 * Metodo encargado de añadir una linea a la matriz de Impresion
	 *
	 * @param matriz
	 *            Matriz Impresion
	 * @param punto
	 *            Punto Pivote
	 * @param posFija
	 *            Posicion Fija
	 * @param size
	 *            Tamaño Segmento
	 * @param caracter
	 *            Caracter Segmento
	 */
	private void adicionarLinea(String[][] matriz, int[] punto, String posFija, int size, String caracter) {

		if (posFija.equalsIgnoreCase(POSICION_X)) {
			for (int y = 1; y <= size; y++) {
				int valor = punto[1] + y;
				matriz[punto[0]][valor] = caracter;
			}
		} else {
			for (int i = 1; i <= size; i++) {
				int valor = punto[0] + i;
				matriz[valor][punto[1]] = caracter;
			}
		}
	}

	/**
	 *
	 * Metodo encargado de un segmento a la matriz de Impresion
	 *
	 * @param segmento
	 *            Segmento a adicionar
	 */
	private void adicionarSegmento(int segmento) {

		switch (segmento) {
		case 1:
			adicionarLinea(this.matrizImpresión, this.puntoFijo1, POSICION_Y, this.size, CARACTER_VERTICAL);
			break;
		case 2:
			adicionarLinea(this.matrizImpresión, this.puntoFijo2, POSICION_Y, this.size, CARACTER_VERTICAL);
			break;
		case 3:
			adicionarLinea(this.matrizImpresión, this.puntoFijo5, POSICION_Y, this.size, CARACTER_VERTICAL);
			break;
		case 4:
			adicionarLinea(this.matrizImpresión, this.puntoFijo4, POSICION_Y, this.size, CARACTER_VERTICAL);
			break;
		case 5:
			adicionarLinea(this.matrizImpresión, this.puntoFijo1, POSICION_X, this.size, CARACTER_HORIZONTAL);
			break;
		case 6:
			adicionarLinea(this.matrizImpresión, this.puntoFijo2, POSICION_X, this.size, CARACTER_HORIZONTAL);
			break;
		case 7:
			adicionarLinea(this.matrizImpresión, this.puntoFijo3, POSICION_X, this.size, CARACTER_HORIZONTAL);
			break;
		default:
			break;
		}
	}

	/**
	 *
	 * Metodo encargado de definir los segmentos que componen un digito y a
	 * partir de los segmentos adicionar la representacion del digito a la
	 * matriz
	 *
	 * @param numero
	 *            
	 */
	private void adicionarDigito(int numero) {
		List<Integer> segList = new ArrayList<>();
		segList.addAll(Arrays.asList(this.valoresNumeros.get(numero)));
		segList.forEach(digito -> adicionarSegmento(digito)
		);
	}

	/**
	 * crea el mapa con los segmentos segun el número 
	 */
	private void crearListaNumeros() {
		// Establece los segmentos de cada numero
		this.valoresNumeros = new HashMap<>();
		valoresNumeros.put(1, new Integer[] { 3, 4 });
		valoresNumeros.put(2, new Integer[] { 5, 3, 6, 2, 7 });
		valoresNumeros.put(3, new Integer[] { 5, 3, 6, 4, 7 });
		valoresNumeros.put(4, new Integer[] { 1, 6, 3, 4 });
		valoresNumeros.put(5, new Integer[] { 5, 1, 6, 4, 7 });
		valoresNumeros.put(6, new Integer[] { 5, 1, 6, 2, 7, 4 });
		valoresNumeros.put(7, new Integer[] { 5, 3, 4 });
		valoresNumeros.put(8, new Integer[] { 1, 2, 3, 4, 5, 6, 7 });
		valoresNumeros.put(9, new Integer[] { 1, 3, 4, 5, 6, 7 });
		valoresNumeros.put(0, new Integer[] { 1, 2, 3, 4, 5, 7 });
	}

	/**
	 *
	 * Metodo encargado de imprimir un numero
	 *
	 * @param size
	 *            Tamaño Segmento Digitos
	 * @param numeroImp
	 *            Numero a Imprimir
	 * @param espacio
	 *            Espacio Entre digitos
	 */
	private String[][] generarNumero(int size, String numeroImp, int espacio) {
		int pivotX = 0;
		char[] digitos;
		// Calcula el numero de filasDig
		int filasDig;
		int columDig;
		int totalFilas;
		int totalColum;

		this.size = size;

		// Calcula el numero de filas cada digito
		filasDig = (2 * this.size) + 3;

		// Calcula el numero de columna de cada digito
		columDig = size + 2;

		// Calcula el total de filas de la matriz en la que se almacenaran los digitos
		totalFilas = filasDig;

		// Calcula el total de columnas de la matriz en la que se almacenaran los digitos
		totalColum = (columDig * numeroImp.length()) + (espacio * numeroImp.length());

		// crea matriz para almacenar los numero a imprimir
		this.matrizImpresión = new String[totalFilas][totalColum];

		// crea el arreglo de digitos
		digitos = numeroImp.toCharArray();

		// Inicializa matriz
		for (String[] row : this.matrizImpresión) {
			Arrays.fill(row, " ");
		}

		for (char digito : digitos) {

			// Valida que el caracter sea un digito
			if (!Character.isDigit(digito)) {
				throw new IllegalArgumentException("Caracter " + digito + " no es un digito");
			}

			int numero = Integer.parseInt(String.valueOf(digito));

			// Calcula puntos fijos
			this.puntoFijo1[0] = 0;
			this.puntoFijo1[1] = 0 + pivotX;

			this.puntoFijo2[0] = filasDig / 2;
			this.puntoFijo2[1] = 0 + pivotX;

			this.puntoFijo3[0] = filasDig - 1;
			this.puntoFijo3[1] = 0 + pivotX;

			this.puntoFijo4[0] = columDig - 1;
			this.puntoFijo4[1] = filasDig / 2 + pivotX;

			this.puntoFijo5[0] = 0;
			this.puntoFijo5[1] = (columDig - 1) + pivotX;

			pivotX = pivotX + columDig + espacio;

			adicionarDigito(numero);
		}

		return this.matrizImpresión;
	}

	/**
	 *
	 * Metodo encargado de procesar la entrada que contiene el size del segmento
	 * de los digitos y los digitos a imprimir
	 *
	 * @param comando
	 *            Entrada que contiene el size del segmento de los digito y el
	 *            numero a imprimir
	 * @param espacioDig
	 *            Espacio Entre digitos
	 */
	public void procesar(String comando, int espacioDig) {

		String[] parametros;

		int tam;

		if (!comando.contains(",")) {
			throw new IllegalArgumentException("Cadena " + comando + " no contiene caracter ,");
		}

		// Se hace el split de la cadena
		parametros = comando.split(",");

		tam = validarEntrada(comando, parametros);
		// Realiza la impresion del numero
		String[][] numero = generarNumero(tam, parametros[1], espacioDig);

		imprimirNumero(numero);

	}
	
	/**
	 * valida los paramentros ingresados
	 * @param comando
	 * @param parametros
	 * @return
	 */
	private int validarEntrada(String comando, String[] parametros) {
		int tam;
		// Valida la cantidad de parametros
		if (parametros.length > 2) {
			throw new IllegalArgumentException("Cadena " + comando + " contiene mas caracter ,");
		}

		// Valida la cantidad de parametros
		if (parametros.length < 2) {
			throw new IllegalArgumentException("Cadena " + comando + " no contiene los parametros requeridos");
		}

		// Valida que el parametro size sea un numerico
		try {
			tam = Integer.parseInt(parametros[0]);
			if (tam < 1 || tam > 10) {
				throw new IllegalArgumentException("El parametro size [" + tam + "] debe estar entre 1 y 10");
			}
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("Parametro Size [" + parametros[0] + "] no es un numero");
		}
		return tam;
	}

	/**
	 * imprime el número en consola
	 * 
	 * @param numero
	 */
	private void imprimirNumero(String[][] numero) {
		for (int i = 0; i < numero.length; i++) {
			for (int j = 0; j < numero[i].length; j++) {
				System.out.print(this.matrizImpresión[i][j]);
			}
			System.out.println();
		}

	}

}
