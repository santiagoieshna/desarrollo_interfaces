package modelo;

public class Libro {
	private String isbn;
	private String titulo;
	private String autor;
	private String editorial;
	private Float precio;
	private String formato;
	private String estado;
	private Integer stock;



	public Libro(String isbn, String titulo, String autor, String editorial,
				Float precio, String formato, String estado, Integer stock) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.precio = precio;
		this.formato=formato;
		this.estado=estado;
		this.stock= stock;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public String getIsbn() {
		return isbn;
	}
	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	@Override
	public String toString() {
		return "Libro \nisbn: " + isbn + "\ntitulo: " + titulo + "\nautor: " + autor
				+ " \neditorial:" + editorial+"\nprecio:" + precio + "\nformato: "
				+formato + "\nestado: " + estado + "\nstock: " + stock;
	}
	
}
