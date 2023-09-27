package controlador;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
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

		// SALIR --------------------------------------------
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		// GUARDAR LIBRO ------------------------------------
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String titulo;
				String mensaje;

				if (esGuardar()) {

					if (esISBvalido()) {
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

			private boolean esISBvalido() {
				// TODO Auto-generated method stub
				return false;
			}

		});
		// CalcularTotal -------------------------------------------
		spinner.addChangeListener(e -> {
			Integer cantidad = Integer.parseInt(spinner.getValue().toString());
			Float precioUnidad = Float.parseFloat(textPrecioVenta.getText());
			Float total = cantidad * precioUnidad;
			lblTotalVenta.setText(total.toString());

		});
		// LIMPIAR CAMPOS -------------------------------------------
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestorLibroPanel.limpiarCampos();
				btnGuardar.setText("Guardar");
				gestorLibroPanel.habilitarISBN();
			}

		});
		// Borrar libro ----------------------------------------------
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gestorTabla.tablaEstaSeleccionada()) {

					libreria.eliminarLibro(gestorTabla.getIsbnTable());
					gestorTabla.cargarTabla(libreria);
				}
			}

		});

		// BUSCAR libro ----------------------------------------------
		btnBuscar.addActionListener(e -> {

			if (!textBuscador.getText().equals("")) {
				buscar();
			} else {
				gestorTabla.cargarTabla(libreria);
			}

		});

		filtro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
					btnBuscar.doClick();
				}
			}
		});

		// IR a VENTA -----------------------------------------------
		btnVender.addActionListener(e -> {
			if (gestorTabla.tablaEstaSeleccionada()) {
				String isbn = gestorTabla.getIsbnTable();
				Libro libro = libreria.getLibro(isbn);
				if (libro.getStock() >= 1) {

					activarTab(Tabs.VENDER);
					rellenarCamposParaVender(libro);
					gestorLibroPanel.desabilitarISBNCampo();
					spinner.setValue(1);
					// new SpinnerNumberModel(valorInicial, minimo, maximo, Salto)
					spinner.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1),
							libreria.getLibro(isbn).getStock(), Integer.valueOf(1)));
				} else {
					String mensaje = "El libro seleccioando esta fuera de Stock";
					String tituloMensaje = "Fuera de stock";
					gestorMensajes.mensajeError(tituloMensaje, mensaje);
				}
			} else {
				String mensaje = "No has seleccionado ningun Libro";
				String tituloMensaje = "Libro no sleccionado";
				gestorMensajes.mensajeError(tituloMensaje, mensaje);

			}

		});

		// 1 Click para coger ISBN --> Boton borrar lo elimina ------------
		tableLibreria.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				// TODO Algo hara, pero no será hoy

			}
		});

		// LISTENER DOBLE CLICK --------------------------------------------
		tableLibreria.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				JTable table = (JTable) me.getSource();
				Point p = me.getPoint();

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

	protected boolean esPrecioValido() {
		// TODO Auto-generated method stub
		return false;
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

	private List<Libro> getLibreria() {
		return libreria.getLibreria();
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
		lblTotalVenta.setText(libro.getPrecio().toString());
	}

	private boolean esGuardar() {
		return btnGuardar.getText().equals("Guardar");
	}
}
