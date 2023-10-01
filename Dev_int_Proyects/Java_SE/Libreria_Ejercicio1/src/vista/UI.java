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
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.border.CompoundBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SpinnerNumberModel;
import java.awt.Dimension;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UI extends JFrame {

	protected JPanel contentPane;
	protected JTextField txtTitulo;
	protected JTextField txtISBN;
	protected JTextField txtPrecio;
	protected JTextField txtAutor;
	protected JTextField txtEditorial;
	protected JRadioButton rdbtnCartone;
	protected JRadioButton rdbtnEspiral;
	protected JRadioButton rdbtnGrapada;
	protected JRadioButton rdbtnRustica;
	protected JRadioButton rdbtnReedicion;
	protected JRadioButton rdbtnNovedad;
	protected ButtonGroup grupoFormato;
	protected ButtonGroup grupoEstado;
	protected JButton btnSalir;
	protected JButton btnBorrar;
	protected JButton btnLimpiar;
	protected JButton btnGuardar;
	protected JTable tableLibreria;
	protected JTabbedPane tabbedPane ;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblFormato;
	private JPanel panelVenta;
	private JLabel lblIsnV;
	private JLabel lblTituloVenta;
	private JLabel lblAutorVentas;
	private JLabel lblEditorialVentas;
	private JLabel lblPrecioVenta;
	private JLabel lblEstado;
	protected JTextField txtIsbnVenta;
	protected JTextField txtTituloVenta;
	protected JTextField txtAutorVenta;
	protected JTextField txtEditorialVenta;
	protected JTextField txtFormatoVenta;
	private JLabel lblFormatoVenta;
	private JLabel lblEstadoVenta;
	protected JTextField txtEstadoVenta;
	protected JTextField textPrecioVenta;
	private JLabel lblTotal;
	protected JLabel lblTotalVenta;
	protected JButton btnVender;
	protected JSpinner spinner;
	protected JTextField textBuscador;
	private JPanel panelLibreria;
	protected JButton btnBuscar;
	protected JComboBox filtro;
	protected JButton btnRealizarVenta;
	protected JButton btnComprar;


	/**
	 * Create the frame.
	 */
	public UI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UI.class.getResource("/res/imagenes/iconoApp.png")));
		setTitle("SantiBook");
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
		panelLibro.setLayout(new MigLayout("", "[129.00px][235px,grow,right][350px,grow]",
				"[grow][grow][grow][grow][grow][grow][grow]"));
		
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
		
		panelLibreria = new JPanel();
		tabbedPane.addTab("Libreria", null, panelLibreria, null);
		panelLibreria.setLayout(new MigLayout("", "[400,grow][350,grow][150]", "[50,grow][371px]"));
		
		filtro = new JComboBox();
		filtro.setModel(new DefaultComboBoxModel(new String[] {"ISBN", "TITULO", "AUTOR", "EDITORIAL"}));
		panelLibreria.add(filtro, "cell 0 0,alignx right");
		
		textBuscador = new JTextField();
		panelLibreria.add(textBuscador, "cell 1 0,growx");
		textBuscador.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		
		
		panelLibreria.add(btnBuscar, "cell 2 0");
		
		JScrollPane scrollPane = new JScrollPane();
		panelLibreria.add(scrollPane, "cell 0 1 3 1,grow");
		
		tableLibreria = new JTable();
		scrollPane.setViewportView(tableLibreria);
		
		panelVenta = new JPanel();
		panelVenta.setBackground(new Color(202, 232, 232));
		tabbedPane.addTab("Compra/Venta", null, panelVenta, null);
		panelVenta.setLayout(new MigLayout("", "[50,center][50,fill][150,center][96.00][80,grow]", 
				"[40,center][40][40][40][40][40][40][40]"));
		
		lblIsnV = new JLabel("ISBN");
		panelVenta.add(lblIsnV, "cell 1 0,alignx trailing");
		
		txtIsbnVenta = new JTextField();
		txtIsbnVenta.setEditable(false);
		panelVenta.add(txtIsbnVenta, "cell 2 0,growx");
		txtIsbnVenta.setColumns(10);
		
		lblTituloVenta = new JLabel("Titulo");
		panelVenta.add(lblTituloVenta, "cell 1 1,alignx trailing");
		
		txtTituloVenta = new JTextField();
		txtTituloVenta.setEditable(false);
		panelVenta.add(txtTituloVenta, "cell 2 1,growx");
		txtTituloVenta.setColumns(10);
		
		lblAutorVentas = new JLabel("Autor");
		panelVenta.add(lblAutorVentas, "cell 1 2,alignx trailing");
		
		txtAutorVenta = new JTextField();
		txtAutorVenta.setEditable(false);
		panelVenta.add(txtAutorVenta, "cell 2 2,growx");
		txtAutorVenta.setColumns(10);
		
		lblEditorialVentas = new JLabel("Editorial");
		panelVenta.add(lblEditorialVentas, "cell 1 3,alignx trailing");
		
		txtEditorialVenta = new JTextField();
		txtEditorialVenta.setEditable(false);
		panelVenta.add(txtEditorialVenta, "cell 2 3,growx");
		txtEditorialVenta.setColumns(10);
		
		lblFormatoVenta = new JLabel("Formato");
		panelVenta.add(lblFormatoVenta, "cell 1 4");
		
		txtFormatoVenta = new JTextField();
		txtFormatoVenta.setEditable(false);
		panelVenta.add(txtFormatoVenta, "cell 2 4,growx");
		txtFormatoVenta.setColumns(10);
		
		lblEstadoVenta = new JLabel("Estado");
		panelVenta.add(lblEstadoVenta, "cell 1 5,alignx trailing");
		
		txtEstadoVenta = new JTextField();
		txtEstadoVenta.setEditable(false);
		txtEstadoVenta.setColumns(10);
		panelVenta.add(txtEstadoVenta, "cell 2 5,growx");
		
		lblPrecioVenta = new JLabel("Precio");
		panelVenta.add(lblPrecioVenta, "cell 1 6,alignx trailing");
		
		textPrecioVenta = new JTextField();
		textPrecioVenta.setEditable(false);
		textPrecioVenta.setColumns(10);
		panelVenta.add(textPrecioVenta, "cell 2 6,growx");
		
		btnComprar = new JButton("Realizar Compra");
		
		panelVenta.add(btnComprar, "cell 4 6,alignx center");
		
		lblTotal = new JLabel("Total");
		panelVenta.add(lblTotal, "cell 1 7");
		
		lblTotalVenta = new JLabel("----");
		panelVenta.add(lblTotalVenta, "cell 2 7");
		
		spinner = new JSpinner();
		
		spinner.setSize(new Dimension(10, 0));
		
		spinner.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1),
				null, Integer.valueOf(1)));

		panelVenta.add(spinner, "cell 3 7");
		
		btnRealizarVenta = new JButton("Realizar Venta");
		btnRealizarVenta.setEnabled(false);
		panelVenta.add(btnRealizarVenta, "cell 4 7,alignx center,aligny center");
		
		JPanel panelBotones = new JPanel();
		contentPane.add(panelBotones, BorderLayout.SOUTH);
		
		btnGuardar = new JButton("Guardar");
		
		panelBotones.add(btnGuardar);
		
		btnLimpiar = new JButton("Limpiar");
		panelBotones.add(btnLimpiar);
		
		btnBorrar = new JButton("Borrar");
		panelBotones.add(btnBorrar);
		
		btnVender = new JButton("Compra/Venta");
		
		panelBotones.add(btnVender);
		
		btnSalir = new JButton("Salir");
	
		
		panelBotones.add(btnSalir);
	}
	
	// Icono del JFrame
	@Override
	public Image getIconImage() {
		Image retValue = Toolkit.getDefaultToolkit()
						.getImage(ClassLoader.getSystemResource("res/imagenes/iconoApp.png"));
		
		return retValue;
	}
	

	protected String getTitulo() {
		return txtTitulo.getText().toString();
	}

	protected String getISBN() {
		return txtISBN.getText().toString();
	}

	protected String getPrecio() {
		return txtPrecio.getText().toString();
	}

	protected String getAutor() {
		return txtAutor.getText().toString();
	}

	protected String getEditorial() {
		return txtEditorial.getText().toString();
	}


}
