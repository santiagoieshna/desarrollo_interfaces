package controlador;

import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import modelo.Libro;

public class ParaUILibroPanel {
	private JTextField txtTitulo;
	private JTextField txtISBN;
	private JTextField txtPrecio;
	private JTextField txtAutor;
	private JTextField txtEditorial;
	private ButtonGroup grupoFormato;
	private ButtonGroup grupoEstado;

	public ParaUILibroPanel(JTextField txtTitulo, JTextField txtISBN, JTextField txtPrecio, JTextField txtAutor,
			JTextField txtEditorial, ButtonGroup grupoFormato, ButtonGroup grupoEstado) {
		super();
		this.txtTitulo = txtTitulo;
		this.txtISBN = txtISBN;
		this.txtPrecio = txtPrecio;
		this.txtAutor = txtAutor;
		this.txtEditorial = txtEditorial;
		this.grupoFormato = grupoFormato;
		this.grupoEstado = grupoEstado;
	}

	public Libro guardarLibro() {
		String isbn = txtISBN.getText();
		String autor = txtAutor.getText();
		String titulo = txtTitulo.getText();
		String editorial = txtEditorial.getText();
		Float precio = Float.parseFloat(txtPrecio.getText());
		String formato = getFormato();
		String estado = getEstado();
		Integer stock = 0;
		return new Libro(isbn, titulo, autor, editorial, precio, formato, estado, stock);
	}


	private void selectedFormato(String formatoLibro) {

		Enumeration<AbstractButton> botones;
		botones = grupoFormato.getElements();

		while (botones.hasMoreElements()) {
			AbstractButton radioBoton = botones.nextElement();
			if ((radioBoton.getText().equals(formatoLibro)))
				radioBoton.setSelected(true);
		}
	}

	private void selectedEstado(String estadoLibro) {
		Enumeration<AbstractButton> botones;
		botones = grupoEstado.getElements();
		while (botones.hasMoreElements()) {
			AbstractButton radioBoton = botones.nextElement();
			if ((radioBoton.getText().equals(estadoLibro)))
				radioBoton.setSelected(true);
		}
	}

	public void rellenarCamposParaModificar(JTable table, int linea) {

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

	public void rellenarCamposParaModificar(Libro libro) {
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

	public void desabilitarISBNCampo() {
		txtISBN.setEditable(false);
	}
	
	public void habilitarISBN() {
		txtISBN.setEditable(true);
	}

	private String getFormato() {
		return grupoFormato.getSelection().getActionCommand();
	}

	private String getEstado() {
		return grupoEstado.getSelection().getActionCommand();
	}
	public void limpiarCampos() {
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

}
