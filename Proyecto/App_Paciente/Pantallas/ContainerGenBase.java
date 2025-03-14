package App_Paciente.Pantallas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import App_Paciente.Base_App_Paciente;
import App_Paciente.Pantallas.SubPantallas.Configuraciones;
import Style.Boton;
import Style.EstilosUI;

public class ContainerGenBase extends JPanel implements ActionListener {
    JPanel app_Bar;
    JPanel contenedor_Gen;
    JPanel panel_Lateral;
    JPanel panel_Central;
    JPanel sub_Panel_Lateral;
    JButton perfil;
    JButton hist_Medico;
    JButton medicamentos;
    JButton recomendaciones;
    JButton agenda;
    JButton chat;
    JLabel titulo;
    JPanel logo_container;
    JLabel label_Logo;
    ImageIcon logo;
    Base_App_Paciente parent;
    Boton configuracion;

    public ContainerGenBase(Base_App_Paciente parent) {
        this.parent = parent;
        init();
    }

    public void init() {
        // Inicializar los componentes
        app_Bar = new JPanel();
        contenedor_Gen = new JPanel();
        panel_Lateral = new JPanel();
        panel_Central = new JPanel();
        panel_Central.setOpaque(false);
        sub_Panel_Lateral = new JPanel();
        perfil = new JButton("Perfil paciente");
        hist_Medico = new JButton("Historial medico");
        medicamentos = new JButton("Medicamentos");
        recomendaciones = new JButton("Recomendaciones");
        agenda = new JButton("Agenda");
        chat = new JButton("Chat");
        titulo = new JLabel("CUIDAME");
        titulo.setForeground(Color.WHITE);
        configuracion = new Boton("⚙️");
        configuracion.getPreferredSize();
        logo = new ImageIcon("App_Paciente\\img\\image.png");
        Image enviar_escalado = logo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        logo_container = new JPanel(new BorderLayout());
        label_Logo = new JLabel(new ImageIcon(enviar_escalado));
        logo_container.add(label_Logo, BorderLayout.CENTER);

        // Aplicar estilos
        EstilosUI.panelApp_Bar(app_Bar);
        EstilosUI.panelApp_Bar(logo_container);
        EstilosUI.aplicarEstiloPanelPrincipal(contenedor_Gen);
        EstilosUI.aplicarEstiloPanelSecundario(panel_Lateral);
        EstilosUI.aplicarEstiloPanelSecundario(panel_Central);
        EstilosUI.aplicarEstiloPanelSecundario(sub_Panel_Lateral);
        EstilosUI.aplicarEstiloBotonMenu(perfil);
        EstilosUI.aplicarEstiloBotonMenu(hist_Medico);
        EstilosUI.aplicarEstiloBotonMenu(medicamentos);
        EstilosUI.aplicarEstiloBotonMenu(recomendaciones);
        EstilosUI.aplicarEstiloBotonMenu(agenda);
        EstilosUI.aplicarEstiloBotonMenu(chat);

        // Configurar el subpanel lateral
        sub_Panel_Lateral.setLayout(new GridLayout(1, 2));
        sub_Panel_Lateral.add(agenda);
        sub_Panel_Lateral.add(chat);

        // Configurar el panel lateral
        panel_Lateral.setLayout(new GridLayout(5, 1));
        panel_Lateral.add(perfil);
        panel_Lateral.add(hist_Medico);
        panel_Lateral.add(medicamentos);
        panel_Lateral.add(recomendaciones);
        panel_Lateral.add(sub_Panel_Lateral);

        // Configurar el panel central
        panel_Central.setLayout(new BorderLayout());
        panel_Central.add(new PerfilPaciente(), BorderLayout.CENTER);

        // Configurar el contenedor general
        contenedor_Gen.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        cont.gridx = 0;
        cont.gridy = 0;
        cont.weightx = 0.15; // El panel lateral ocupa el 15% del ancho
        cont.weighty = 1.0;
        cont.fill = GridBagConstraints.BOTH; // Se expande en ambas direcciones
        contenedor_Gen.add(panel_Lateral, cont);

        cont.gridx = 1;
        cont.weightx = 0.85; // El panel central ocupa el 85% del ancho
        cont.fill = GridBagConstraints.BOTH; // Se expande en ambas direcciones
        contenedor_Gen.add(panel_Central, cont);

        // Configurar el appBar
        app_Bar.setLayout(new GridBagLayout());
        GridBagConstraints app = new GridBagConstraints();
        app.gridx = 0;
        app.gridy = 0;
        app.weightx = 0.15; // Logo ocupa el 15% del ancho
        app.fill = GridBagConstraints.BOTH;
        app_Bar.add(logo_container, app);

        app.gridx = 1;
        app.weightx = 0.70; // Título ocupa el 70% del ancho
        app.fill = GridBagConstraints.BOTH;
        app_Bar.add(titulo, app);

        app.gridx = 2;
        app.weightx = 0.15; // Botón de configuración ocupa el 15% del ancho
        app.fill = GridBagConstraints.BOTH;
        app_Bar.add(configuracion, app);

        // Configurar el layout principal
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // appBar ocupa la parte superior
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.05; // Altura del appBar (5% de la ventana)
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(app_Bar, gbc);

        // contenedor_Gen ocupa el resto del espacio
        gbc.gridy = 1;
        gbc.weighty = 0.95; // Altura del contenedor general (95% de la ventana)
        add(contenedor_Gen, gbc);

        // Listener para ajustar tamaños dinámicamente
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                ajustarTamanos();
            }
        });

        // Asignar listeners a los botones
        chat.addActionListener(this);
        hist_Medico.addActionListener(this);
        medicamentos.addActionListener(this);
        configuracion.addActionListener(this);
        perfil.addActionListener(this);
        recomendaciones.addActionListener(this);
        agenda.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel nuevoPanel = null;

        if (e.getSource() == chat) {
            panel_Central.removeAll();
            nuevoPanel = new Chat_Cliente();
        } else if (e.getSource() == hist_Medico) {
            panel_Central.removeAll();
            nuevoPanel = new Historial_Medico();
        } else if (e.getSource() == medicamentos) {
            panel_Central.removeAll();
            nuevoPanel = new Medicamentos();
        } else if (e.getSource() == perfil) {
            panel_Central.removeAll();
            nuevoPanel = new PerfilPaciente();
        } else if (e.getSource() == recomendaciones) {
            panel_Central.removeAll();
            nuevoPanel = new Recomendaciones();
        } else if (e.getSource() == agenda) {
            panel_Central.removeAll();
            nuevoPanel = new CalendarApp();
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
        // Ajustar el tamaño del panel lateral dinámicamente
        int anchoTotal = getWidth();
        int anchoLateral = (int) (anchoTotal * 0.15); // 15% del ancho total
        panel_Lateral.setPreferredSize(new Dimension(anchoLateral, getHeight()));
        revalidate();
    }
}