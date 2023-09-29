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
	
	public void mensajeInfo(String tituloMensaje, String mensaje) {
		JOptionPane.showMessageDialog(this.contentPane,
				mensaje, tituloMensaje, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public int mensajeVenderSioNo(Libro libro, Integer cantidad, Float precioVenta) {
		
		String titulo = "¿Realizar Venta: "+libro.getTitulo()+"?";
		String mensaje= "ISBN: "+libro.getIsbn()+"\nTitulo: "+libro.getTitulo()
				+"\nPrecio unidad: "+libro.getPrecio().toString()+" €\nCantidad: "+cantidad;  
		mensaje += (cantidad>1) ? " unidad":" unidades";
		mensaje += "\nInteres:"+libro.INTERES_VENTA+"\n-----------------\nTotal: "+precioVenta;
		
		return JOptionPane.showConfirmDialog(this.contentPane,
				mensaje, titulo , JOptionPane.YES_NO_OPTION);
	}

	public void mensajeFactura(Libro libro, Float precioVenta) {	
		mensajeInfo("Ejemplar Vendido", libro.toString());
	}
	
}
