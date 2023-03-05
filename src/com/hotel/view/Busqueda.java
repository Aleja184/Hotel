package com.hotel.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.hotel.controller.BusquedaController;
import com.hotel.controller.HuespedController;
import com.hotel.controller.ReservasController;
import com.hotel.model.Huesped;
import com.hotel.model.Reservas;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloH;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private ReservasController reservasController;
	private HuespedController huespedController;
	private BusquedaController busquedaController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
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
	public Busqueda() {
		
		this.reservasController = new ReservasController();
		this.huespedController = new HuespedController();
		this.busquedaController = new BusquedaController();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		
		
		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), tbReservas, null);
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		
		cargarTablaReservas();
		
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), tbHuespedes, null);
		modeloH = (DefaultTableModel) tbHuespedes.getModel();
		modeloH.addColumn("Numero de Huesped");
		modeloH.addColumn("Nombre");
		modeloH.addColumn("Apellido");
		modeloH.addColumn("Fecha de Nacimiento");
		modeloH.addColumn("Nacionalidad");
		modeloH.addColumn("Telefono");
		modeloH.addColumn("Numero de Reserva");
		
		cargarTablaHuespedes();
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String palabraBusqueda = txtBuscar.getText();	
				if(palabraBusqueda.length()!=0) {
					try {
						Integer id = Integer.valueOf(palabraBusqueda.toString());
						limpiarTablaHuesped();
						cargarTablaHuespedes();
						limpiarTablaReservas();
						cargarTablaReservasBusqueda(id);
					} catch (Exception e2) {
						limpiarTablaReservas();
						cargarTablaReservas();
						limpiarTablaHuesped();
						cargarTablaHuespedesBusqueda(palabraBusqueda);
					}
				}else{
					limpiarTablaHuesped();
					limpiarTablaReservas();
					cargarTablaHuespedes();
					cargarTablaReservas();
				}
				
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		btnEditar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(tbHuespedes.isVisible()) {
					modificarHuesped();
					limpiarTablaHuesped();
					cargarTablaHuespedes();
				}else {
					modificarReserva();
					limpiarTablaReservas();
					cargarTablaReservas();
				}
			}
		});
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!tieneFilaElegidaHuesped()) {
					eliminarHuesped();
				}else if(!tieneFilaElegidaReservas()) {
					eliminarReserva();
				}
				limpiarTablaReservas();
				cargarTablaReservas();
				limpiarTablaHuesped();
				cargarTablaHuespedes();
			}
		});
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	
	}
	
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
	    
	    //Métodos reservas
	    private void cargarTablaReservas() {
	    	List<Reservas> reservas = this.reservasController.listar();
	    	reservas.forEach(reserva -> modelo.addRow(new Object[] {
	    			reserva.getId(),
	    			reserva.getFechaEntrada(),
	    			reserva.getFechaSalida(),
	    			reserva.getValor(),
	    			reserva.getFormaPago()
	    	}));
	    }
	    
	    private void limpiarTablaReservas() {
	    	for (int i = 0; i < modelo.getRowCount(); i++) {
	    		modelo.removeRow(i);
	    		i-=1;
	    		}
	    }
	    
	    private void cargarTablaReservasBusqueda(Integer id) {
	    	List<Reservas> reservas = this.busquedaController.listarReservas(id);
	    	reservas.forEach(reserva -> modelo.addRow(new Object[] {
	    			reserva.getId(),
	    			reserva.getFechaEntrada(),
	    			reserva.getFechaSalida(),
	    			reserva.getValor(),
	    			reserva.getFormaPago()
	    	}));
	    }
	    
	    private boolean tieneFilaElegidaReservas() {
	    	return tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedColumnCount() ==0;
	    }
	    
	    private void eliminarReserva() {
	    	if(tieneFilaElegidaReservas()) {
	    		JOptionPane.showMessageDialog(this, "Por favor, elije un item");
	    		return;
	    	}
	    	
	    	Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
	    		.ifPresent(fila->{
	    			Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
	    			this.reservasController.eliminar(id);
	    			modelo.removeRow(tbReservas.getSelectedRow());
	    			
	    			JOptionPane.showMessageDialog(this, "Reserva "+id+" eliminada");
	    		});
	    }
	    
	    private void modificarReserva() {
	    	if(tieneFilaElegidaReservas()) {
	    		JOptionPane.showMessageDialog(this, "Por favor, elije un item");
	            return;
	    	}
	    	
	    	Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
	    		.ifPresent(fila->{
	    			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    			Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
	    			String fechaEntrada = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString(); //Tomamos las fechas que están en las tablas como strings
	    			java.sql.Date fechaEntradaSQL = null; //Esta fecha tipo sql.Date está declarada, pero no inicializada. Puesto que en el try/catch se le asignará el valor. 
	    			LocalDate dateStart = null; //Variable para calcular la fecha de entrada
	    			String fechaSalida = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString();
	    			java.sql.Date fechaSalidaSQL = null;
	    			LocalDate dateEnd = null; //Variable para calcular la fecha de salida
	    			Double valor = null;
	    			try {
						Date parsedFE = dateFormat.parse(fechaEntrada);//Primero convertimos la fecha que tomamos como String a tipo util.Date usando el SimpleDateFormat.
						dateStart = parsedFE.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //Tomamos la fecha de entrada tipo LocalDate
						fechaEntradaSQL = new java.sql.Date(parsedFE.getTime());//Finalmente esa fecha tipo util.Date la pasamos a sql.Date
						Date parsedFS = dateFormat.parse(fechaSalida);
						dateEnd = parsedFS.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //Tomamos la fecha de salida tipo LocalDate
						fechaSalidaSQL = new java.sql.Date(parsedFS.getTime());
						
					} catch (ParseException e) {
						e.printStackTrace();
					}
	    			long numberOfDays = ChronoUnit.DAYS.between(dateStart, dateEnd ); //Calculamos el número de días entre la fecha de entrada y salida
					LocalDate today = LocalDate.now();
					long numberOfDaysToday = ChronoUnit.DAYS.between(today, dateStart); //Calculamos el número de días entre hoy y la fecha de entrada
					long costoFinal = 45900*numberOfDays; //Hacemos el costo de los días de estadia
					if(numberOfDaysToday<=0) { //Si el número de días entre hoy y la fecha de entrada es menor a 0, entonces el usuario deberá elegir una nueva fecha
						JOptionPane.showMessageDialog(null, "Debes seleccionar una fecha después de hoy");
					}else {
						if(costoFinal<=0) { //Si el costofinal es menor 0, osea que la fecha de entrada es después de la fecha de salida entonces el usuario deberá elegir otra fecha
							JOptionPane.showMessageDialog(null, "Debes seleccionar primero la fecha de llegada y luego la fecha de salida");
						}else {
							String costoString = String.valueOf(costoFinal);
							valor = Double.valueOf(costoString);
						}
					}
					String formaPago = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);
					
					Reservas reserva = new Reservas(id,fechaEntradaSQL, fechaSalidaSQL, valor, formaPago);
					this.reservasController.modificar(reserva);
					
					JOptionPane.showMessageDialog(this, "Reserva " +id+" actualizada");
	    		});
	    }
	    
	    //Métodos Huespedes
	    private void cargarTablaHuespedes() {
	    	List<Huesped> huespedes = this.huespedController.listar();
	    	huespedes.forEach(huesped -> modeloH.addRow(new Object[] {
	    			huesped.getId(),
	    			huesped.getNombre(),
	    			huesped.getApellido(),
	    			huesped.getFecha_nacimiento(),
	    			huesped.getNacionalidad(),
	    			huesped.getNumero(),
	    			huesped.getId_reserva()
	    	}));
	    }
	    private void limpiarTablaHuesped() {
	    	for (int i = 0; i < modeloH.getRowCount(); i++) {
	    		modeloH.removeRow(i);
	    		i-=1;
	    		}
	    }
	    
	   
	    private void cargarTablaHuespedesBusqueda(String apellido) {
	    	List<Huesped> huespedes = this.busquedaController.listarHuesped(apellido);
	    	huespedes.forEach(huesped -> modeloH.addRow(new Object[] {
	    			huesped.getId(),
	    			huesped.getNombre(),
	    			huesped.getApellido(),
	    			huesped.getFecha_nacimiento(),
	    			huesped.getNacionalidad(),
	    			huesped.getNumero(),
	    			huesped.getId_reserva()
	    	}));
	    }
	    
	   

	    
	    private boolean tieneFilaElegidaHuesped() {
	    	return tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0;
	    }
	    
	    private void eliminarHuesped() {
	    	if(tieneFilaElegidaHuesped()) {
	    		JOptionPane.showMessageDialog(this, "Por favor, elije un item");
	    		return;
	    	}
	    	
	    	Optional.ofNullable(modeloH.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
    		.ifPresent(fila->{
    			Integer id = Integer.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
    			this.huespedController.eliminar(id);
    			modeloH.removeRow(tbHuespedes.getSelectedRow());
    			
    			JOptionPane.showMessageDialog(this, "Huesped "+id+" eliminado");
    		});
	    }
	    
	    private void modificarHuesped() {
	    	if(tieneFilaElegidaHuesped()) {
	    		JOptionPane.showMessageDialog(this, "Por favor, elije un item");
	            return;
	    	}
	    	
	    	Optional.ofNullable(modeloH.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
	    		.ifPresent(fila->{
	    			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    			Integer id = Integer.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
	    			String nombre = (String) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 1);
	    			String apellido = (String) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 2);
	    			String fechaNacimiento = (String) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 3).toString();
	    			java.sql.Date fechaNacimientoSQL = null;
	    			try {
						Date parsed = dateFormat.parse(fechaNacimiento);
						fechaNacimientoSQL = new java.sql.Date(parsed.getTime());
					} catch (ParseException e) {
						e.printStackTrace();
					}
	    			String nacionalidad = (String) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 4);
	    			String numero = (String) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 5);
	    			Integer idReserva = Integer.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
	    			
	    			
	    			
	    			Huesped huesped = new Huesped(id, nombre, apellido, fechaNacimientoSQL, nacionalidad, numero, idReserva);
	    			this.huespedController.modificar(huesped);
	    			
	    			JOptionPane.showMessageDialog(this, "Huesped " +id+" actualizada");
	    		});
	    }
}

