package App_Doctor.Pantallas;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Datos_Doctor extends JPanel implements ActionListener {
    JLabel correo;
    JTextField most_Correo;
    JLabel num_Telefonico;
    JTextField number_Phone;
    JLabel direccion;
    JTextField direction;
    JLabel contrasenia;
    JTextField password;
    Button guardar;

    public Datos_Doctor(){
        init();
    }

    public void init(){
        correo = new JLabel("Correo electrónico");
        most_Correo = new JTextField();
        num_Telefonico = new JLabel("Numero telefonico");
        number_Phone = new JTextField();
        direccion = new JLabel("Dirreccion del domicilio");
        direction = new JTextField();
        contrasenia = new JLabel("Contraseña");
        password = new JTextField();
        guardar = new Button("Aceptar");
        this.setLayout(new GridLayout(5,2));
        this.add(correo);
        this.add(most_Correo);
        this.add(num_Telefonico);
        this.add(number_Phone);
        this.add(direccion);
        this.add(direction);
        this.add(contrasenia);
        this.add(password);
        this.add(guardar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
