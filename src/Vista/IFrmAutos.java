package Vista;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import Modelo.cls_Autos;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;

public class IFrmAutos extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtMarc;
	private JTextField txtAnio;
	private JTextField txtPlaca;
	private JTextField txtColor;
	private JTable table;
	private JTextField txtBusMec;
	public static DefaultTableModel modelo;
	cls_Autos autos = new cls_Autos();
	private JTextField txtFI;
	private JScrollPane scrollPane;
	private JTextField txtKM;
	private JTextField txtIngreso;
	private JTextField txtEstado;
	private JTextField txtMec;
	private JCheckBox check;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IFrmAutos frame = new IFrmAutos();
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
	public IFrmAutos() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameActivated(InternalFrameEvent e) {
				llenarTabla("");
			}
		});
		setBounds(100, 100, 820, 607);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 769, 531);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ingresar PEPESSSS");
		lblNewLabel.setBounds(10, 10, 335, 44);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Marca");
		lblNewLabel_1.setBounds(10, 114, 98, 16);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Año");
		lblNewLabel_1_2.setBounds(10, 164, 98, 16);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Placa");
		lblNewLabel_1_2_1.setBounds(10, 211, 98, 16);
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Color");
		lblNewLabel_1_2_1_1.setBounds(10, 257, 98, 16);
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel_1_2_1_1);
		
		txtMarc = new JTextField();
		txtMarc.setBounds(85, 116, 173, 19);
		panel.add(txtMarc);
		txtMarc.setColumns(10);
		
		txtAnio = new JTextField();
		txtAnio.setBounds(60, 166, 173, 19);
		txtAnio.setColumns(10);
		panel.add(txtAnio);
		
		txtPlaca = new JTextField();
		txtPlaca.setBounds(73, 213, 173, 19);
		txtPlaca.setColumns(10);
		panel.add(txtPlaca);
		
		txtColor = new JTextField();
		txtColor.setBounds(73, 259, 173, 19);
		txtColor.setColumns(10);
		panel.add(txtColor);
		
		JButton btnAgregar = new JButton("Ingresar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (verificar()) {
					JOptionPane.showMessageDialog(null, "Llene todos los campos");

				} else {
					autos.setMarca(txtMarc.getText());
					autos.setPlaca(txtPlaca.getText());
					autos.setAnio(txtAnio.getText());
					autos.setColor(txtColor.getText());
					autos.setKm(txtKM.getText());
					autos.setMecanico(txtMec.getText());
					autos.setIngreso(txtIngreso.getText());
					autos.setEstado(Integer.parseInt(txtEstado.getText()));

					if (autos.insertar()) {
						JOptionPane.showMessageDialog(null, "Registro Guardado Correctamente");
						lcajas();
						llenarTabla("");

					} else {
						JOptionPane.showMessageDialog(null, "Error al Guardar");
					}
				}
			}
		});
		btnAgregar.setBounds(10, 495, 130, 26);
		btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(btnAgregar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(289, 114, 438, 390);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		txtBusMec = new JTextField();
		txtBusMec.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				llenarTabla(txtBusMec.getText());
			}
		});
		txtBusMec.setBounds(289, 76, 153, 19);
		panel.add(txtBusMec);
		txtBusMec.setColumns(10);
		
		txtFI = new JTextField();
		txtFI.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				llenarTabla2(txtFI.getText());
			}
		});
		txtFI.setBounds(475, 76, 116, 19);
		panel.add(txtFI);
		txtFI.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Fecha Ingreso");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(475, 53, 98, 13);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Mecanico Encargado");
		lblNewLabel_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_1_2.setBounds(303, 55, 139, 13);
		panel.add(lblNewLabel_2_1_2);
		
		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("KM");
		lblNewLabel_1_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_2_1_1_1.setBounds(10, 308, 98, 16);
		panel.add(lblNewLabel_1_2_1_1_1);
		
		txtKM = new JTextField();
		txtKM.setColumns(10);
		txtKM.setBounds(73, 310, 173, 19);
		panel.add(txtKM);
		
		JLabel lblNewLabel_1_2_1_1_2 = new JLabel("Fecha ingreso");
		lblNewLabel_1_2_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_2_1_1_2.setBounds(10, 357, 130, 16);
		panel.add(lblNewLabel_1_2_1_1_2);
		
		txtIngreso = new JTextField();
		txtIngreso.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtIngreso.setColumns(10);
		txtIngreso.setBounds(150, 359, 122, 19);
		panel.add(txtIngreso);
		
		JLabel lblNewLabel_1_2_1_1_3 = new JLabel("Estado");
		lblNewLabel_1_2_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_2_1_1_3.setBounds(10, 398, 98, 16);
		panel.add(lblNewLabel_1_2_1_1_3);
		
		txtEstado = new JTextField();
		txtEstado.setColumns(10);
		txtEstado.setBounds(85, 397, 98, 19);
		panel.add(txtEstado);
		
		JLabel lblNewLabel_1_2_1_1_4 = new JLabel("Mecanico");
		lblNewLabel_1_2_1_1_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_2_1_1_4.setBounds(10, 444, 98, 16);
		panel.add(lblNewLabel_1_2_1_1_4);
		
		txtMec = new JTextField();
		txtMec.setColumns(10);
		txtMec.setBounds(106, 446, 173, 19);
		panel.add(txtMec);
		
		check = new JCheckBox("Terminados");
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llenarTabla3(checkb());
			}
		});
		check.setBounds(634, 75, 93, 21);
		panel.add(check);

	}
	public void llenarTabla(String Mecanico) {
		String[] cabeceras = {"cod", "Marca", "Año", "Placa", "Color", "KM", "Ingreso", "Mecanico","Estado"};
		modelo = new DefaultTableModel(autos.consultar(Mecanico), cabeceras);
		table.setModel(modelo);
	}
	public void llenarTabla2(String Fecha) {
		String[] cabeceras = {"cod", "Marca", "Año", "Placa", "Color", "KM", "Ingreso", "Mecanico","Estado"};
		modelo = new DefaultTableModel(autos.consultar2(Fecha), cabeceras);
		table.setModel(modelo);
	}
	
	public void llenarTabla3(boolean cheq) {
		String[] cabeceras = {"cod", "Marca", "Año", "Placa", "Color", "KM", "Ingreso", "Mecanico","Estado"};
		modelo = new DefaultTableModel(autos.consultar3(cheq), cabeceras);
		table.setModel(modelo);
	}
	public void lcajas() {
		txtMarc.setText(null);
		txtPlaca.setText(null);
		txtColor.setText(null);
		txtAnio.setText(null);
		txtIngreso.setText(null);
		txtEstado.setText(null);
		txtKM.setText(null);
		txtMec.setText(null);
	}
	public boolean verificar(){
		if (txtMarc.getText().equals("")||txtPlaca.getText().equals("")||txtColor.getText().equals("")||txtAnio.getText().equals("")) {
			return true;
		}else {
			return false;
		}
}
	public boolean checkb(){
		if (check.isSelected()) {
			return true;
		}else {
			return false;
		}
	}
}
