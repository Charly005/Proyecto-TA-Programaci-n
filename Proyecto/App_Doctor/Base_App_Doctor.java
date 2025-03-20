package App_Doctor;

import java.awt.CardLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Base_App_Doctor extends JFrame {
    CardLayout cambiar = new CardLayout();
    JPanel contenedor = new JPanel();

    public Base_App_Doctor() {
        setLayout(new GridLayout(1, 1));
        contenedor.setLayout(cambiar);

        contenedor.add(new InicioSesion(this), "Inicio Sesión");
        add(contenedor);

        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // public static void main(String[] args) {
    // new Base_App_Doctor();
    // }

    public void cambPantalla(JPanel newPant, String name) {
        contenedor.removeAll();
        contenedor.add(newPant, name);

        cambiar.show(contenedor, name);
        contenedor.revalidate();
        contenedor.repaint();
    }
}
