package App_Doctor.Pantallas;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

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




class ContainerGen extends JPanel implements ActionListener {
    JButton accepted;
    JTextField search;
    JPanel container;
    JPopupMenu menu;
    JScrollPane scroll;
    JMenuItem datos_Personales;
    JMenuItem historial_medico;
    JMenuItem medicamentos_Activos;
    String pacienteSeleccionado;
    Pantalla_List_Paciente parent;

    private final String[] pacientes = {
            "Alejandro Gómez", "Beatriz Sánchez", "Carlos Ramírez", "Diana López", "Eduardo Torres",
            "Fernanda Ruiz", "Gabriel Herrera", "Hilda Mendoza", "Iván Castro", "Julia Ortega",
            "Kevin Navarro", "Laura Paredes", "Mario Estrada", "Nancy Salazar", "Oscar Villarreal",
            "Paola Chávez", "Quintín Fuentes", "Rocío Guzmán", "Sergio Ávila", "Teresa Duarte",
            "Ulises Peña", "Valeria Montes", "Wilfredo Cárdenas", "Ximena Farías", "Yahir Domínguez",
            "Zaira Esquivel", "Antonio Beltrán", "Brenda Núñez", "César Aguirre", "Daniela Flores"
    };

    public ContainerGen(Pantalla_List_Paciente parent) {
        this.parent = parent;
        init();
    }

    private void init() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Panel de búsqueda y botón
        JPanel options = new JPanel(new GridBagLayout());
        search = new JTextField();
        accepted = new JButton("Buscar");
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.75;
        gbc.fill = GridBagConstraints.BOTH;
        options.add(search, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.25;
        options.add(accepted, gbc);

        // Contenedor de botones con scroll
        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        scroll = new JScrollPane(container);
        scroll.setPreferredSize(new Dimension(300, 400));
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        // Menú emergente
        menu = new JPopupMenu();
        datos_Personales = new JMenuItem("Datos Personales");
        historial_medico = new JMenuItem("Historial Médico");
        medicamentos_Activos = new JMenuItem("Medicamentos Activos");

        menu.add(datos_Personales);
        menu.add(historial_medico);
        menu.add(medicamentos_Activos);

        datos_Personales.addActionListener(this);
        historial_medico.addActionListener(this);
        medicamentos_Activos.addActionListener(this);

        // Añadir botones de pacientes
        for (String paciente : pacientes) {
            JButton btnPaciente = new JButton(paciente);
            
            btnPaciente.setMaximumSize(new Dimension(Integer.MAX_VALUE,40));
            btnPaciente.setPreferredSize(new Dimension(container.getWidth(), 40));
            btnPaciente.setAlignmentX(Component.LEFT_ALIGNMENT);
            btnPaciente.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnPaciente.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    pacienteSeleccionado = paciente;
                    menu.show(btnPaciente, e.getX(), e.getY());
                }
            });
            container.add(btnPaciente);
            container.add(Box.createRigidArea(new Dimension(10,20)));
        }

        // Añadir componentes al panel principal
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.05;
        gbc.weightx = 1.0;
        add(options, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.95; 
        gbc.fill = GridBagConstraints.BOTH;
        add(scroll, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (pacienteSeleccionado != null) {
            JPanel newPant = null;
            String name = "";
            if (e.getSource() == datos_Personales) {
                newPant = new Datos_Personales(parent, pacienteSeleccionado);
                name = "Datos Personales";
            } else if (e.getSource() == historial_medico) {
                newPant = new Hist_Medico(parent, pacienteSeleccionado);
                name = "Historial Medico";
            } else if (e.getSource() == medicamentos_Activos) {
                newPant = new Medicamentos_Activos(parent, pacienteSeleccionado);
                name = "Medicamentos Activos";
            }
            if(newPant != null){
                parent.cambPantalla(newPant, name);
            }
        }
    }
}






class Datos_Personales extends JPanel implements ActionListener{

    JPanel app_Bar;
    JPanel container;
    JLabel correo;
    JTextField most_Correo;
    JLabel nomb_Paciente;
    JLabel num_Telefonico;
    JTextField number_Phone;
    JLabel direccion;
    JTextField direction;
    JLabel contrasenia;
    JTextField password;
    JButton guardar;
    JButton volver;
    Pantalla_List_Paciente parent;

    public Datos_Personales(Pantalla_List_Paciente parent,String nomb){
        this.parent = parent;
        init(nomb);
    }

    public void init(String nomb){
        app_Bar = new JPanel();
        container = new JPanel();
        correo = new JLabel("Datos Personales");
        most_Correo = new JTextField();
        nomb_Paciente = new JLabel(nomb);
        num_Telefonico = new JLabel("Numero telefonico");
        number_Phone = new JTextField();
        direccion = new JLabel("Dirreccion del domicilio");
        direction = new JTextField();
        contrasenia = new JLabel("Contraseña");
        password = new JTextField();
        guardar = new JButton("Aceptar");
        volver = new JButton("Volver");

        volver.addActionListener(this);

        container.setLayout(new GridLayout(5,2));
        container.add(correo);
        container.add(most_Correo);
        container.add(num_Telefonico);
        container.add(number_Phone);
        container.add(direccion);
        container.add(direction);
        container.add(contrasenia);
        container.add(password);
        container.add(guardar);

        app_Bar.setLayout(new GridBagLayout());
        GridBagConstraints app = new GridBagConstraints();
        app.gridx = 0;
        app.gridy = 0;
        app.weightx = 0.25;
        app.weighty = 1;
        app.fill = GridBagConstraints.BOTH;
        app_Bar.add(volver, app);

        app.gridx = 1;
        app.weightx = 0.75;
        app_Bar.add(nomb_Paciente, app);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.05;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(app_Bar, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.95;
        this.add(container, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == volver){
            JPanel newPanel = new ContainerGen(parent);  // Usamos el parent ya configurado
            ((Pantalla_List_Paciente) parent).cambPantalla(newPanel, "Lista Pacientes");
        }
    }
}







class Hist_Medico extends JPanel implements ActionListener{

    JPanel app_Bar;
    JPanel container;
    JLabel correo;
    JTextField most_Correo;
    JLabel nomb_Paciente;
    JLabel num_Telefonico;
    JTextField number_Phone;
    JLabel direccion;
    JTextField direction;
    JLabel contrasenia;
    JTextField password;
    JButton guardar;
    JButton volver;
    Pantalla_List_Paciente parent;

    public Hist_Medico(Pantalla_List_Paciente parent, String nomb){
        this.parent = parent;
        init(nomb);
    }

    public void init(String nomb){
        app_Bar = new JPanel();
        container = new JPanel();
        correo = new JLabel("Historial Medico");
        most_Correo = new JTextField();
        nomb_Paciente = new JLabel(nomb);
        num_Telefonico = new JLabel("Numero telefonico");
        number_Phone = new JTextField();
        direccion = new JLabel("Dirreccion del domicilio");
        direction = new JTextField();
        contrasenia = new JLabel("Contraseña");
        password = new JTextField();
        guardar = new JButton("Aceptar");
        volver = new JButton("Volver");

        volver.addActionListener(this);

        container.setLayout(new GridLayout(5,2));
        container.add(correo);
        container.add(most_Correo);
        container.add(num_Telefonico);
        container.add(number_Phone);
        container.add(direccion);
        container.add(direction);
        container.add(contrasenia);
        container.add(password);
        container.add(guardar);

        app_Bar.setLayout(new GridBagLayout());
        GridBagConstraints app = new GridBagConstraints();
        app.gridx = 0;
        app.gridy = 0;
        app.weightx = 0.25;
        app.weighty = 1;
        app.fill = GridBagConstraints.BOTH;
        app_Bar.add(volver, app);

        app.gridx = 1;
        app.weightx = 0.75;
        app_Bar.add(nomb_Paciente, app);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.05;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(app_Bar, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.95;
        this.add(container, gbc);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == volver){
            JPanel newPanel = new ContainerGen(parent);  // Usamos el parent ya configurado
            ((Pantalla_List_Paciente) parent).cambPantalla(newPanel, "Lista Pacientes");
        }
    }
}








class Medicamentos_Activos extends JPanel implements ActionListener{

    JPanel app_Bar;
    JPanel container;
    JLabel correo;
    JTextField most_Correo;
    JLabel nomb_Paciente;
    JLabel num_Telefonico;
    JTextField number_Phone;
    JLabel direccion;
    JTextField direction;
    JLabel contrasenia;
    JTextField password;
    JButton guardar;
    JButton volver;
    Pantalla_List_Paciente parent;

    public Medicamentos_Activos(Pantalla_List_Paciente parent, String nomb){
        this.parent = parent;
        init(nomb);
    }

    public void init(String nomb){
        app_Bar = new JPanel();
        container = new JPanel();
        correo = new JLabel("Medicamentos Activos");
        most_Correo = new JTextField();
        nomb_Paciente = new JLabel(nomb);
        num_Telefonico = new JLabel("Numero telefonico");
        number_Phone = new JTextField();
        direccion = new JLabel("Dirreccion del domicilio");
        direction = new JTextField();
        contrasenia = new JLabel("Contraseña");
        password = new JTextField();
        guardar = new JButton("Aceptar");
        volver = new JButton("Volver");

        volver.addActionListener(this);

        container.setLayout(new GridLayout(5,2));
        container.add(correo);
        container.add(most_Correo);
        container.add(num_Telefonico);
        container.add(number_Phone);
        container.add(direccion);
        container.add(direction);
        container.add(contrasenia);
        container.add(password);
        container.add(guardar);

        app_Bar.setLayout(new GridBagLayout());
        GridBagConstraints app = new GridBagConstraints();
        app.gridx = 0;
        app.gridy = 0;
        app.weightx = 0.25;
        app.weighty = 1;
        app.fill = GridBagConstraints.BOTH;
        app_Bar.add(volver, app);

        app.gridx = 1;
        app.weightx = 0.75;
        app_Bar.add(nomb_Paciente, app);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.05;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(app_Bar, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.95;
        this.add(container, gbc);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == volver){
            JPanel newPanel = new ContainerGen(parent);  // Usamos el parent ya configurado
            ((Pantalla_List_Paciente) parent).cambPantalla(newPanel, "Lista Pacientes");
        }
    }
}
