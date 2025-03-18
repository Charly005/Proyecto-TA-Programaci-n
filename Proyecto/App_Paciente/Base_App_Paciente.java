package App_Paciente;

import java.awt.CardLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

//import com.formdev.flatlaf.FlatLightLaf;

public class Base_App_Paciente extends JFrame {
    CardLayout cambiar = new CardLayout();
    JPanel contenedor = new JPanel();

    public Base_App_Paciente() {
        setLayout(new GridLayout(1, 1));
        contenedor.setLayout(cambiar);

        // Asegúrate de agregar la pantalla base
        contenedor.add(new InicioSesion(this), "Inicio Sesión");
        add(contenedor);
        // try {
        // UIManager.setLookAndFeel(new FlatLightLaf());
        // } catch (UnsupportedLookAndFeelException e) {
        // e.printStackTrace();
        // }

        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static void main(String[] args) {
        new Base_App_Paciente();
    }

    public void cambPantalla(JPanel newPant, String name) {
        contenedor.removeAll();
        contenedor.add(newPant, name);

        cambiar.show(contenedor, name);
        contenedor.revalidate();
        contenedor.repaint();
    }

}