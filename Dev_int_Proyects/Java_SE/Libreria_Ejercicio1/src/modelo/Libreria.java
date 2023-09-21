package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Libreria {

	private List<Libro> libreria;

	

	public Libreria() {
		super();
		this.libreria = new ArrayList<>();
		LibroObjectMother.iniciarLibreria(this);
	}
	
	public Boolean guardarLibro(String isbn, String titulo, String autor, String editorial,
								Float precio, String formato, String estado, Integer stock) {
		return addLibro(new Libro(isbn, titulo, autor, editorial, precio, formato, estado, stock));
	}

	public Boolean addLibro(Libro libro) {
		return libreria.add(libro);

	}

	public boolean eliminarLibro(Libro libro) {
		return libreria.remove(libro);
	}

	public Boolean eliminarLibro(String isbn) {
		Boolean respuesta = false;

		Iterator<Libro> iterador = libreria.iterator();

		while (iterador.hasNext()) {
			Libro libro = (Libro) iterador.next();
			if (libro.getIsbn().equals(isbn)) {
				iterador.remove();
				respuesta = true;
			}
		}
		return respuesta;
	}
	public List<Libro> getLibreria() {
		return libreria;
	}
	
	public void modificarLibro(String isbn, String titulo, String autor, String editorial, Float precio) {
		for (Libro libro : libreria) {
			if(libro.getIsbn().equals(isbn)) {
				libro.setTitulo(titulo);
				libro.setAutor(autor);
				libro.setEditorial(editorial);
				libro.setPrecio(precio);
			}
		}
	}
	
	public boolean estaIsbn(String isbn) {
		boolean respuesta = false;
		
//		libreria.forEach(libro -> {
//			if(libro.getIsbn().equals(isbn)) {
//				respueta = true;
//			}
//		});
		for (Libro libro : libreria) {
			if(libro.getIsbn().equals(isbn)) {
				respuesta = true;
			}
		}
		
		return respuesta;
	}
	
	public Libro getLibro(String isbn) {
		for (Libro libro : libreria) {
			if(libro.getIsbn().equals(isbn))
				return libro;
		}
		return null;
	}
	

}
