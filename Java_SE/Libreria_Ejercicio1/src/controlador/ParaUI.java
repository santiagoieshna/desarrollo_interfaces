package controlador;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Libreria;
import modelo.Libro;
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

		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String isbn;
				String autor;
				String titulo;
				String editorial;
				Float precio;
				if (btnGuardar.getText().equals("Guardar")) {
					isbn = txtISBN.getText();
					autor = txtAutor.getText();
					titulo = txtTitulo.getText();
					editorial = txtEditorial.getText();
					precio = Float.parseFloat(txtPrecio.getText());
					libreria.guardarLibro(isbn, titulo, autor, editorial, precio);
					limpiarCampos();
					cargarTabla();
				} else {

					isbn = txtISBN.getText();
					autor = txtAutor.getText();
					titulo = txtTitulo.getText();
					editorial = txtEditorial.getText();
					precio = Float.parseFloat(txtPrecio.getText());
					libreria.modificarLibro(isbn, titulo, autor, editorial, precio);
					btnGuardar.setText("Guardar");
					txtISBN.setEditable(true);
					limpiarCampos();
					cargarTabla();
				}
			}
		});
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
			}

		});
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rowTabla != -1) {

					libreria.eliminarLibro(tableLibreria.getValueAt(rowTabla, 0).toString());
					rowTabla = 1;
					cargarTabla();
				}
			}
		});

		tableLibreria.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
//				System.out.println(evt.getClickCount());
				rowTabla = tableLibreria.rowAtPoint(evt.getPoint());
//		        int col = tableLibreria.columnAtPoint(evt.getPoint());

//		        libreria.eliminarLibro(tableLibreria.getValueAt(rowTabla, 0).toString());
				cargarTabla();

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
					tabbedPane.setSelectedIndex(0);
					btnGuardar.setText("Modificar");
					// Cegamos ISBN
					txtISBN.setEditable(false);
					// ------ Metemos campos
					txtISBN.setText(table.getValueAt(linea, 0).toString());
					txtTitulo.setText(table.getValueAt(linea, 1).toString());
					txtEditorial.setText(table.getValueAt(linea, 2).toString());
					txtAutor.setText(table.getValueAt(linea, 3).toString());
					txtPrecio.setText(table.getValueAt(linea, 4).toString());

					// Aca llamamos a la ventana que nos traera el los detalles del registro
				}
			}
		});

	}

	private List<Libro> getLibreria() {
		return libreria.getLibreria();
	}

	private void cargarTabla() {
		String nombresColumnas[] = { "ISBN", "TITULO", "EDITORIAL", "AUTOR", "PRECIO" };
		String[][] filasTabla = new String[getLibreria().size()][5];
		for (int i = 0; i < getLibreria().size(); i++) {
			filasTabla[i][0] = getLibreria().get(i).getIsbn();
			filasTabla[i][1] = getLibreria().get(i).getTitulo();
			filasTabla[i][2] = getLibreria().get(i).getEditorial();
			filasTabla[i][3] = getLibreria().get(i).getAutor();
			filasTabla[i][4] = getLibreria().get(i).getPrecio().toString();
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
	}

}
