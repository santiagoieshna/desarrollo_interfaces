package vista;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.CompoundBorder;

public class UI extends JFrame {

	protected JPanel contentPane;
	protected JTextField txtTitulo;
	protected JTextField txtISBN;
	protected JTextField txtPrecio;
	protected JTextField txtAutor;
	protected JTextField txtEditorial;
	protected JButton btnSalir;
	protected JButton btnBorrar;
	protected JButton btnLimpiar;
	protected JButton btnGuardar;
	protected JTable tableLibreria;
	protected JTabbedPane tabbedPane ;
	private JPanel panel;
	private JPanel panel_1;
	protected JRadioButton rdbtnCartone;
	protected JRadioButton rdbtnEspiral;
	protected JRadioButton rdbtnGrapada;
	protected JRadioButton rdbtnRustica;
	protected JRadioButton rdbtnReedicion;
	protected JRadioButton rdbtnNovedad;
	protected ButtonGroup grupoFormato;
	protected ButtonGroup grupoEstado;
	private JLabel lblFormato;
	private JPanel panelVenta;
	private JLabel lblIsnV;
	private JLabel lblTituloVenta;
	private JLabel lblAutorVentas;
	private JLabel lblEditorialVentas;
	private JLabel lblPrecioVenta;
	private JLabel lblEstado;
	private JTextField txtIbnVenta;
	private JTextField txtTituloVenta;
	private JTextField txtAutorVenta;
	private JTextField txtEditorialVenta;
	private JTextField txtFormatoVenta;
	private JLabel lblFormatoVenta;
	private JLabel lblEstadoVenta;
	private JTextField txtEstadoVenta;
	private JTextField textField;
	private JLabel lblTotal;
	protected JLabel lblTotalVenta;
	


	/**
	 * Create the frame.
	 */
	public UI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 712, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		panelTop.setBackground(new Color(128, 128, 255));
		contentPane.add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("LIBRERIA");
		lblNewLabel.setForeground(new Color(226, 226, 226));
		lblNewLabel.setFont(new Font("Microsoft Himalaya", Font.BOLD, 22));
		panelTop.add(lblNewLabel);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelLibro = new JPanel();
		panelLibro.setBackground(new Color(202, 232, 232));
		tabbedPane.addTab("Libro", null, panelLibro, null);
		panelLibro.setLayout(new MigLayout("", "[129.00px][235px,grow,right][350px,grow]", "[grow][grow][grow][grow][grow][grow][grow]"));
		
		JLabel lblISBN = new JLabel("ISBN");
		panelLibro.add(lblISBN, "cell 0 0,grow");
		
		txtISBN = new JTextField();
		txtISBN.setColumns(10);
		panelLibro.add(txtISBN, "cell 1 0,grow");
		
		JLabel lblTitulo = new JLabel("Titulo");
		panelLibro.add(lblTitulo, "cell 0 1,grow");
		
		txtTitulo = new JTextField();
		panelLibro.add(txtTitulo, "cell 1 1,grow");
		txtTitulo.setColumns(10);
		
		JLabel lblAutor = new JLabel("Autor");
		panelLibro.add(lblAutor, "cell 0 2,grow");
		
		txtAutor = new JTextField();
		txtAutor.setColumns(10);
		panelLibro.add(txtAutor, "cell 1 2,grow");
		
		JLabel lblEditorial = new JLabel("Editorial");
		panelLibro.add(lblEditorial, "cell 0 3,grow");
		
		txtEditorial = new JTextField();
		txtEditorial.setColumns(10);
		panelLibro.add(txtEditorial, "cell 1 3,grow");
		
		JLabel lblPrecio = new JLabel("Precio");
		panelLibro.add(lblPrecio, "cell 0 4,grow");
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		panelLibro.add(txtPrecio, "cell 1 4,grow");
		
		lblFormato = new JLabel("Formato");
		panelLibro.add(lblFormato, "cell 0 5");
		
		panel = new JPanel();
		panel.setBorder(new CompoundBorder());
		panelLibro.add(panel, "cell 1 5,grow");
		
		grupoFormato = new ButtonGroup();
		
		
		rdbtnCartone = new JRadioButton("Cartoné");
		rdbtnCartone.setActionCommand(rdbtnCartone.getText());
		rdbtnRustica = new JRadioButton("Rústica");
		rdbtnRustica.setActionCommand(rdbtnRustica.getText());
		rdbtnGrapada = new JRadioButton("Grapada");
		rdbtnGrapada.setActionCommand(rdbtnGrapada.getText());
		rdbtnEspiral = new JRadioButton("Espiral");
		rdbtnEspiral.setActionCommand(rdbtnEspiral.getText());
		
		// Metemos los botones en el ButtonGroup
		grupoFormato.add(rdbtnCartone);
		grupoFormato.add(rdbtnEspiral);
		grupoFormato.add(rdbtnGrapada);
		grupoFormato.add(rdbtnNovedad);
		grupoFormato.add(rdbtnReedicion);
		grupoFormato.add(rdbtnRustica);
		panel.setLayout(new GridLayout(0, 4, 0, 0));
		
		// Metemos los botones en el panel
		panel.add(rdbtnCartone);
		panel.add(rdbtnRustica);
		panel.add(rdbtnGrapada);
		panel.add(rdbtnEspiral);
		
		lblEstado = new JLabel("Estado");
		panelLibro.add(lblEstado, "cell 0 6");
		
		panel_1 = new JPanel();
		panelLibro.add(panel_1, "cell 1 6,grow");
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		rdbtnReedicion = new JRadioButton("Reedición");
		rdbtnReedicion.setActionCommand(rdbtnReedicion.getText());
		panel_1.add(rdbtnReedicion);
		
		rdbtnNovedad = new JRadioButton("Novedad");
		rdbtnNovedad.setActionCommand(rdbtnNovedad.getText());
		panel_1.add(rdbtnNovedad);
		
		grupoEstado = new ButtonGroup();
		// Botones estado en el GrupoEstado
		grupoEstado.add(rdbtnReedicion);
		grupoEstado.add(rdbtnNovedad);
		
		JPanel panelLibreria = new JPanel();
		tabbedPane.addTab("Libreria", null, panelLibreria, null);
		panelLibreria.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelLibreria.add(scrollPane);
		
		tableLibreria = new JTable();
		scrollPane.setViewportView(tableLibreria);
		
		panelVenta = new JPanel();
		panelVenta.setBackground(new Color(202, 232, 232));
		tabbedPane.addTab("Venta", null, panelVenta, null);
		panelVenta.setLayout(new MigLayout("", "[50,center][50,fill][150,center][80][80]", "[40,center][40][40][40][40][40][40][40]"));
		
		lblIsnV = new JLabel("ISBN");
		panelVenta.add(lblIsnV, "cell 1 0,alignx trailing");
		
		txtIbnVenta = new JTextField();
		panelVenta.add(txtIbnVenta, "cell 2 0,growx");
		txtIbnVenta.setColumns(10);
		
		lblTituloVenta = new JLabel("Titulo");
		panelVenta.add(lblTituloVenta, "cell 1 1,alignx trailing");
		
		txtTituloVenta = new JTextField();
		panelVenta.add(txtTituloVenta, "cell 2 1,growx");
		txtTituloVenta.setColumns(10);
		
		lblAutorVentas = new JLabel("Autor");
		panelVenta.add(lblAutorVentas, "cell 1 2,alignx trailing");
		
		txtAutorVenta = new JTextField();
		panelVenta.add(txtAutorVenta, "cell 2 2,growx");
		txtAutorVenta.setColumns(10);
		
		lblEditorialVentas = new JLabel("Editorial");
		panelVenta.add(lblEditorialVentas, "cell 1 3,alignx trailing");
		
		txtEditorialVenta = new JTextField();
		panelVenta.add(txtEditorialVenta, "cell 2 3,growx");
		txtEditorialVenta.setColumns(10);
		
		lblFormatoVenta = new JLabel("Formato");
		panelVenta.add(lblFormatoVenta, "cell 1 4");
		
		txtFormatoVenta = new JTextField();
		panelVenta.add(txtFormatoVenta, "cell 2 4,growx");
		txtFormatoVenta.setColumns(10);
		
		lblEstadoVenta = new JLabel("Estado");
		panelVenta.add(lblEstadoVenta, "cell 1 5,alignx trailing");
		
		txtEstadoVenta = new JTextField();
		txtEstadoVenta.setColumns(10);
		panelVenta.add(txtEstadoVenta, "cell 2 5,growx");
		
		lblPrecioVenta = new JLabel("Precio");
		panelVenta.add(lblPrecioVenta, "cell 1 6,alignx trailing");
		
		textField = new JTextField();
		textField.setColumns(10);
		panelVenta.add(textField, "cell 2 6,growx");
		
		lblTotal = new JLabel("Total");
		panelVenta.add(lblTotal, "cell 1 7");
		
		lblTotalVenta = new JLabel("----");
		panelVenta.add(lblTotalVenta, "cell 2 7");
		
		JPanel panelBotones = new JPanel();
		contentPane.add(panelBotones, BorderLayout.SOUTH);
		
		btnGuardar = new JButton("Guardar");
		
		panelBotones.add(btnGuardar);
		
		btnLimpiar = new JButton("Limpiar");
		panelBotones.add(btnLimpiar);
		
		btnBorrar = new JButton("Borrar");
		panelBotones.add(btnBorrar);
		
		btnSalir = new JButton("Salir");
	
		
		panelBotones.add(btnSalir);
	}
	
	protected String getIsbn() {
		return txtISBN.getText();
	}
	protected String getITitulo() {
		return txtISBN.getText();
	}
	protected String getPrecio() {
		return txtISBN.getText();
	}
	protected String getTitulo() {
		return txtISBN.getText();
	}
	protected String getAutor() {
		return txtISBN.getText();
	}
	protected String getEditorial() {
		return txtISBN.getText();
	}

}
