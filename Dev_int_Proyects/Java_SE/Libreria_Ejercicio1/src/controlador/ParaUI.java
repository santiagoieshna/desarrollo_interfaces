package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JOptionPane;

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
	private ParaUIVentas gestorVentas;

	public ParaUI() {
		super();
		libreria = new Libreria();
		LibroObjectMother.iniciarLibreria(libreria);

		gestorTabla = new ParaUITable(tableLibreria);
		gestorTabla.cargarTabla(libreria);

		gestorMensajes = new Mensajes(this.contentPane);

		gestorLibroPanel = new ParaUILibroPanel(txtTitulo, txtISBN, txtPrecio, txtAutor,
				txtEditorial, grupoFormato, grupoEstado);
		
		gestorVentas = new ParaUIVentas(spinner, lblTotalVenta, txtIsbnVenta, txtTituloVenta,
				txtAutorVenta,txtEditorialVenta, txtFormatoVenta, textPrecioVenta, 
				txtEstadoVenta, btnRealizarVenta, lblStock);
		
		
		// Iniciamos Spinner aqui para negar el editar tambien
		gestorVentas.setMaxSpinner(1);
		
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
				
				if (esGuardar() ) {
					if(sonCamposValidos()) {
						if (!libreria.comprobarIsbn(getISBN())) {
								Libro libro = gestorLibroPanel.guardarLibro();
								Boolean guardado = libreria.guardarLibro(libro);
								if(guardado) {			
									titulo="Libro Guardado";
									gestorMensajes.mensajeInformeLibro(libreria.getLibro(libro.getIsbn()), titulo);
								}
								gestorLibroPanel.limpiarCampos();
	
						} else {
							mensaje = "El ISBN " + getISBN() + "Ya existe";
							titulo = "Libro Existente";
							gestorMensajes.mensajeError(titulo, mensaje);
						}
					}
				} else {
					// MODIFICAR -----------------------------------------------------------
					if (sonCamposValidos()) {

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
					} 
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
					gestorVentas.rellenarCamposParaVender(libro);
					gestorLibroPanel.desabilitarISBNCampo();
					spinner.setValue(1);
					// new SpinnerNumberModel(valorInicial, minimo, maximo, Salto)
					
					if (libro.hayStock()) {
						gestorVentas.setMaxSpinner(libreria.getLibro(isbn).getStock());
						gestorVentas.habilitarVenta();
					}
					 else {	 
						gestorVentas.deshabilitarVenta();
						lblTotalVenta.setText("Fuera de Stock!");
						lblTotalVenta.setForeground(Color.RED);
					 }
					
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
					Integer cantidad = gestorVentas.getCantidad();
					Boolean vendido = askVenderLibro(libro, cantidad);
					Integer reset = 1;
					if(vendido) {
						libro.venderLibro(cantidad);
						String titulo = "Ejemplar Vendido";
						gestorMensajes.mensajeInformeLibro(libro, titulo);
						gestorVentas.limpiarCampos();
					}
					gestorTabla.cargarTabla(libreria);
					gestorVentas.setMaxSpinner(reset);
				}else {
					String mensaje = "El libro seleccioando esta fuera de Stock";
					String tituloMensaje = "Fuera de stock";
					gestorMensajes.mensajeError(tituloMensaje, mensaje);
				}
				gestorVentas.deshabilitarVenta();
			}


			
		});
		
		// REALIZAR COMPRA ------------------------------------------------------------
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validarVentaValida()) {
					 String mensaje = "¿Cuantas unidades quieres comprar?";
					 String titulo  = "Comprar "+ txtTituloVenta.getText().toString();
					 String cantidadInput = JOptionPane.showInputDialog(contentPane, mensaje, titulo , JOptionPane.DEFAULT_OPTION);
					 if(cantidadInput !=null && Validacion.isNumero(cantidadInput)) {						 
						 Integer cantidadCompra = Integer.parseInt(cantidadInput);
						 Libro libro = libreria.getLibro(txtIsbnVenta.getText().toString());
						 Float compraTotal = libro.comprarLibro(cantidadCompra);
						 gestorMensajes.mensajeInfo( "Precio de la Compra", compraTotal.toString()+"€");
						 gestorVentas.setMaxSpinner(libro.getStock());
						 gestorVentas.setStockVentas(libro.getStock());
						 lblTotalVenta.setText(libro.ConsultarPrecio(gestorVentas.getCantidad()).toString());
						 lblTotalVenta.setForeground(Color.BLACK);
						 gestorVentas.habilitarVenta();
					 }
				}
				gestorTabla.cargarTabla(libreria);
			}
		});

		// LISTENER DOBLE CLICK --------------------------------------------
		tableLibreria.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.getClickCount() == 2) {
					Libro libro = libreria.getLibro(gestorTabla.getIsbnTable());
					btnGuardar.setText("Modificar");
					gestorLibroPanel.rellenarCamposParaModificar(libro);
					activarTab(Tabs.LIBRO);
				}
			}
		});
	}
	
	private Boolean askVenderLibro(Libro libro, Integer cantidad) {
		Boolean vendido = false;
		Float precioCalculado = Float.parseFloat(lblTotalVenta.getText().toString());
		Integer respuestaVenta = gestorMensajes.mensajeVenderSioNo(libro, cantidad, precioCalculado);
		vendido = (respuestaVenta == JOptionPane.YES_OPTION)? true:false;
		return vendido;
	}
	
	private void setPrecioVenta() {
		Integer cantidad = gestorVentas.getCantidad();
		Libro libroVenta = libreria.getLibro(txtIsbnVenta.getText().toString());
		Float total = libroVenta.ConsultarPrecio(cantidad);
		lblTotalVenta.setText(total.toString());
	}
	
	private boolean validarVentaValida() {
		// si tiene el ISBN esque el panel esta relleno
		return Validacion.validISBN(txtIsbnVenta.getText().toString()) 
				&& Validacion.validNumero(gestorVentas.getCantidad().toString());
	}
	protected boolean esPrecioValido() {
		return Validacion.validPrecio(getPrecio());
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

	private void activarTab(Tabs tab) {
		tabbedPane.setSelectedIndex(tab.getIndice());
	}

	private boolean esGuardar() {
		return btnGuardar.getText().equals("Guardar");
	}
	
	private boolean sonCamposValidos() {
		Boolean respuesta = true;
		
		String mensaje;
		String titulo;
		
		if(!Validacion.validISBN(getISBN())) {
			titulo = "ISB invalido";
			mensaje = "El ISBN debe tener 13 caracteres, todos numeros";
			gestorMensajes.mensajeError(titulo, mensaje);
			respuesta = false;
		}
		if(respuesta && !Validacion.esAlfaNumericoConAcento(getTitulo())) {
			titulo = "Titulo invalido";
			mensaje = "Inserte un titulo Correcto";
			gestorMensajes.mensajeError(titulo, mensaje);
			respuesta = false;
		} 
		if(respuesta && !Validacion.esNombre(getAutor())) {
			titulo = "Autor invalido";
			mensaje = "El Autor es invalido";
			gestorMensajes.mensajeError(titulo, mensaje);
			respuesta = false;
		}
		if(respuesta && !Validacion.esAlfabetico(getEditorial())) {
			titulo = "Editorial invalido";
			mensaje = "La Editorial es invalida";
			gestorMensajes.mensajeError(titulo, mensaje);
			respuesta = false;
		}
		if(respuesta && !Validacion.validPrecio(getPrecio())) {
			mensaje = "El precio introducido no es valido";
			titulo = "Precio Invalido";
			gestorMensajes.mensajeError(titulo, mensaje);
			respuesta = false;
		}
		if(respuesta && grupoFormato.getSelection()==null){
			mensaje = "Debes indicar el formato del libro";
			titulo = "Formato No Seleccionado";
			gestorMensajes.mensajeError(titulo, mensaje);
			respuesta = false;
		}
		if(respuesta && grupoEstado.getSelection()==null){
			mensaje = "Debes indicar el Estado del libro";
			titulo = "Estado No Seleccionado";
			gestorMensajes.mensajeError(titulo, mensaje);
			respuesta = false;
		}
		
		return respuesta;
	}

}
