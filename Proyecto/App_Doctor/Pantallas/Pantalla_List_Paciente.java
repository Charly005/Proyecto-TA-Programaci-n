package App_Doctor.Pantallas;
import javax.swing.JPanel;

import App_Doctor.Pantallas.SubPantallas.ContainerGen;

import java.awt.CardLayout;
import java.awt.GridLayout;

public class Pantalla_List_Paciente extends JPanel{
    CardLayout cambiar = new CardLayout();
    JPanel contenedor = new JPanel();

    public Pantalla_List_Paciente() {
        setLayout(new GridLayout(1,1));  
        contenedor.setLayout(cambiar);
        contenedor.add(new ContainerGen(this), "Lista Pacientes");
        add(contenedor);
    }
    public void cambPantalla(JPanel newPant, String name){
        contenedor.add(newPant, name);
        cambiar.show(contenedor, name);
    }
}