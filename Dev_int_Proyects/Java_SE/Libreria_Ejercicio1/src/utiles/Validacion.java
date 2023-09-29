package utiles;

import java.util.regex.Pattern;

public class Validacion {
	public static Boolean validISBN(String isbn) {
		return Pattern.matches("^[0-9]{13}$", isbn);
	}
	
	public static Boolean validPrecio(String precio) {
		return Pattern.matches("[0-9]*\\.?[0-9]{0,2}", precio);
	}
	
	public static Boolean validNumero(String numero) {
		return Pattern.matches("[0-9]+$", numero);
	}
	
	public static Boolean isCampoEscrito(String campo) {
		return campo.length()>0;
	}
	
	public static Boolean isLetras(String frase) {
		return Pattern.matches("[a-z,A-Z]", frase);
	}
	
	public static Boolean cumpleMetricas50(String campo) {
		return Pattern.matches("{50}$", campo);
	}

	public static boolean isNumero(String numero) {
		return Pattern.matches("[0-9]{1,2}", numero);
	}
	
	
}
