package App_Paciente.Pantallas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import App_Paciente.Pantallas.SubPantallas.VisitaInfo;
import Style.EstilosUI;

public class Historial_Medico extends JPanel implements ActionListener {
    String[] vistDoctor = { "Fecha: 10/03/2024     Motivo: Control rutinario",
            "Fecha: 15/03/2024     Motivo: Dolor de garganta", "Fecha: 20/03/2024     Motivo: Revisión de alergias",
            "Fecha: 25/03/2024     Motivo: Dolor en el pecho", "Fecha: 30/03/2024     Motivo: Dolor general",
            "Fecha: 10/04/2024     Motivo: Dolor en de rodilla" };
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
    JScrollPane scroll;

    public Historial_Medico() {
        setLayout(new BorderLayout()); // Cambia el layout a BorderLayout
        init();
    }

    public void init() {
        container_base = new JPanel();
        container = new JPanel();
        // GridBagConstraints gbc = new GridBagConstraints();

        // Configuración del container_base
        container_base.setLayout(new GridLayout(vistDoctor.length, 1, 0, 5)); // Espacio vertical entre botones
        container_base.setOpaque(false);

        // Agregar botones al container_base
        for (int i = 0; i < vistDoctor.length; i++) {
            JButton visita = new JButton(vistDoctor[i]);
            visita.setBackground(Color.WHITE);

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
        container.setLayout(new BorderLayout());
        container.setOpaque(false);
        container.add(container_base, BorderLayout.CENTER); // Agrega container_base al centro del container

        // ScrollPane para el contenedor
        scroll = new JScrollPane(container);

        scroll.setBorder(BorderFactory.createEmptyBorder(35, 35, 35, 35)); // Bordes internos de 35 píxeles
        scroll.setPreferredSize(null); // Elimina el tamaño preferido
        scroll.setMinimumSize(null); // Elimina el tamaño mínimo
        add(scroll, BorderLayout.CENTER); // Asegura que el JScrollPane ocupe todo el espacio disponible

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
        container.add(container_base, BorderLayout.CENTER);
        container.revalidate();
        container.repaint();
    }
}