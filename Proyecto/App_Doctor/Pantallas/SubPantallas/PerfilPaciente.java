package App_Doctor.Pantallas.SubPantallas;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PerfilPaciente extends JPanel{
    String nombre = "Juan Pérez";
    double peso = 70.5; 
    double estatura = 1.75; 
    String grupoSanguineo = "O+";
    String[] alergias = {"Polen", "Mariscos"};
    String[] padecimientosCronicos = {"Asma", "Hipertensión"};
    String[][] medicamentosCronicos = {
        {"Salbutamol", "Budesonida"}, 
        {"Losartán", "Amlodipino"}    
    };

    JLabel lblNombre;
    JLabel fotoPerfil;
    JLabel lblPeso;
    JLabel lblEstatura;
    JLabel lblGrupoSanguineo;
    JLabel lblAlergias;
    JLabel lblPadecimientos;
    JLabel lblMedicamentos;
    JPanel container;
    JScrollPane scroll;

    public PerfilPaciente() {
        init();
    }

    public void init() {
        container = new JPanel();
        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        ImageIcon icono = new ImageIcon("App_Paciente\\img\\Foto_Perfil.jpeg"); 
        Image imagen = icono.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH); 
        fotoPerfil = new JLabel(new ImageIcon(imagen));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);
        container.add(fotoPerfil, gbc);

        lblNombre = new JLabel("Nombre: " + nombre);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        container.add(lblNombre, gbc);

        // Peso
        lblPeso = new JLabel("Peso: " + peso + " kg");
        gbc.gridy = 2;
        container.add(lblPeso, gbc);

        // Estatura
        lblEstatura = new JLabel("Estatura: " + estatura + " m");
        gbc.gridy = 3;
        container.add(lblEstatura, gbc);

        // Grupo sanguíneo
        lblGrupoSanguineo = new JLabel("Grupo Sanguíneo: " + grupoSanguineo);
        gbc.gridy = 4;
        container.add(lblGrupoSanguineo, gbc);

        // Alergias
        lblAlergias = new JLabel("Alergias: " + String.join(", ", alergias));
        gbc.gridy = 5;
        container.add(lblAlergias, gbc);

        // Padecimientos crónicos
        lblPadecimientos = new JLabel("Padecimientos Crónicos: " + String.join(", ", padecimientosCronicos));
        gbc.gridy = 6;
        container.add(lblPadecimientos, gbc);

        // Medicamentos asociados a padecimientos crónicos
        if (padecimientosCronicos.length > 0) {
            StringBuilder medicamentosText = new StringBuilder("Medicamentos: ");
            for (int i = 0; i < padecimientosCronicos.length; i++) {
                medicamentosText.append(padecimientosCronicos[i]).append(" → ")
                               .append(String.join(", ", medicamentosCronicos[i])).append("; ");
            }
            lblMedicamentos = new JLabel(medicamentosText.toString());
            gbc.gridy = 7;
            container.add(lblMedicamentos, gbc);
        }

        // ScrollPane para el contenedor
        scroll = new JScrollPane(container);
        scroll.setPreferredSize(new Dimension(750, 550));
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        add(scroll);
    }
}