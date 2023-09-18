package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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



	/**
	 * Create the frame.
	 */
	public UI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
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
		panelLibro.setLayout(new GridLayout(6, 2, 0, 0));
		
		JLabel lblISBN = new JLabel("ISBN");
		panelLibro.add(lblISBN);
		
		txtISBN = new JTextField();
		txtISBN.setColumns(10);
		panelLibro.add(txtISBN);
		
		JLabel lblTitulo = new JLabel("Titulo");
		panelLibro.add(lblTitulo);
		
		txtTitulo = new JTextField();
		panelLibro.add(txtTitulo);
		txtTitulo.setColumns(10);
		
		JLabel lblAutor = new JLabel("Autor");
		panelLibro.add(lblAutor);
		
		txtAutor = new JTextField();
		txtAutor.setColumns(10);
		panelLibro.add(txtAutor);
		
		JLabel lblEditorial = new JLabel("Editorial");
		panelLibro.add(lblEditorial);
		
		txtEditorial = new JTextField();
		txtEditorial.setColumns(10);
		panelLibro.add(txtEditorial);
		
		JLabel lblPrecio = new JLabel("Precio");
		panelLibro.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		panelLibro.add(txtPrecio);
		
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
