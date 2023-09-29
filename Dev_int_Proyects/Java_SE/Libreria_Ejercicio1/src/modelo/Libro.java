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
	public static Float INTERES_VENTA=0.12f; 

	public Libro(String isbn, String titulo, String autor, String editorial, Float precio, String formato,
			String estado, Integer stock) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.precio = precio;
		this.formato = formato;
		this.estado = estado;
		if(stock>=0) {
			this.stock = stock;			
		}else {
			this.stock= 0;
		}
	}
	
	/**
	 * @return True si el stock es mayor que 0
	 * 			False caso contrario.
	 */
	public Boolean hayStock() {
		return this.getStock()>0;
	}
	
	/**
	 * Metodo que devolvera el precio de venta del libro, ajustara el stock.
	 * @param cantidad
	 * @return -1 si la cantidad es superior al stock
	 * 			Variable Float del precio si hay stock.
	 */
	public Float venderLibro(Integer cantidad) {
		Float precioVenta = -1f;
		if (this.getStock() >= cantidad) {
			Integer stockDiferencia = cantidad * (-1);
			ActualizarStock(stockDiferencia);
			precioVenta = this.getPrecio() * cantidad*(1+INTERES_VENTA);
		}
		return precioVenta;
	}
	
	public Float ConsultarPrecio(Integer cantidad){
		Libro libroClonado = (Libro) this.clone();
		return libroClonado.venderLibro(cantidad);
	}
	
	public Float comprarLibro(Integer cantidad) {
		ActualizarStock(cantidad);
		return this.getPrecio()*cantidad;
	}
	
	/**
	 * Metodo que actualiza el Stock cuando haya una modificacion de ello.
	 * @param cantidad
	 */
	private void ActualizarStock(Integer cantidad) {
		this.setStock(this.getStock() + cantidad);
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
		return "Libro \nisbn: " + isbn + "\ntitulo: " + titulo + "\nautor: " + autor + " \neditorial: " + editorial
				+ "\nprecio: " + precio + "\nformato: " + formato + "\nestado: " + estado + "\nstock: " + stock;
	}

	@Override
	protected Object clone() {
		Libro libroClone = new Libro(this.isbn, this.titulo, this.autor, this.editorial,
				this.precio, this.formato, this.estado, this.stock);
		return libroClone;
	}

}
