package controlador;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Libreria;

public class ParaUITable {
	private JTable tableLibreria;
	
	public ParaUITable(JTable tableLibreria) {
		super();
		this.tableLibreria = tableLibreria;
	}

	public void cargarTabla(Libreria libreria) {
		String nombresColumnas[] = { "ISBN", "TITULO", "EDITORIAL", "AUTOR",
									"PRECIO", "TAGS", "ESTADO", "STOCK" };
		String[][] filasTabla = new String[libreria.size()][nombresColumnas.length];
		fillTabla(filasTabla, libreria);
		setColums(nombresColumnas, filasTabla);

	}

	private void fillTabla( String[][] filasTabla, Libreria libreria) {
		for (int i = 0; i < libreria.size(); i++) {
			filasTabla[i][0] = libreria.get(i).getIsbn();
			filasTabla[i][1] = libreria.get(i).getTitulo();
			filasTabla[i][2] = libreria.get(i).getEditorial();
			filasTabla[i][3] = libreria.get(i).getAutor();
			filasTabla[i][4] = libreria.get(i).getPrecio().toString();
			filasTabla[i][5] = libreria.get(i).getFormato();
			filasTabla[i][6] = libreria.get(i).getEstado();
			filasTabla[i][7] = libreria.get(i).getStock().toString();
		}
	}

	private void setColums(String[] nombresColumnas, String[][] filasTabla) {
		// Aqui creamos la Tabla
		DefaultTableModel tablaCompleta = new DefaultTableModel(filasTabla, nombresColumnas) {
			// PAra que las celdas no sean editables
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		// tableLibreria es el molde donde puede haber tablas, pero esta no es una
		// tabla, si no el molde
		tableLibreria.setModel(tablaCompleta);
	}
	
	public boolean tablaEstaSeleccionada() {

		return -1 != tableLibreria.getSelectedRow();
	}
	public String getIsbnTable() {
		byte fila = (byte) tableLibreria.getSelectedRow();
		return tableLibreria.getValueAt(fila, 0).toString();
	}
}
