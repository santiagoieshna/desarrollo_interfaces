package utiles;

import java.util.regex.Pattern;

public class Validacion {
	public static Boolean validISBN(String isbn) {
		return Pattern.matches("^[0-9]{13}$", isbn);
	}
	
	public static Boolean validPrecio(String precio) {
		return Pattern.matches("[0-9]+\\.?[0-9]{0,2}", precio);
	}
	
	public static Boolean validNumero(String numero) {
		return Pattern.matches("[0-9]+$", numero);
	}
	
	public static Boolean esAlfabetico(String frase) {
		return Pattern.matches("[a-zA-Z\\s]+", frase);
	}
	/**
	 * Metodo que valida cualquier nombre con acentos españoles
	 * @param frase
	 * @return
	 */
	public static Boolean esNombre(String frase) {
		// es un intento de aplicar todos los acentos pero no tuvo exito
		String regex= "[A-ZÄËÏÖÜÁÉÍÓÚÂÊÎÔÛÀÈÌÒÙ][a-zäÄëËïÏöÖüÜáéíóúáéíóúÁÉÍÓÚÂÊÎÔÛâêîôûàèìòùÀÈÌÒÙ\\s]+";
		return Pattern.matches("[A-Za-zÁÉÍÓÚáéíóú\\s]+[A-Za-zÁÉÍÓÚáéíóú\\s]*",frase);
	}
	
	public static boolean isNumero(String numero) {
		return Pattern.matches("[0-9]{1,2}", numero);
	}
	
	
	public static boolean esAlfaNumericoConAcento(String numero) {
		return Pattern.matches("[A-Za-z0-9ÁÉÍÓÚáéíóúáéíóú\\s]+", numero);
	}
	
	public static boolean esAlfaNumerico(String numero) {
		return Pattern.matches("[A-Za-z0-9]+", numero);
	}
	
	
}
