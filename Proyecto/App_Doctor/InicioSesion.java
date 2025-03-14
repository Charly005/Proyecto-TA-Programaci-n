package App_Doctor;

import java.awt.Color;
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

import App_Doctor.Pantallas.ContainerGenBase;
import App_Doctor.Pantallas.OlvidoContraseña;


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
    Base_App_Doctor parent;

    public InicioSesion(Base_App_Doctor parent){
        this.parent = parent;
        init();
    }

    public void init(){
        izquierdo = new JPanel();
        centro = new JPanel();
        derecho = new JPanel();
        img = new JLabel();
        usuario = new JTextField();
        contraseña = new JPasswordField();
        user = new JLabel("Ingrese su usuario");
        password = new JLabel("Ingrese su contraseña");
        accepted = new JButton("Aceptar");
        olvPassword = new JButton("Olvido su contraseña");
        olvPassword.setVisible(false);

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
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.32;
        centro.add(new JLabel(), gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.06;
        centro.add(user, gbc);

        gbc.gridy = 2;
        gbc.weighty = 0.06;
        centro.add(usuario, gbc);

        gbc.gridy = 3;
        gbc.weighty = 0.06;
        centro.add(password, gbc);

        gbc.gridy = 4;
        gbc.weighty = 0.06;
        centro.add(contraseña, gbc);

        gbc.gridy = 5;
        gbc.weighty = 0.06;
        centro.add(accepted, gbc);

        gbc.gridy = 6;
        gbc.weighty = 0.06;
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
        if(e.getSource() == accepted){
            if(usuario_Ingresado.equals("admin") && contraseña_ingresada.equals("123")){
                nuevoPanel = new ContainerGenBase(parent);
                String name = "Base App Paciente";
                if (nuevoPanel != null) {
                    parent.cambPantalla(nuevoPanel, name);
                }
                usuario.setBackground(Color.white);
                contraseña.setBackground(Color.white);
            } else{
                olvPassword.setVisible(true);
                usuario.setBackground(Color.red);
                contraseña.setBackground(Color.red);
                
            }
        } else if(e.getSource() == olvPassword){
            nuevoPanel = new OlvidoContraseña(parent);
            String name = "Olvido Contraseña";
            if (nuevoPanel != null) {
                parent.cambPantalla(nuevoPanel, name);
            }
        }
    }
}
