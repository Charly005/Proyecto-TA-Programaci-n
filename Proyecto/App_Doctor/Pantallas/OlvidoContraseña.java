package App_Doctor.Pantallas;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import App_Doctor.Base_App_Doctor;
import App_Doctor.InicioSesion;

public class OlvidoContraseña extends JPanel implements ActionListener {
    JPanel izquierdo;
    JPanel centro; 
    JPanel derecho;
    JLabel img;
    JTextField correo;
    JTextField usuario;
    JLabel user;
    JLabel cambioContraseña;
    JLabel nomb_user;
    JButton accepted;
    Base_App_Doctor parent;


    public OlvidoContraseña(Base_App_Doctor parent){
        this.parent = parent;
        init();
    }

    public void init(){
        izquierdo = new JPanel();
        centro = new JPanel();
        derecho = new JPanel();
        img = new JLabel();
        correo = new JTextField();
        usuario = new JTextField();
        user = new JLabel("Ingrese su correo electronico");
        nomb_user = new JLabel("Su usuario");
        accepted = new JButton("Aceptar");
        cambioContraseña = new JLabel("Se le envío un correo con los pasos");
        cambioContraseña.setVisible(false);

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
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.32;
        centro.add(new JLabel(), gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.06;
        centro.add(user, gbc);

        gbc.gridy = 2;
        gbc.weighty = 0.06;
        centro.add(correo, gbc);

        gbc.gridy = 3;
        gbc.weighty = 0.06;
        centro.add(nomb_user, gbc);

        gbc.gridy = 4;
        gbc.weighty = 0.06;
        centro.add(usuario, gbc);

        gbc.gridy = 5;
        gbc.weighty = 0.06;
        centro.add(accepted, gbc);

        gbc.gridy = 6;
        gbc.weighty = 0.06;
        centro.add(cambioContraseña, gbc);

        gbc.gridy = 7;
        gbc.weighty = 0.32;
        centro.add(new JLabel(), gbc);

        accepted.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == accepted){
            cambioContraseña.setVisible(true);
            Timer timer = new Timer(3000, evt -> {
                JPanel nuevoPanel = new InicioSesion(parent);
                String name = "Inicio Sesión";
                if (nuevoPanel != null) {
                    parent.cambPantalla(nuevoPanel, name);
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }
}