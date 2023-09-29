package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import modelo.Libreria;
import modelo.Libro;
import modelo.LibroObjectMother;
import modelo.Mensajes;
import modelo.Tabs;
import utiles.Validacion;
import vista.UI;

public class ParaUI extends UI {

	private Libreria libreria;
	private ParaUITable gestorTabla;
	private ParaUILibroPanel gestorLibroPanel;
	private Mensajes gestorMensajes;

	public ParaUI() {
		super();
		libreria = new Libreria();
		LibroObjectMother.iniciarLibreria(libreria);

		gestorTabla = new ParaUITable(tableLibreria);
		gestorTabla.cargarTabla(libreria);

		gestorMensajes = new Mensajes(this.contentPane);

		gestorLibroPanel = new ParaUILibroPanel(txtTitulo, txtISBN, txtPrecio, txtAutor, txtEditorial, grupoFormato,
				grupoEstado);
		setMaxSpinner(1);
		
		// SALIR -----------------------------------------------------------------------
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		// GUARDAR LIBRO ---------------------------------------------------------------
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String titulo;
				String mensaje;

				if (esGuardar()) {

					if (esISBNvalido(getIsbnText())) {
						if (libreria.estaIsbn(getIsbnText())) {
							if (esPrecioValido()) {
								libreria.guardarLibro(gestorLibroPanel.guardarLibro());
								gestorLibroPanel.limpiarCampos();
							} else
								gestorMensajes.mensajeError("Precio Erroneo", "El precio es erroneo");

						} else {
							mensaje = "El ISBN " + getIsbnText() + "Ya existe";
							titulo = "Libro Existente";
							gestorMensajes.mensajeError(titulo, mensaje);
						}
					} else {
						titulo = "ISB invalido";
						mensaje = "El ISBN debe tener 13 caracteres, todos numeros";
						gestorMensajes.mensajeError(titulo, mensaje);
					}
				} else {
					if (esPrecioValido()) {

						mensaje = "Seguro que quieres modificar el libro";
						titulo = "Modificar";
						Integer opcion = gestorMensajes.mensajeSioNo(titulo, mensaje);
						if (opcion == JOptionPane.YES_OPTION) {
							Libro libro = gestorLibroPanel.guardarLibro();
							libreria.modificarLibro(libro);
						}
						btnGuardar.setText("Guardar");
						gestorLibroPanel.habilitarISBN();
						gestorLibroPanel.limpiarCampos();
					} else
						mensaje = "El ISBN " + getIsbnText() + "Ya existe";
					titulo = "Libro Existente";
					gestorMensajes.mensajeError(titulo, mensaje);
				}
				gestorTabla.cargarTabla(libreria);
			}

		});
		
		// CalcularTotal --------------------------------------------------------------
		spinner.addChangeListener(e -> {
			if(validarVentaValida())
					setPrecioVenta();
		});
		
		// LIMPIAR CAMPOS -------------------------------------------------------------
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestorLibroPanel.limpiarCampos();
				btnGuardar.setText("Guardar");
				gestorLibroPanel.habilitarISBN();
			}

		});
		
		// Borrar libro ---------------------------------------------------------------
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gestorTabla.tablaEstaSeleccionada()) {

					libreria.eliminarLibro(gestorTabla.getIsbnTable());
					gestorTabla.cargarTabla(libreria);
				}
			}

		});

		// BUSCAR libro ----------------------------------------------------------------
		btnBuscar.addActionListener(e -> {

			if (!textBuscador.getText().equals("")) {
				buscar();
			} else {
				gestorTabla.cargarTabla(libreria);
			}

		});


		// IR a VENTA ------------------------------------------------------------------
		btnVender.addActionListener(e -> {
			
				if (gestorTabla.tablaEstaSeleccionada()) {
					String isbn = gestorTabla.getIsbnTable();
					Libro libro = libreria.getLibro(isbn);
					activarTab(Tabs.VENDER);
					rellenarCamposParaVender(libro);
					gestorLibroPanel.desabilitarISBNCampo();
					spinner.setValue(1);
					// new SpinnerNumberModel(valorInicial, minimo, maximo, Salto)
					
					if (libro.hayStock()) {
						setMaxSpinner(libreria.getLibro(isbn).getStock());
						habilitarVenta();
					}
					 else 
						deshabilitarVenta();
					
				} else {
					String mensaje = "No has seleccionado ningun Libro";
					String tituloMensaje = "Libro no sleccionado";
					gestorMensajes.mensajeError(tituloMensaje, mensaje);

				}
			

		});
		
		// REALIZAR VENTA ---------------------------------------------------------------------
		btnRealizarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String isbn = txtIsbnVenta.getText().toString();
				Libro libro = libreria.getLibro(isbn);
				/*
				 * Esta validacion de si hay stock sobra, el programa deberia de ir bien para 
				 * nunca vender sin stock, pero servira de cortafuegos por si se escapa cualquier
				 * factor no contemplado.
				 * Pd: Jose me mataria, porque diria que si esta bien programado no hara falta, el boton
				 * siempre estaria deshabilitado.
				 */
				if(libro.hayStock()) {
					Integer cantidad = Integer.parseInt(getCantidad());
					Boolean vendido = venderLibro(libro, cantidad);
					gestorTabla.cargarTabla(libreria);
					setMaxSpinner(libro.getStock());
				}else {
					String mensaje = "El libro seleccioando esta fuera de Stock";
					String tituloMensaje = "Fuera de stock";
					gestorMensajes.mensajeError(tituloMensaje, mensaje);
				}
			}

			
		});
		
		// REALIZAR COMPRA ------------------------------------------------------------
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validarVentaValida()) {
					 String mensaje = "¿Cuantas unidades quieres comprar?";
					 String titulo  = "Comprar "+ txtTituloVenta.getText().toString();
					 String cantidadInput = JOptionPane.showInputDialog(contentPane, mensaje, titulo , JOptionPane.DEFAULT_OPTION);
					 if(Validacion.isNumero(cantidadInput)) {						 
						 Integer cantidadCompra = Integer.parseInt(cantidadInput);
						 Libro libro = libreria.getLibro(txtIsbnVenta.getText().toString());
						 Float compraTotal = libro.comprarLibro(cantidadCompra);
						 gestorMensajes.mensajeInfo( "Precio de la Compra", compraTotal.toString()+"€");
						 setMaxSpinner(libro.getStock());
						 lblTotalVenta.setText(libro.ConsultarPrecio(1).toString());
					
					 }
				}
			}
		});

		// LISTENER DOBLE CLICK --------------------------------------------
		tableLibreria.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(MouseEvent me) {
//				JTable table = (JTable) me.getSource();
//				Point p = me.getPoint();
				if (me.getClickCount() == 2) {
//					int linea = table.getSelectedRow();
					Libro libro = libreria.getLibro(gestorTabla.getIsbnTable());
					btnGuardar.setText("Modificar");
					gestorLibroPanel.rellenarCamposParaModificar(libro);
					activarTab(Tabs.LIBRO);
				}
			}
		});
	}
	
	private Boolean venderLibro(Libro libro, Integer cantidad) {
		Boolean vendido = false;
		Float precioCalculado = Float.parseFloat(lblTotalVenta.getText().toString());
		Integer respuestaVenta = gestorMensajes.mensajeVenderSioNo(libro, cantidad, precioCalculado);
		
		if(respuestaVenta == JOptionPane.YES_OPTION) {
			Float precioVenta = libro.venderLibro(cantidad);
			vendido = true;
			gestorMensajes.mensajeFactura(libro, precioVenta);
		}
		return vendido;
	}

	private void deshabilitarVenta() {
		btnRealizarVenta.setEnabled(false);
	}

	private void habilitarVenta() {
		btnRealizarVenta.setEnabled(true);
	}

	private void setMaxSpinner(Integer stock) {
		spinner.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1),
				stock, Integer.valueOf(1)));
		ponerSpinnerNoEditable();
	}

	private void setPrecioVenta() {
		Integer cantidad = Integer.parseInt(getCantidadVenta());
		Libro libroVenta = libreria.getLibro(txtIsbnVenta.getText().toString());
		Float total = libroVenta.ConsultarPrecio(cantidad);
		lblTotalVenta.setText(total.toString());
	}
	
	/**
	 * Metodo que hace que el TextField interno del Spinner no sea editable
	 */
	private void ponerSpinnerNoEditable() {
		JFormattedTextField tf = ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField();
	    tf.setEditable(false);
	}

	private boolean validarVentaValida() {
		// si tiene el ISBN esque el panel esta relleno
		return esISBNvalido(txtIsbnVenta.getText().toString()) 
				&& Validacion.validNumero(getCantidadVenta());
	}

	private String getCantidadVenta() {
		return getCantidad();
	}

	protected boolean esPrecioValido() {
		return Validacion.validPrecio(getPrecioText());
	}

	private boolean esISBNvalido(String isbn) {
		return Validacion.validISBN(isbn);
	}

	private void buscar() {
		if (filtro.getSelectedItem().toString().equals("ISBN")) {
			busquedaISBN();
		} else if (filtro.getSelectedItem().toString().equals("AUTOR")) {
			busquedaAutor();
		} else if (filtro.getSelectedItem().toString().equals("TITULO")) {
			busquedaTitulo();
		} else if (filtro.getSelectedItem().toString().equals("EDITORIAL")) {
			busquedaEditorial();
		}

	}

	private void busquedaEditorial() {
		List<Libro> librosMatch = libreria.getListByEditorial(textBuscador.getText());
		gestorTabla.cargarTabla(new Libreria(librosMatch));
	}

	private void busquedaTitulo() {
		List<Libro> librosMatch = libreria.getListByTitulo(textBuscador.getText());
		gestorTabla.cargarTabla(new Libreria(librosMatch));
	}

	private void busquedaAutor() {
		List<Libro> librosMatch = libreria.getListByAutor(textBuscador.getText());
		gestorTabla.cargarTabla(new Libreria(librosMatch));
	}

	private void busquedaISBN() {
		List<Libro> librosMatch = libreria.getListByISBN(textBuscador.getText());
		gestorTabla.cargarTabla(new Libreria(librosMatch));
	}

	private void activarTab(Tabs vender) {
		tabbedPane.setSelectedIndex(vender.getIndice());
	}

	private void rellenarCamposParaVender(Libro libro) {

		// ------ Metemos campos
		txtIsbnVenta.setText(libro.getIsbn());
		txtTituloVenta.setText(libro.getTitulo());
		txtEditorialVenta.setText(libro.getEditorial());
		txtAutorVenta.setText(libro.getAutor());
		txtFormatoVenta.setText(libro.getFormato());
		txtEstadoVenta.setText(libro.getEstado());
		textPrecioVenta.setText(libro.getPrecio().toString());
		lblTotalVenta.setText(libro.ConsultarPrecio(Integer.parseInt(getCantidad())).toString());
	}

	private String getCantidad() {
		return spinner.getValue().toString();
	}

	private boolean esGuardar() {
		return btnGuardar.getText().equals("Guardar");
	}
}
