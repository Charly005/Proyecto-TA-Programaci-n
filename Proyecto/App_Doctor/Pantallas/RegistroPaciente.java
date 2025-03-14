package App_Doctor.Pantallas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegistroPaciente extends JPanel implements ActionListener {
    // Campos del formulario
    JTextField txtNombreCompleto;
    JTextField txtFechaNacimiento;
    JComboBox<String> cmbGenero;
    JTextField txtTelefono;
    JTextField txtCorreoElectronico;
    JTextField txtDireccion;
    JTextField txtPeso;
    JTextField txtEstatura;
    JComboBox<String> cmbGrupoSanguineo;
    JTextField txtAlergias;
    JTextField txtPadecimientosCronicos;
    JLabel lblCodigoAcceso;
    JButton btnRegistrar;

    public RegistroPaciente() {
        init();
    }

    public void init() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Nombre completo:"), gbc);

        gbc.gridx = 1;
        txtNombreCompleto = new JTextField(20);
        add(txtNombreCompleto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Fecha de nacimiento:"), gbc);

        gbc.gridx = 1;
        txtFechaNacimiento = new JTextField(20);
        add(txtFechaNacimiento, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Género:"), gbc);

        gbc.gridx = 1;
        cmbGenero = new JComboBox<>(new String[]{"Masculino", "Femenino", "Otro"});
        add(cmbGenero, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Teléfono:"), gbc);

        gbc.gridx = 1;
        txtTelefono = new JTextField(20);
        add(txtTelefono, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Correo electrónico:"), gbc);

        gbc.gridx = 1;
        txtCorreoElectronico = new JTextField(20);
        add(txtCorreoElectronico, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Dirección:"), gbc);

        gbc.gridx = 1;
        txtDireccion = new JTextField(20);
        add(txtDireccion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(new JLabel("Peso (kg):"), gbc);

        gbc.gridx = 1;
        txtPeso = new JTextField(20);
        add(txtPeso, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        add(new JLabel("Estatura (m):"), gbc);

        gbc.gridx = 1;
        txtEstatura = new JTextField(20);
        add(txtEstatura, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        add(new JLabel("Grupo sanguíneo:"), gbc);

        gbc.gridx = 1;
        cmbGrupoSanguineo = new JComboBox<>(new String[]{"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"});
        add(cmbGrupoSanguineo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        add(new JLabel("Alergias:"), gbc);

        gbc.gridx = 1;
        txtAlergias = new JTextField(20);
        add(txtAlergias, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        add(new JLabel("Padecimientos crónicos:"), gbc);

        gbc.gridx = 1;
        txtPadecimientosCronicos = new JTextField(20);
        add(txtPadecimientosCronicos, gbc);

        gbc.gridx = 0;
        gbc.gridy = 11;
        add(new JLabel("Código de acceso:"), gbc);

        gbc.gridx = 1;
        lblCodigoAcceso = new JLabel(generarCodigoAcceso());
        add(lblCodigoAcceso, gbc);

        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnRegistrar = new JButton("Registrar Paciente");
        btnRegistrar.addActionListener(this);
        add(btnRegistrar, gbc);
    }

    private String generarCodigoAcceso() {
        Random rand = new Random();
        int codigo = rand.nextInt(9000) + 1000;
        return String.valueOf(codigo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegistrar) {
            System.out.println("Se registro");
        }
    }
}