package App_Paciente;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatLightLaf;

import App_Paciente.Pantallas.ContainerGenBase;
import App_Paciente.Pantallas.OlvidoContraseña;
import Style.EstilosUI;

public class InicioSesion extends JPanel implements ActionListener {
    JPanel izquierdo;
    JPanel centro;
    JPanel derecho;
    JLabel img;
    JTextField usuario;
    JPasswordField contraseña;
    JLabel user;
    JLabel password;
    JButton accepted;
    JButton olvPassword;
    Base_App_Paciente parent;

    public InicioSesion(Base_App_Paciente parent) {
        this.parent = parent;
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());

            // DISEÑO DEL BOTÓN ACEPTA
            // UIManager.put("Button.background", new Color(76, 156, 207));// #rgb(76,
            // 156,207));
            // UIManager.put("Button.focus", new Color(0, 254, 136));// #rgb(0, 254, 136));
            // UIManager.put("Button.foreground", Color.BLACK);

        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        init();
    }

    public void init() {
        izquierdo = new JPanel();
        centro = new JPanel();
        derecho = new JPanel();
        img = new JLabel();
        usuario = new JTextField();
        contraseña = new JPasswordField();
        user = new JLabel(" Usuario");
        password = new JLabel("Contraseña");
        accepted = new JButton("Aceptar");
        EstilosUI.aplicarEstiloBoton(accepted);

        olvPassword = new JButton("Olvidé mi contraseña");
        olvPassword.setVisible(false);
        EstilosUI.aplicarEstiloBoton(olvPassword);

        GridBagConstraints gbc = new GridBagConstraints();

        this.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.weightx = 0.35;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(izquierdo, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.3;
        this.add(centro, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0.35;
        this.add(derecho, gbc);

        centro.setLayout(new GridBagLayout());
        gbc.insets = new Insets(5, 10, 10, 10);
        gbc.gridx = 0;
        gbc.weightx = 0.1;
        gbc.weighty = 0.32;
        JLabel login = new JLabel("LOGIN", SwingConstants.CENTER);
        login.setForeground(Color.BLACK);
        login.setFont(new Font("Barlow Black", Font.BOLD, 50));
        centro.add(login, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.01;
        centro.add(user, gbc);

        gbc.gridy = 2;
        gbc.weighty = 0.06;
        centro.add(usuario, gbc);

        gbc.gridy = 3;
        gbc.weighty = 0.01;
        centro.add(password, gbc);

        gbc.gridy = 4;
        gbc.weighty = 0.06;

        centro.add(contraseña, gbc);

        gbc.gridy = 5;
        gbc.weighty = 0.05;
        gbc.insets = new Insets(20, 30, 20, 30);
        centro.add(accepted, gbc);

        gbc.gridy = 6;
        gbc.weighty = 0.05;
        gbc.insets = new Insets(0, 30, 0, 30);
        centro.add(olvPassword, gbc);

        gbc.gridy = 7;
        gbc.weighty = 0.32;
        centro.add(new JLabel(), gbc);

        accepted.addActionListener(this);
        olvPassword.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel nuevoPanel = null;
        String usuario_Ingresado = usuario.getText().trim();
        String contraseña_ingresada = new String(contraseña.getPassword()).trim();
        if (e.getSource() == accepted) {
            if (usuario_Ingresado.equals("admin") && contraseña_ingresada.equals("123")) {
                nuevoPanel = new ContainerGenBase(parent);
                String name = "Base App Paciente";
                if (nuevoPanel != null) {
                    parent.cambPantalla(nuevoPanel, name);
                }
                usuario.setBackground(Color.white);
                contraseña.setBackground(Color.white);
            } else {
                olvPassword.setVisible(true);
                usuario.setBackground(Color.PINK);
                contraseña.setBackground(Color.PINK);

            }
        } else if (e.getSource() == olvPassword) {
            nuevoPanel = new OlvidoContraseña(parent);
            String name = "Olvido Contraseña";
            if (nuevoPanel != null) {
                parent.cambPantalla(nuevoPanel, name);
            }
        }
    }
}
