package App_Doctor.Pantallas.SubPantallas;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import App_Doctor.Base_App_Doctor;
import App_Doctor.Pantallas.ContainerGenBase;
import App_Doctor.Pantallas.Datos_Doctor;
import Style.EstilosUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Configuraciones extends JPanel implements ActionListener {
    JPanel app_Bar;
    JPanel contenedor_Gen;
    JPanel panel_Lateral;
    JPanel panel_Central;
    JButton regresar;
    JButton dat_Perfil;
    Base_App_Doctor parent;
    JLabel titulo;
    JPanel logo_container;
    JLabel label_Logo;
    ImageIcon logo;

    public Configuraciones(Base_App_Doctor parent) {
        this.parent = parent;
        init();
    }

    public void init() {
        // Inicializar los componentes
        app_Bar = new JPanel();
        contenedor_Gen = new JPanel();
        panel_Lateral = new JPanel();
        panel_Central = new JPanel();
        regresar = new JButton("⬅Regresar");
        dat_Perfil = new JButton("Datos del Perfil");
        titulo = new JLabel("Cuidame");
        logo = new ImageIcon("App_Paciente\\img\\image.png");
        Image enviar_escalado = logo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        logo_container = new JPanel(new BorderLayout());
        label_Logo = new JLabel(new ImageIcon(enviar_escalado));
        logo_container.add(label_Logo, BorderLayout.CENTER);

        EstilosUI.aplicarEstiloPanelPrincipal(contenedor_Gen);
        EstilosUI.aplicarEstiloPanelPrincipal(panel_Lateral);
        EstilosUI.aplicarEstiloPanelPrincipal(panel_Central);
        EstilosUI.aplicarEstiloBoton(regresar);
        EstilosUI.aplicarEstiloBoton(dat_Perfil);

        // incluir el subpanel y los componentes del panel lateral
        panel_Lateral.setLayout(new GridLayout(3, 1));
        panel_Lateral.add(regresar);
        panel_Lateral.add(dat_Perfil);

        // Panel Central
        panel_Central.setLayout(new BorderLayout());
        panel_Central.add(new Datos_Doctor(), BorderLayout.CENTER);

        // Incluir dentro del panel del contenedor general los dos paneles lateral y
        // central
        contenedor_Gen.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        cont.gridx = 0;
        cont.gridy = 0;
        cont.weightx = 0.15;
        cont.weighty = 1.0;
        cont.fill = GridBagConstraints.BOTH;
        contenedor_Gen.add(panel_Lateral, cont);

        cont.gridx = 1;
        cont.weightx = 0.85;
        contenedor_Gen.add(panel_Central, cont);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                ajustarTamanos();
            }
        });
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

        // acciones de los botones
        regresar.addActionListener(this);
        dat_Perfil.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == regresar) {
            JPanel nuevoPanel = new ContainerGenBase(parent) ;
            String name = "Base App Paciente";
            if (nuevoPanel != null) {
                parent.cambPantalla(nuevoPanel, name);
            }
        } else if(e.getSource() == dat_Perfil){
            panel_Central.removeAll();
            JPanel nuevoPanel = new Datos_Doctor();
            if (nuevoPanel != null) {
                nuevoPanel.setPreferredSize(panel_Central.getSize());
                panel_Central.add(nuevoPanel, BorderLayout.CENTER);
                panel_Central.revalidate();
                panel_Central.repaint();
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
