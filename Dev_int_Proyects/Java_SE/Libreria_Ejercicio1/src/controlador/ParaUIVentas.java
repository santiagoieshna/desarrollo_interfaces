package controlador;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class ParaUIVentas {
	protected JSpinner spinner;
	private JLabel lblTotalVenta;
	protected JTextField txtIsbnVenta;
	protected JTextField txtTituloVenta;
	protected JTextField txtAutorVenta;
	protected JTextField txtEditorialVenta;
	protected JTextField txtFormatoVenta;
	protected JTextField textPrecioVenta;
	protected JTextField txtEstadoVenta;
	protected JButton btnRealizarVenta;
	
	
	void limpiarCampos() {
		lblTotalVenta.setText("");
		txtIsbnVenta.setText("");
		txtTituloVenta.setText("");
		txtAutorVenta.setText("");
		textPrecioVenta.setText("");
		txtEditorialVenta.setText("");
		txtFormatoVenta.setText("");
		txtEstadoVenta.setText("");
		setMaxSpinner(1);
	}
	
	public ParaUIVentas(JSpinner spinner, JLabel lblTotalVenta, JTextField txtIsbnVenta, JTextField txtTituloVenta,
			JTextField txtAutorVenta, JTextField txtEditorialVenta, JTextField txtFormatoVenta,
			JTextField textPrecioVenta, JTextField txtEstadoVenta, JButton btnRealizarVenta) {
		super();
		this.spinner = spinner;
		this.lblTotalVenta = lblTotalVenta;
		this.txtIsbnVenta = txtIsbnVenta;
		this.txtTituloVenta = txtTituloVenta;
		this.txtAutorVenta = txtAutorVenta;
		this.txtEditorialVenta = txtEditorialVenta;
		this.txtFormatoVenta = txtFormatoVenta;
		this.textPrecioVenta = textPrecioVenta;
		this.txtEstadoVenta = txtEstadoVenta;
		this.btnRealizarVenta = btnRealizarVenta;
	}

	public void setMaxSpinner(Integer stock) {
		spinner.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1),
				stock, Integer.valueOf(1)));
		ponerSpinnerNoEditable();
	}
	
	/**
	 * Metodo que hace que el TextField interno del Spinner no sea editable
	 */
	private void ponerSpinnerNoEditable() {
		JFormattedTextField tf = ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField();
	    tf.setEditable(false);
	}
	
	public void deshabilitarVenta() {
		btnRealizarVenta.setEnabled(false);
	}

	public void habilitarVenta() {
		btnRealizarVenta.setEnabled(true);
	}
}
