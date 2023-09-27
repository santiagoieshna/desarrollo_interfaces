package modelo;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Mensajes {
	private JPanel contentPane;
	
	
	public Mensajes(JPanel contentPane) {
		this.contentPane = contentPane;
	}

	public int mensajeSioNo(String titulo, String mensaje) {
		return JOptionPane.showConfirmDialog(this.contentPane,
				mensaje, titulo , JOptionPane.YES_NO_OPTION);
	}

	public void mensajeError(String tituloMensaje, String mensaje) {
		JOptionPane.showMessageDialog(this.contentPane,
				mensaje, tituloMensaje, JOptionPane.ERROR_MESSAGE);
	}
}
