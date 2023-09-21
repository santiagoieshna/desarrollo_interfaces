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
	protected ButtonGroup grupoTag;
	


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
		
		panel = new JPanel();
		panelLibro.add(panel, "cell 1 5,grow");
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		grupoTag = new ButtonGroup();
		// TODO insertar boton grupo
		
		rdbtnCartone = new JRadioButton("Cartoné");
		panel.add(rdbtnCartone);
		
		rdbtnRustica = new JRadioButton("Rústica");
		panel.add(rdbtnRustica);
		
		rdbtnGrapada = new JRadioButton("Grapada");
		panel.add(rdbtnGrapada);
		
		rdbtnEspiral = new JRadioButton("Espiral");
		panel.add(rdbtnEspiral);
		
		panel_1 = new JPanel();
		panelLibro.add(panel_1, "cell 1 6,grow");
		
		rdbtnReedicion = new JRadioButton("Reedición");
		panel_1.add(rdbtnReedicion);
		
		rdbtnNovedad = new JRadioButton("Novedad");
		panel_1.add(rdbtnNovedad);
		
		JPanel panelLibreria = new JPanel();
		tabbedPane.addTab("Libreria", null, panelLibreria, null);
		panelLibreria.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelLibreria.add(scrollPane);
		
		tableLibreria = new JTable();
		scrollPane.setViewportView(tableLibreria);
		
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

}
