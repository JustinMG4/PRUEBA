package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Conexion.cls_conexion;
import Modelo.cls_Autos;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.MenuBar;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class frm_login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUs;
	private JTextField txtCt;
	IFrmAutos au = new IFrmAutos();
	private JDesktopPane jDesk;
	private JPanel panel;
	String sentencias;
	cls_conexion conex = new cls_conexion();
	cls_Autos autos = new cls_Autos();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frm_login frame = new frm_login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frm_login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 887, 682);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.setVisible(false);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Autos");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jDesk.add(au);
				au.show();
			}
		});
		menuBar.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		jDesk = new JDesktopPane();
		jDesk.setBounds(10, 10, 853, 597);
		contentPane.add(jDesk);
		
		panel = new JPanel();
		panel.setBounds(10, 10, 833, 577);
		jDesk.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Iniciar Sesión");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel.setBounds(250, 21, 282, 49);
		panel.add(lblNewLabel);
		
		JLabel ln = new JLabel("Usuario");
		ln.setFont(new Font("Tahoma", Font.BOLD, 16));
		ln.setBounds(86, 233, 127, 13);
		panel.add(ln);
		
		JLabel lblNewLabel_1_1 = new JLabel("Contraseña");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(86, 283, 127, 13);
		panel.add(lblNewLabel_1_1);
		
		txtUs = new JTextField();
		txtUs.setBounds(171, 232, 187, 19);
		panel.add(txtUs);
		txtUs.setColumns(10);
		
		txtCt = new JTextField();
		txtCt.setBounds(197, 282, 161, 19);
		panel.add(txtCt);
		txtCt.setColumns(10);
		
		JButton btnNewButton = new JButton("Iniciar Sesión");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = txtUs.getText();
		        String clave = txtCt.getText();
				boolean verificar = autos.login(usuario, clave);
				if (verificar) {
					JOptionPane.showMessageDialog(null, "Iniciando Sesion");
					menuBar.setVisible(true);
					panel.setVisible(false);
					
				}else {
					JOptionPane.showMessageDialog(null, "Credenciales incorrectas o fuera de servicio");
				}
			}
		});
		btnNewButton.setBounds(303, 385, 173, 39);
		panel.add(btnNewButton);
	}
}
