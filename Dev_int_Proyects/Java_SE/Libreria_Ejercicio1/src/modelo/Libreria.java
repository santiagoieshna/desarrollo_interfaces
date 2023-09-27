package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Libreria {

	private List<Libro> libreria;

	

	public Libreria() {
		super();
		this.libreria = new ArrayList<>();
	}
	
	public Libreria(List<Libro> libros) {
		super();
		this.libreria = libros;
	}
	
	
	public Boolean guardarLibro(String isbn, String titulo, String autor, String editorial,
								Float precio, String formato, String estado, Integer stock) {
		return addLibro(new Libro(isbn, titulo, autor, editorial, precio, formato, estado, stock));
	}
	
	public Boolean guardarLibro(Libro libro) {
		return addLibro(libro);
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
	
	
	public boolean estaIsbn(String isbn) {
		boolean respuesta = false;
		
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
	
	public List<Libro> getListByISBN(String isbn){
		List<Libro> librosEncontrados = libreria.stream()
									.filter(libro-> libro.getIsbn().indexOf(isbn)!=-1)
									.collect(Collectors.toList());
		
		return librosEncontrados;
	}

	public int size() {
		
		return this.libreria.size();
	}

	public Libro get(int i) {
		
		return libreria.get(i);
	}

	public void editarLibro(Libro libroEditado) {
		Libro libro = getLibro(libroEditado.getIsbn());
		
	}
	
	public void modificarLibro(Libro libroEditado) {
		Libro libro = getLibro(libroEditado.getIsbn());
		libro.setTitulo(libroEditado.getTitulo());
		libro.setAutor(libroEditado.getAutor());
		libro.setEditorial(libroEditado.getEditorial());
		libro.setPrecio(libroEditado.getPrecio());
		libro.setFormato(libroEditado.getFormato());
		libro.setEstado(libroEditado.getEstado());
	}
	public void modificarLibro(String isbn, String titulo,
			String autor, String editorial, String precio,
			String formato, String estado) {
		Libro libro = getLibro(isbn);
		libro.setTitulo(titulo);
		libro.setAutor(autor);
		libro.setEditorial(editorial);
		libro.setPrecio(Float.parseFloat(precio));
		libro.setFormato(formato);
		libro.setEstado(estado);
	}

}
