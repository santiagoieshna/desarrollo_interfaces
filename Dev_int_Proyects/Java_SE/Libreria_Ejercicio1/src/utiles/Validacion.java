package utiles;

import java.util.regex.Pattern;

public class Validacion {
	public static Boolean validISBN(String isbn) {
		return Pattern.matches("[0-9]{13}$", isbn);
	}
	
	public static Boolean validPrecio(String precio) {
		return Pattern.matches("[0-9]\\.?[0-9]{2}", precio);
	}
	
	
	
}
