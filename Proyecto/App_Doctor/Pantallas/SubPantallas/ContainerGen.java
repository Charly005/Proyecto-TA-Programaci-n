package App_Doctor.Pantallas.SubPantallas;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import App_Doctor.Pantallas.Pantalla_List_Paciente;

public class ContainerGen extends JPanel implements ActionListener {
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

    /**
     * 
     */
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
                newPant = new PerfilPaciente(parent);
                name = "Datos Personales";
            } else if (e.getSource() == historial_medico) {
                newPant = new Historial_Medico(parent);
                name = "Historial Medico";
            } else if (e.getSource() == medicamentos_Activos) {
                newPant = new Medicamentos(parent);
                name = "Medicamentos Activos";
            }
            if(newPant != null){
                parent.cambPantalla(newPant, name);
            }
        }
    }
}