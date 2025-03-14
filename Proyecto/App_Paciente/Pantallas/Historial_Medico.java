package App_Paciente.Pantallas;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import App_Paciente.Pantallas.SubPantallas.VisitaInfo;
import Style.EstilosUI;

public class Historial_Medico extends JPanel implements ActionListener {
    String[] vistDoctor = { "Fecha: 10/03/2024     Motivo: Control rutinario",
            "Fecha: 15/03/2024     Motivo: Dolor de garganta", "Fecha: 20/03/2024     Motivo: Revisión de alergias",
            "Fecha: 25/03/2024     Motivo: Dolor en el pecho" };
    String[] diagnostico = { "Presión arterial alta", "Faringitis aguda", "Alergia al polen", "Ansiedad" };
    String[] medicamentosAplicados = { "Losartán 50 mg, Amlodipino 5 mg",
            "Amoxicilina 500 mg cada 8 horas, Paracetamol 500 mg cada 6 horas", "Loratadina 10 mg una vez al día",
            "Diazepam 5 mg según necesidad" };
    String[] documentosCitas = { "Receta_10_03_2024.pdf, Informe_Presion_10_03_2024.pdf",
            "Receta_15_03_2024.pdf, Cultivo_Garganta_15_03_2024.pdf",
            "Pruebas_Alergia_20_03_2024.pdf, Recomendaciones_Alergia_20_03_2024.pdf",
            "Electrocardiograma_25_03_2024.pdf, Informe_Psiquiatria_25_03_2024.pdf" };
    JPanel container;
    JPanel container_base;

    public Historial_Medico() {
        init();
    }

    public void init() {
        container_base = new JPanel();
        container = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();

        // Configuración del container_base
        container_base.setLayout(new GridLayout(vistDoctor.length, 1));
        container_base.setOpaque(false);

        // Agregar botones al container_base
        for (int i = 0; i < vistDoctor.length; i++) {
            JButton visita = new JButton(vistDoctor[i]);

            container_base.add(visita);
            visita.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    visita.setBackground(Color.lightGray);
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    visita.setBackground(Color.white);
                }

            });
            visita.setActionCommand(Integer.toString(i));
            visita.addActionListener(this);
            visita.setBorderPainted(false);
            visita.setContentAreaFilled(true);
        }

        // Configuración del container
        container.setLayout(new GridLayout(1, 1));
        container.add(container_base);

        // Configuración del layout principal
        this.setLayout(new GridBagLayout());

        // Configuración de GridBagConstraints para el container
        gbc.gridx = 0;
        gbc.gridy = 0; // Comienza en la fila 0
        gbc.weightx = 1.0; // Ocupa todo el ancho disponible
        gbc.weighty = 1.0; // Ocupa todo el alto disponible
        gbc.fill = GridBagConstraints.BOTH; // Se expande en ambas direcciones
        gbc.insets = new Insets(30, 30, 30, 30); // Ajusta los márgenes si es necesario
        this.add(container, gbc);

        // APLICAR ESTILOS
        EstilosUI.aplicarEstiloPanelPrincipal(container);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = Integer.parseInt(e.getActionCommand());
        String diag = diagnostico[index];
        String medic = medicamentosAplicados[index];
        String doc = documentosCitas[index];
        container.removeAll();
        container.add(new VisitaInfo(diag, medic, doc, this));
        container.revalidate();
        container.repaint();
    }

    public void reset() {
        container.removeAll();
        container.add(container_base);
        container.revalidate();
        container.repaint();
    }
}