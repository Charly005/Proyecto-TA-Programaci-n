package App_Paciente.Pantallas;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import App_Paciente.Pantallas.SubPantallas.MedicamentoInfo;
import Style.EstilosUI;

public class Medicamentos extends JPanel implements ActionListener {
    String[] nombres = { "Paracetamol", "Amoxicilina", "Ibuprofeno", "Loratadina", "Metformina", "Omeprazol",
            "Losartán", "Atorvastatina" };
    String[] dosis = { "500 mg cada 8 horas", "875 mg cada 12 horas", "400 mg cada 6 horas", "10 mg una vez al día",
            "850 mg cada 12 horas", "20 mg en ayunas", "50 mg cada 24 horas", "10 mg antes de dormir" };
    String[] fechaIni = { "10/03/2025", "05/03/2025", "08/03/2025", "01/03/2025", "07/03/2025", "03/03/2025",
            "02/03/2025", "04/03/2025" };
    String[] fechaFin = { "17/03/2025", "12/03/2025", "15/03/2025", "10/03/2025", "07/06/2025", "17/03/2025",
            "02/06/2025", "04/09/2025" };
    JPanel container;
    JScrollPane scroll;

    public Medicamentos() {
        init();
        setSize(800, 800);
        setVisible(true);
    }

    public void init() {
        container = new JPanel();
        scroll = new JScrollPane(container);
        scroll.setOpaque(false);
        scroll.setBorder(BorderFactory.createEmptyBorder()); // Elimina el borde del JScrollPane
        GridBagConstraints gbc = new GridBagConstraints();

        // Configuración del contenedor principal
        container.setLayout(new GridLayout(nombres.length, 1, 0, 5)); // Espacio vertical entre botones
        container.setOpaque(false);

        for (int i = 0; i < nombres.length; i++) {
            // Crear un botón personalizado
            JButton botonMedicamento = new JButton();
            botonMedicamento.setLayout(new GridBagLayout());
            botonMedicamento.setBackground(Color.white);
            botonMedicamento.setBorderPainted(false);
            botonMedicamento.setFocusPainted(false);
            botonMedicamento.setContentAreaFilled(true);

            // Crear un panel interno para el nombre y la dosis
            JPanel panelInterno = new JPanel(new GridLayout(2, 1));
            panelInterno.setOpaque(false);

            // Crear etiquetas para el nombre y la dosis
            JLabel labelNombre = new JLabel("Medicamento: " + nombres[i], SwingConstants.LEFT);
            JLabel labelDosis = new JLabel("Dosis: " + dosis[i], SwingConstants.LEFT);

            // Aplicar sangría a las etiquetas
            labelNombre.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0)); // Sangría de 20 píxeles
            labelDosis.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0)); // Sangría de 20 píxeles

            // Agregar etiquetas al panel interno
            panelInterno.add(labelNombre);
            panelInterno.add(labelDosis);

            // Agregar el panel interno al botón
            botonMedicamento.add(panelInterno);

            // Agregar listeners para cambiar el color al pasar el cursor
            botonMedicamento.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    botonMedicamento.setBackground(Color.lightGray);
                }

                public void mouseExited(MouseEvent evt) {
                    botonMedicamento.setBackground(Color.white);
                }
            });

            // Configurar el ActionListener para el botón
            botonMedicamento.setActionCommand(Integer.toString(i));
            botonMedicamento.addActionListener(this);

            // Agregar el botón al contenedor principal
            container.add(botonMedicamento);
        }

        // Configuración del layout principal
        this.setLayout(new GridBagLayout());

        // Configuración de GridBagConstraints para el scroll
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(35, 35, 35, 35);
        this.add(scroll, gbc);

        // APLICAR ESTILOS
        EstilosUI.aplicarEstiloPanelPrincipal(container);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Obtener el índice del medicamento seleccionado
        int index = Integer.parseInt(e.getActionCommand());
        String n = nombres[index];
        String d = dosis[index];
        String fIni = fechaIni[index];
        String fFin = fechaFin[index];
        container.removeAll();
        container.add(new MedicamentoInfo(n, d, fIni, fFin, this));
        container.revalidate();
        container.repaint();
    }

    public void restablecer() {
        container.removeAll();
        for (int i = 0; i < nombres.length; i++) {
            // Crear un botón personalizado (igual que en init)
            JButton botonMedicamento = new JButton();
            botonMedicamento.setLayout(new GridBagLayout());
            botonMedicamento.setBackground(Color.white);
            botonMedicamento.setBorderPainted(false);
            botonMedicamento.setFocusPainted(false);
            botonMedicamento.setContentAreaFilled(true);

            JPanel panelInterno = new JPanel(new GridLayout(2, 1));
            panelInterno.setOpaque(false);

            JLabel labelNombre = new JLabel("Medicamento: " + nombres[i], SwingConstants.LEFT);
            JLabel labelDosis = new JLabel("Dosis: " + dosis[i], SwingConstants.LEFT);

            labelNombre.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
            labelDosis.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

            panelInterno.add(labelNombre);
            panelInterno.add(labelDosis);

            botonMedicamento.add(panelInterno);

            botonMedicamento.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    botonMedicamento.setBackground(Color.lightGray);
                }

                public void mouseExited(MouseEvent evt) {
                    botonMedicamento.setBackground(Color.white);
                }
            });

            botonMedicamento.setActionCommand(Integer.toString(i));
            botonMedicamento.addActionListener(this);

            container.add(botonMedicamento);
        }
        container.revalidate();
        container.repaint();
    }
}