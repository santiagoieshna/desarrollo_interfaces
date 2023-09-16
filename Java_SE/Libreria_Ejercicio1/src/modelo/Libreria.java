package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Libreria {

	private List<Libro> libreria;

	

	public Libreria() {
		super();
		this.libreria = new ArrayList<>();
		iniciarLibreria();
	}

	private void iniciarLibreria() {
		this.addLibro(new Libro("9780141036144", "1984", "George Orwell", "Penguin Books", 10.99f));
		this.addLibro(new Libro("9780061120084", "To Kill a Mockingbird", "Harper Lee",
				"Harper Perennial Modern Classics", 12.99f));
		this.addLibro(new Libro("9780062315007", "The Catcher in the Rye", "J.D. Salinger", "Little, Brown and Company",
				11.49f));
		this.addLibro(new Libro("9780544003415", "The Hobbit", "J.R.R. Tolkien", "Houghton Mifflin Harcourt", 14.99f));
		this.addLibro(new Libro("9780060850524", "The Great Gatsby", "F. Scott Fitzgerald", "Scribner", 9.99f));
		this.addLibro(new Libro("9780743273565", "The Da Vinci Code", "Dan Brown", "Pocket Books", 13.95f));
		this.addLibro(new Libro("9780061122415", "The Hunger Games", "Suzanne Collins", "Scholastic Press", 8.99f));
		this.addLibro(new Libro("9780143134772", "Becoming", "Michelle Obama", "Penguin Books", 18.99f));
		this.addLibro(new Libro("9780739326222", "The Road", "Cormac McCarthy", "Vintage", 10.95f));
		this.addLibro(new Libro("9780062662851", "Educated", "Tara Westover", "Random House", 16.99f));
	}
	
	public Boolean guardarLibro(String isbn, String titulo, String autor, String editorial, Float precio) {
		return addLibro(new Libro(isbn, titulo, autor, editorial, precio));
	}

	private Boolean addLibro(Libro libro) {
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

}
