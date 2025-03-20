import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import App_Doctor.Base_App_Doctor;
import App_Paciente.Base_App_Paciente;

public class CuidameApp extends JFrame implements ActionListener {

    Base_App_Paciente main;
    Base_App_Doctor main2;

    JButton botonDoctor;
    JButton botonPaciente;

    public CuidameApp() {

        setTitle("Aplicación de Salud");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // panel principal con fondo gris
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(Color.GRAY);
        panelPrincipal.setLayout(new BorderLayout());

        // panel secundario
        JPanel panelSecundario = new JPanel();
        panelSecundario.setBackground(Color.WHITE);
        panelSecundario.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panelSecundario.setLayout(new GridBagLayout());

        // botones
        botonDoctor = new JButton("DOCTOR");
        botonPaciente = new JButton("PACIENTE");
        botonDoctor.setBackground(Color.WHITE);
        botonPaciente.setBackground(Color.WHITE);
        // botonDoctor.setSize(new Dimension(100,40));

        // restricciones para centrar los botones
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Agregar los botones al panel secundario
        panelSecundario.add(botonDoctor, gbc);
        gbc.gridy = 1;
        panelSecundario.add(botonPaciente, gbc);

        panelPrincipal.add(panelSecundario, BorderLayout.CENTER);

        add(panelPrincipal);

        setVisible(true);
    }

    public static void main(String[] args) {
        CuidameApp a = new CuidameApp();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonPaciente) {
            new Base_App_Paciente();
        }
        if (e.getSource() == botonDoctor) {
            new Base_App_Doctor();
        }
    }

}
