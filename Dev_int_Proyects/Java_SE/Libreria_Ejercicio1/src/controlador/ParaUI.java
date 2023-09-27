package controlador;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import modelo.Libreria;
import modelo.Libro;
import modelo.Tabs;
import vista.UI;

public class ParaUI extends UI {

	private Libreria libreria;
	private Integer rowTabla;

	
	public ParaUI() {
		super();
		libreria = new Libreria();
		rowTabla = -1;

		cargarTabla();

		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		// GUARDAR LIBRO
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (btnGuardar.getText().equals("Guardar")) {
					guardarLibro();
					limpiarCampos();
					cargarTabla();
					
				} else {
					JOptionPane.showConfirmDialog(contentPane, 
							"Seguro que quieres modificar el libro",
							"Modificar "+ getIsbn(),
							JOptionPane.YES_NO_OPTION);
					guardarLibro();
					btnGuardar.setText("Guardar");
					habilitarISBN();
					limpiarCampos();
					cargarTabla();
				}
			}

			

		});
		// CalcularTotal
		spinner.addChangeListener(e->{
				Integer cantidad =  Integer.parseInt(spinner.getValue().toString());
				Float precioUnidad = Float.parseFloat(textPrecioVenta.getText());
				Float total  = cantidad * precioUnidad;
				lblTotalVenta.setText(total.toString());
			
		});
		// LIMPIAR CAMPOS
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
			}

		});
		// Borrar libro
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tablaEstaSeleccionada()) {

					libreria.eliminarLibro(getIbnTable());
					cargarTabla();
				}
			}
		});
		
		// Ir a Ventas
		btnVender.addActionListener(e -> {
				if(tablaEstaSeleccionada()) {
					
					String isbn = getIbnTable();
					Libro libro = libreria.getLibro(isbn);
					activarTab(Tabs.VENDER);
					rellenarCamposParaVender(libro);
					desabilitarISBNCampo();
					spinner.setValue(1);
					// new SpinnerNumberModel(valorInicial, minimo, maximo, Salto)
					spinner.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
				}else {
					JOptionPane.showMessageDialog(this.contentPane, "No has seleccionado ningun Libro");
				}
				
			});
		
		// 1 Click para coger ISBN --> Boton borrar lo elimina
		tableLibreria.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				//TODO Algo hara, pero no será hoy
				
			}
		});
		// LISTENER DOBLE CLICK
		tableLibreria.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				JTable table = (JTable) me.getSource();
				Point p = me.getPoint();
//				int row = table.rowAtPoint(p);

				if (me.getClickCount() == 2) {
					int linea = table.getSelectedRow();
					// Rellenar Version 1
					rellenarCamposParaModificar(table, linea);
					// Rellenar Version 2
//					String isbn = table.getValueAt(linea, 0).toString();
//					rellenarCamposParaModificar(isbn, linea);
//					Aca llamamos a la ventana que nos traera el los detalles del registro
				}
			}	
		});
	}

	private void activarTab(Tabs vender) {
		tabbedPane.setSelectedIndex(vender.getIndice());
	}

	private boolean tablaEstaSeleccionada() {
		
		return -1 != tableLibreria.getSelectedRow();
	}

	private String getIbnTable() {
		byte fila= (byte) tableLibreria.getSelectedRow();
		return tableLibreria.getValueAt(fila, 0).toString();
	}

	private List<Libro> getLibreria() {
		return libreria.getLibreria();
	}

	private void cargarTabla() {
		String nombresColumnas[] = { "ISBN", "TITULO", "EDITORIAL", "AUTOR",
									"PRECIO", "TAGS", "ESTADO", "STOCK" };
		String[][] filasTabla = new String[getLibreria().size()][8];
		for (int i = 0; i < getLibreria().size(); i++) {
			filasTabla[i][0] = getLibreria().get(i).getIsbn();
			filasTabla[i][1] = getLibreria().get(i).getTitulo();
			filasTabla[i][2] = getLibreria().get(i).getEditorial();
			filasTabla[i][3] = getLibreria().get(i).getAutor();
			filasTabla[i][4] = getLibreria().get(i).getPrecio().toString();
			filasTabla[i][5] = getLibreria().get(i).getFormato();
			filasTabla[i][6] = getLibreria().get(i).getEstado();
			filasTabla[i][7] = getLibreria().get(i).getStock().toString();
		}
		// Aqui creamos la Tabla
		DefaultTableModel tablaCompleta = new DefaultTableModel(filasTabla, nombresColumnas);
		// tableLibreria es el molde donde puede haber tablas, pero esta no es una
		// tabla, si no el molde
		tableLibreria.setModel(tablaCompleta);

	}

	private void limpiarCampos() {
		txtISBN.setText("");
		txtAutor.setText("");
		txtTitulo.setText("");
		txtPrecio.setText("");
		txtEditorial.setText("");
		limpiarTags();
		limpiarEstado();
	}

	private void limpiarTags() {
		grupoFormato.clearSelection();
	}
	
	private void limpiarEstado() {
		grupoEstado.clearSelection();
	}

	private void guardarLibro() {
		String isbn=txtISBN.getText();
		String autor = txtAutor.getText();
		String titulo = txtTitulo.getText();
		String editorial= txtEditorial.getText();
		Float precio  = Float.parseFloat(txtPrecio.getText());
		String formato= getFormato();
		String estado = getEstado();
		Integer stock = getStock();
		libreria.guardarLibro(isbn, titulo, autor, editorial, precio, formato, estado, stock);
	}
	
	private void rellenarCamposParaModificar(JTable table, int linea) {
		tabbedPane.setSelectedIndex(0);
		btnGuardar.setText("Modificar");
		desabilitarISBNCampo();
		// ------ Metemos campos
		txtISBN.setText(table.getValueAt(linea, 0).toString());
		txtTitulo.setText(table.getValueAt(linea, 1).toString());
		txtEditorial.setText(table.getValueAt(linea, 2).toString());
		txtAutor.setText(table.getValueAt(linea, 3).toString());
		txtPrecio.setText(table.getValueAt(linea, 4).toString());
		selectedFormato(table.getValueAt(linea, 5).toString());
		selectedEstado(table.getValueAt(linea, 6).toString());
	}

	
	private void rellenarCamposParaModificar(String isbn, int linea) {
		Libro libro = libreria.getLibro(isbn);
		desabilitarISBNCampo();
		// ------ Metemos campos
		txtISBN.setText(libro.getIsbn());
		txtTitulo.setText(libro.getTitulo());
		txtEditorial.setText(libro.getEditorial());
		txtAutor.setText(libro.getAutor());
		txtPrecio.setText(libro.getPrecio().toString());
		selectedFormato(libro.getFormato());
		selectedEstado(libro.getEstado());
	}
	
	private void habilitarISBN() {
		txtISBN.setEditable(true);
	}

	private Integer getStock() {
		
		return 0;
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

	private void desabilitarISBNCampo() {
		txtISBN.setEditable(false);
	}
	
	private String getFormato() {
		return  grupoFormato.getSelection().getActionCommand();
	}
	
	private String getEstado() {
		return grupoEstado.getSelection().getActionCommand();
	}
	
	private void selectedFormato(String formatoLibro) {
		
		Enumeration<AbstractButton> botones = grupoFormato.getElements();
		
		while(botones.hasMoreElements()) {
			AbstractButton radioBoton = botones.nextElement();
			if((radioBoton.getText().equals(formatoLibro)))
				 radioBoton.setSelected(true);
		}
	}
	
	private void selectedEstado(String estadoLibro) {
		Enumeration<AbstractButton> botones = grupoEstado.getElements();
		while(botones.hasMoreElements()) {
			AbstractButton radioBoton = botones.nextElement();
			if((radioBoton.getText().equals(estadoLibro)))
				radioBoton.setSelected(true);
		}
	}

}
