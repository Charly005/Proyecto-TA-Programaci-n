package App_Doctor.Pantallas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

public class AgendaDoctor extends JPanel implements ActionListener {
    // String nombres, apellidos, genero, telefono, direccion, curp, fecha, hora,
    // tipo, motivo;
    JLabel nomLlb, apellidoLbl, generoLbl, telefonoLbl, direccionLbl, curpLbl, fechaLbl, horaLbl, tipoLbl, motivoLbl;
    JLabel lblNombreCliente, lblCurp, lblGenero, lblTelefono, lblDireccion, lblFecha, lblHora, lblTipo, lblAreaMotivo;
    JTextArea txtAreaMotivo, txtDireccion;

    JTextField txtName;
    JTextField txtLastName;
    JTextField txtCellPhone;
    JTextField txtAdress;// direcion
    JTextField txtId;
    JTextField txtReason;// motivo
    JTextField txtGenero;
    JTextField txtType;
    JFormattedTextField cbDate;
    JFormattedTextField cbHour;

    JComboBox<String> sexo;
    JComboBox<String> type;

    JTable tablaClientes;
    JButton btnGuardar;
    JButton btnCancelar;
    DefaultTableModel modelo = new DefaultTableModel();
    ArrayList<DatosCliente> listaCliente = new ArrayList<DatosCliente>();

    public AgendaDoctor() {
        //this.setSize(900, 600);
        init();
        actualizarTabla();

        tablaClientes.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // Para evitar llamadas múltiples al seleccionar una fila
                int selectedRow = tablaClientes.getSelectedRow();
                if (selectedRow != -1) {
                    mostrarInformacionCliente(selectedRow);
                }
            }
        });
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == btnGuardar) {
                DatosCliente clienteNuevo = new DatosCliente();
                clienteNuevo.setCurp(txtId.getText()); //
                clienteNuevo.setNombres(txtName.getText()); //
                clienteNuevo.setApellidos(txtLastName.getText()); //
                clienteNuevo.setFecha(cbDate.getText().toString());//
                clienteNuevo.setHora(cbHour.getText().toString());//
                clienteNuevo.setGenero(sexo.getSelectedItem().toString());//
                clienteNuevo.setDireccion(txtAdress.getText());//
                clienteNuevo.setTelefono(txtCellPhone.getText());//
                clienteNuevo.setMotivo(txtReason.getText());//
                clienteNuevo.setTipo(type.getSelectedItem().toString());//

                listaCliente.add(clienteNuevo);
                actualizarTabla();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar Cliente");
        }
        try {
            if (e.getSource() == btnCancelar) {
                txtId.setText("");
                txtName.setText("");
                txtLastName.setText("");
                cbDate.setText("");
                cbHour.setText("");
                txtAdress.setText("");
                txtCellPhone.setText("");
                sexo.setSelectedIndex(0);
                type.setSelectedIndex(0);

            }

        } catch (Exception exc) {
            JOptionPane.showMessageDialog(this, "Error al borrar");
        }

    }

    public void init() {
        JPanel pnlPrincipal = new JPanel(new BorderLayout());
        // pnlPrincipal.setBackground(Color.yellow);

        // Panel principal superior
        JPanel pnlSuperior = new JPanel(new BorderLayout());
        // pnlSuperior.setBackground(Color.BLUE);
        pnlPrincipal.add(pnlSuperior, "North");
        // Panel formulario en superior
        JPanel pnlForm1 = new JPanel(new BorderLayout());
        pnlForm1.setPreferredSize(new Dimension(250, 300));
        pnlForm1.setMaximumSize(new Dimension(400, 400));
        pnlForm1.setMinimumSize(new Dimension(100, 300));
        // pnlForm1.setBackground(Color.YELLOW);
        pnlSuperior.add(pnlForm1, "Center");

        JPanel pnlForm = new JPanel(new GridLayout(6, 3));
      //  pnlForm.setBackground(Color.GREEN);
        pnlForm1.add(pnlForm, "North");

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botones.setPreferredSize(new Dimension(250, 40));
        botones.setMaximumSize(new Dimension(400, 40));
        botones.setMinimumSize(new Dimension(200, 40));
        // botones.setBackground(Color.blue);
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");

        btnGuardar.setPreferredSize(new Dimension(100, 30));
        btnCancelar.setPreferredSize(new Dimension(100, 30));

        botones.add(btnGuardar);
        botones.add(btnCancelar);

        pnlForm1.add(botones, "South");

        // MOTIVO PNL FORM1---FORM
        JPanel motiJPanel = new JPanel(new BorderLayout());
        pnlForm1.add(motiJPanel, "Center");
        motiJPanel.add(new JLabel("Motivo de consulta", SwingConstants.CENTER), "North");
        motiJPanel.setPreferredSize(new Dimension(0, 100));

        // INFORMACIÓN DEL FORMULARIO
        txtName = new JTextField();
        txtName.setPreferredSize(new Dimension(80, 20));
        txtLastName = new JTextField();
        txtLastName.setPreferredSize(new Dimension(80, 20));
        txtCellPhone = new JTextField();
        txtAdress = new JTextField();
        txtAdress.setPreferredSize(new Dimension(80, 20));
        txtId = new JTextField();
        txtId.setPreferredSize(new Dimension(80, 20));
        txtReason = new JTextField();
        txtReason.setPreferredSize(new Dimension(100, 50));
        motiJPanel.add(txtReason, "Center");

        String[] elegirGenero = { "Masculino", "Femenino" };
        sexo = new JComboBox<>(elegirGenero);

        String[] elegirTipo = { "Presencial", "En linea" };
        type = new JComboBox<>(elegirTipo);

        // --------Añadimos al pnlForm
        pnlForm.add(new JLabel("Nombre(s)", SwingConstants.CENTER));
        pnlForm.add(new JLabel("Apellido(s)", SwingConstants.CENTER));
        pnlForm.add(new JLabel("Genero", SwingConstants.CENTER));
        pnlForm.add(txtName);
        pnlForm.add(txtLastName);
        pnlForm.add(sexo);
        pnlForm.add(new JLabel("Telefono", SwingConstants.CENTER));
        pnlForm.add(new JLabel("Direccion", SwingConstants.CENTER));
        pnlForm.add(new JLabel("CURP", SwingConstants.CENTER));
        pnlForm.add(txtCellPhone);
        pnlForm.add(txtAdress);
        pnlForm.add(txtId);
        pnlForm.add(new JLabel("Fecha", SwingConstants.CENTER));
        pnlForm.add(new JLabel("Hora", SwingConstants.CENTER));
        pnlForm.add(new JLabel("Tipo", SwingConstants.CENTER));
        // FORMATEO DE FECHA
        try {
            MaskFormatter format = new MaskFormatter("##/##/####");
            format.setPlaceholderCharacter('0');
            cbDate = new JFormattedTextField(format);
            pnlForm.add(cbDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // FORMATEO DE HORA
        try {
            MaskFormatter formatter = new MaskFormatter("##:##");
            formatter.setPlaceholderCharacter('0');
            cbHour = new JFormattedTextField(formatter);
            pnlForm.add(cbHour);
        } catch (Exception e) {
            e.printStackTrace();
        }
        pnlForm.add(type);

        // Panel listaClientes en superior
        JPanel pnlLista = new JPanel(new GridLayout(1, 1));
        // setBackground(Color.LIGHT_GRAY);
        pnlLista.setPreferredSize(new Dimension(500, 300));
        pnlSuperior.add(pnlLista, "East");

        // Crear tabla para panelLista
        modelo.addColumn("CURP");
        modelo.addColumn("Nombre(s)");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Fecha");
        modelo.addColumn("Hora de Consulta");
        tablaClientes = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tablaClientes);

        pnlLista.add(scrollPane);

        // Panel principal centro
        JPanel pnlCentro = new JPanel(new BorderLayout());
        pnlPrincipal.add(pnlCentro, "Center");

        JPanel encabezado = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        encabezado.add(new JLabel("DETALLES DE LA CITA", SwingConstants.CENTER));

        JPanel tablas = new JPanel(new FlowLayout());
        tablas.setPreferredSize(new Dimension(400, 180));
        tablas.setMaximumSize(new Dimension(400, 400));
        tablas.setMinimumSize(new Dimension(300, 180));
        JPanel pnlNombreCliente = new JPanel(new FlowLayout());
        JLabel lblNomb = new JLabel("NOMBRE(S): ");
        pnlNombreCliente.add(lblNomb);
        lblNombreCliente = new JLabel(" ");
        pnlNombreCliente.add(lblNombreCliente);

        pnlNombreCliente.setPreferredSize(new Dimension(300, 40));
        pnlNombreCliente.setMaximumSize(new Dimension(300, 40)); // Evita que se expanda más allá de este tamaño
        pnlNombreCliente.setMinimumSize(new Dimension(250, 30));

        JPanel pnlPersonal = new JPanel(new GridLayout(6, 1));
        pnlPersonal.setPreferredSize(new Dimension(300, 150));
        pnlPersonal.setMaximumSize(new Dimension(300, 300));
        pnlPersonal.setMinimumSize(new Dimension(200, 150));
        pnlPersonal.add(new JLabel("Datos personales", SwingConstants.CENTER));
        lblCurp = new JLabel("CURP: ");
        lblGenero = new JLabel("Género: ");
        lblTelefono = new JLabel("Teléfono: ");
        lblDireccion = new JLabel("Dirección: ");
        pnlPersonal.add(lblCurp);
        pnlPersonal.add(lblGenero);
        pnlPersonal.add(lblTelefono);
        pnlPersonal.add(lblDireccion);

        txtDireccion = new JTextArea();
        txtDireccion.setEditable(false);
        pnlPersonal.add(new JScrollPane(txtDireccion));

        JPanel pnlInfo = new JPanel(new GridLayout(6, 1));
        pnlInfo.setPreferredSize(new Dimension(300, 150));
        pnlInfo.setMaximumSize(new Dimension(300, 300));
        pnlInfo.setMinimumSize(new Dimension(200, 150));
        pnlInfo.add(new JLabel("Información de la cita", SwingConstants.CENTER));
        lblFecha = new JLabel("Fecha: ");
        lblHora = new JLabel("Hora: ");
        lblTipo = new JLabel("Tipo de consulta: ");
        lblAreaMotivo = new JLabel("Motivo");
        pnlInfo.add(lblFecha);
        pnlInfo.add(lblHora);
        pnlInfo.add(lblTipo);
        pnlInfo.add(lblAreaMotivo);

        txtAreaMotivo = new JTextArea();
        txtAreaMotivo.setEditable(false);
        pnlInfo.add(new JScrollPane(txtAreaMotivo));

        pnlCentro.add(encabezado, "North");
        pnlCentro.add(pnlNombreCliente, "Center");
        pnlCentro.add(tablas, "South");
        tablas.add(pnlPersonal);
        tablas.add(pnlInfo);

        // Añadimos acciones
        btnGuardar.addActionListener(this);
        btnCancelar.addActionListener(this);
        this.add(pnlPrincipal, "Center");

    }

    public void actualizarTabla() {
        // Borra los elementos del modelo
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        for (DatosCliente clienteNuevo : listaCliente) {
            Object a[] = new Object[5];
            a[0] = clienteNuevo.getCurp();
            a[1] = clienteNuevo.getNombres();
            a[2] = clienteNuevo.getApellidos();
            a[3] = clienteNuevo.getFecha();
            a[4] = clienteNuevo.getHora();
            modelo.addRow(a);

        }
        tablaClientes.setModel(modelo);
    }

    private void mostrarInformacionCliente(int rowIndex) {
        // Obtener el cliente seleccionado de la lista
        DatosCliente cliente = listaCliente.get(rowIndex);

        // Actualizar pnlNombreCliente con nombre y apellidos
        lblNombreCliente.setText(cliente.getNombres() + " " + cliente.getApellidos());

        // Actualizar pnlPersonal con los datos personales
        lblCurp.setText("CURP: " + cliente.getCurp());
        lblGenero.setText("Género: " + cliente.getGenero().toString());
        lblTelefono.setText("Teléfono: " + cliente.getTelefono());
        txtDireccion.setText(cliente.getDireccion());

        // Actualizar pnlInfo con la información de la cita
        lblFecha.setText("Fecha: " + cliente.getFecha().toString());
        lblHora.setText("Hora: " + cliente.getHora().toString());
        lblTipo.setText("Tipo de consulta: " + cliente.getTipo());
        txtAreaMotivo.setText(cliente.getMotivo());
    }

    public static void main(String[] args) {
        AgendaDoctor a = new AgendaDoctor();
    }
}

class DatosCliente {
    String nombres;
    String apellidos;
    String genero;
    String telefono;
    String direccion;
    String curp;
    String fecha;
    String hora;
    String tipo;
    String motivo;

    public DatosCliente() {

    }

    public DatosCliente(String nombres, String apellidos,
            String genero, String telefono, String direccion,
            String curp, String fecha, String hora, String tipo,
            String motivo) {

        this.nombres = nombres;
        this.apellidos = apellidos;
        this.genero = genero;
        this.telefono = telefono;
        this.direccion = direccion;
        this.curp = curp;
        this.fecha = fecha;
        this.hora = hora;
        this.tipo = tipo;
        this.motivo = motivo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return "nombres=" + nombres + ", apellidos=" + apellidos + ", genero=" + genero + ", telefono="
                + telefono + ", direccion=" + direccion + ", curp=" + curp + ", fecha=" + fecha + ", hora=" + hora
                + ", tipo=" + tipo + ", motivo=" + motivo;
    }

}
