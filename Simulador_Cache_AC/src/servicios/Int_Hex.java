package servicios;
/**
 * 
 * @author Bryan Zambrano
 * Esta clase sirve para transformar las direcciones de la memoria principal de decimal a hexadecimal
 * Hay distintos tipos de metodos pero todos cumplen la misma funcion.
 * Cada metodo es utilizado para trasformar la direccion, el tag y  la palabra respectivamente
 *
 */

public class Int_Hex {
	

	public static String int_to_hex(int n) {
		String hex_string = Integer.toHexString(n);
		while(hex_string.length() < 8) {
			hex_string = "0" + hex_string;
		}
		return hex_string;
	}
	
	public static String int_to_hexTAG(int n) {
		String hex_string = Integer.toHexString(n);
		while(hex_string.length() < 5) {
			hex_string = "0" + hex_string;
		}
		return hex_string;
	}
	public static String int_to_hexWord(int n) {
		String hex_string = Integer.toHexString(n);
		while(hex_string.length() < 2) {
			hex_string = "0" + hex_string;
		}
		return hex_string;
	}
	  
}
