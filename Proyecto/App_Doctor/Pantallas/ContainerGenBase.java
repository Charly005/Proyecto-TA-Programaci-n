package App_Doctor.Pantallas;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import App_Doctor.Base_App_Doctor;
import App_Doctor.Pantallas.SubPantallas.Configuraciones;
import Style.Boton;
import Style.EstilosUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ContainerGenBase extends JPanel implements ActionListener {
    JPanel app_Bar;
    JPanel contenedor_Gen;
    JPanel panel_Lateral;
    JPanel panel_Central;
    JButton lista;
    JButton registro;
    JButton agenda;
    JButton chat;
    JLabel titulo;
    JPanel logo_container;
    JLabel label_Logo;
    ImageIcon logo;
    Base_App_Doctor parent;
    Boton configuracion;

    public ContainerGenBase(Base_App_Doctor parent) {
        this.parent = parent;
        init();
    }

    public void init() {
        // Inicializar los componentes
        app_Bar = new JPanel();
        contenedor_Gen = new JPanel();
        panel_Lateral = new JPanel();
        panel_Central = new JPanel();
        lista = new JButton("Listas de Paciente");
        registro = new JButton("Registro de Pacientes");
        agenda = new JButton("Agenda");
        chat = new JButton("Chat");
        titulo = new JLabel("Cuidame");
        configuracion = new Boton("⚙️");
        configuracion.getPreferredSize();
        logo = new ImageIcon("App_Paciente\\img\\image.png");
        Image enviar_escalado = logo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        logo_container = new JPanel(new BorderLayout());
        label_Logo = new JLabel(new ImageIcon(enviar_escalado));
        logo_container.add(label_Logo, BorderLayout.CENTER);

       // EstilosUI.aplicarEstiloPanel(app_Bar);
       // EstilosUI.aplicarEstiloPanel(contenedor_Gen); Aplicar estilos actualizados
       // EstilosUI.aplicarEstiloPanel(panel_Lateral);
       // EstilosUI.aplicarEstiloPanel(panel_Central);
        EstilosUI.aplicarEstiloBoton(lista);
        EstilosUI.aplicarEstiloBoton(registro);
        EstilosUI.aplicarEstiloBoton(agenda);
        EstilosUI.aplicarEstiloBoton(chat);

        // incluir el subpanel y los componentes del panel lateral
        panel_Lateral.setLayout(new GridBagLayout());
        GridBagConstraints panel = new GridBagConstraints();
        panel.gridx = 0;
        panel.gridy = 0;
        panel.weighty = 1;
        panel.weightx = 0.25;
        panel.fill = GridBagConstraints.BOTH;
        panel_Lateral.add(lista, panel);

        panel.gridx = 1;
        panel.weightx = 0.25;
        panel_Lateral.add(registro, panel);

        panel.gridx = 2;
        panel.weightx = 0.25;
        panel_Lateral.add(agenda, panel);

        panel.gridx = 3;
        panel.weightx = 0.25;
        panel_Lateral.add(chat, panel);

        // Panel Central
        panel_Central.setLayout(new BorderLayout());
        panel_Central.add(new Pantalla_List_Paciente(), BorderLayout.CENTER);

        // Incluir dentro del panel del contenedor general los dos paneles lateral y
        // central
        contenedor_Gen.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        cont.gridx = 0;
        cont.gridy = 0;
        cont.weighty = 0.15;
        cont.weightx = 1.0;
        cont.fill = GridBagConstraints.BOTH;
        contenedor_Gen.add(panel_Lateral, cont);

        cont.gridy = 1;
        cont.weighty = 0.85;
        contenedor_Gen.add(panel_Central, cont);

        // AppBar
        app_Bar.setLayout(new GridBagLayout());
        GridBagConstraints app = new GridBagConstraints();
        app.gridx = 0;
        app.gridy = 0;
        app.weightx = 0.15;
        app.fill = GridBagConstraints.BOTH;
        app_Bar.add(logo_container, app);

        app.gridx = 1;
        app.weightx = 0.8;
        app_Bar.add(titulo, app);

        app.gridx = 2;
        app.weightx = 0.05;
        app_Bar.add(configuracion, app);

        // meter todo dentro del gridLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // app_Bar ocupa
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.01;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(app_Bar, gbc);

        // contenedor_Gen 
        gbc.gridy = 1;
        gbc.weighty = 0.99; 
        add(contenedor_Gen, gbc);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                ajustarTamanos();
            }
        });

        // acciones de los botones
        chat.addActionListener(this);
        registro.addActionListener(this);
        lista.addActionListener(this);
        configuracion.addActionListener(this);
        agenda.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel nuevoPanel = null;
        
        if (e.getSource() == chat) {
            panel_Central.removeAll();
            nuevoPanel = new Chat_Doctor();
        } else if (e.getSource() == lista) { 
            panel_Central.removeAll();
            nuevoPanel = new Pantalla_List_Paciente();
        } else if (e.getSource() == registro) { 
            panel_Central.removeAll();
            nuevoPanel = new RegistroPaciente();
        }
        else if (e.getSource() == agenda) {
            panel_Central.removeAll();
            nuevoPanel = new AgendaDoctor();
        }
        if (nuevoPanel != null) {
            nuevoPanel.setPreferredSize(panel_Central.getSize());
            panel_Central.add(nuevoPanel, BorderLayout.CENTER);
            panel_Central.revalidate();
            panel_Central.repaint();
        }
        if (e.getSource() == configuracion) {
            nuevoPanel = new Configuraciones(parent);
            String name = "Configuraciones";
            if (nuevoPanel != null) {
                parent.cambPantalla(nuevoPanel, name);
            }
        }
    }

    private void ajustarTamanos() {
        int anchoTotal = getWidth();
        int anchoLateral = (int) (anchoTotal * 0.15);
        panel_Lateral.setPreferredSize(new Dimension(anchoLateral, getHeight()));
        revalidate();
    }
}
