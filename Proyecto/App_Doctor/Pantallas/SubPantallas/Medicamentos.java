package App_Doctor.Pantallas.SubPantallas;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import App_Doctor.Pantallas.Pantalla_List_Paciente;

public class Medicamentos extends JPanel implements ActionListener {
    String[] nombres = { "Paracetamol", "Amoxicilina", "Ibuprofeno", "Loratadina", "Metformina", "Omeprazol",
            "Losartán", "Atorvastatina" };
    String[] dosis = { "500 mg cada 8 horas", "875 mg cada 12 horas", "400 mg cada 6 horas", "10 mg una vez al día",
            "850 mg cada 12 horas", "20 mg en ayunas", "50 mg cada 24 horas", "10 mg antes de dormir" };
    String[] fechaIni = { "10/03/2025", "05/03/2025", "08/03/2025", "01/03/2025", "07/03/2025", "03/03/2025",
            "02/03/2025", "04/03/2025" };
    String[] fechaFin = { "17/03/2025", "12/03/2025", "15/03/2025", "10/03/2025", "07/06/2025", "17/03/2025",
            "02/06/2025", "04/09/2025" };
    JButton volver;
    JLabel medicina;
    JLabel dosis_asignada;
    JPanel container;
    JPanel nomb_medicamentos;
    JPanel dosis_aplicada;
    JScrollPane scroll;
    Pantalla_List_Paciente parent;

    public Medicamentos(Pantalla_List_Paciente parent) {
        this.parent = parent;
        init();
    }

    public void init() {
        volver = new JButton("regresar");
        medicina = new JLabel("Nombre:");
        dosis_asignada = new JLabel("Dosis: ");
        container = new JPanel();
        nomb_medicamentos = new JPanel();
        dosis_aplicada = new JPanel();
        scroll = new JScrollPane(container);
        GridBagConstraints gbc = new GridBagConstraints();

        nomb_medicamentos.setLayout(new GridLayout(nombres.length + 1, 1));
        nomb_medicamentos.add(medicina);
        for (int i = 0; i < nombres.length; i++) {
            JButton medic = new JButton(nombres[i]);
            nomb_medicamentos.add(medic);
            medic.setActionCommand(Integer.toString(i));
            medic.addActionListener(this);
            medic.setBorderPainted(false);
            medic.setContentAreaFilled(false);
            medic.setFocusPainted(false);
            medic.setOpaque(false);
        }

        dosis_aplicada.setLayout(new GridLayout(dosis.length + 1, 1));
        dosis_aplicada.add(dosis_asignada);
        for (int i = 0; i < dosis.length; i++) {
            JButton medicDosis = new JButton(dosis[i]);
            dosis_aplicada.add(medicDosis);
            medicDosis.setActionCommand(Integer.toString(i));
            medicDosis.addActionListener(this);
            medicDosis.setBorderPainted(false);
            medicDosis.setContentAreaFilled(false);
            medicDosis.setFocusPainted(false);
            medicDosis.setOpaque(false);
        }

        container.setLayout(new GridLayout(1, 2));
        container.add(nomb_medicamentos);
        container.add(dosis_aplicada);
        scroll.setPreferredSize(new Dimension(60, 60));
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        this.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(volver, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.9;
        this.add(container, gbc);

        volver.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == volver){
            JPanel newPanel = new ContainerGen(parent);
            ((Pantalla_List_Paciente) parent).cambPantalla(newPanel, "Lista Pacientes");
        } else {
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
    }

    public void restablecer(){
        container.removeAll();
        container.add(nomb_medicamentos);
        container.add(dosis_aplicada);
        container.revalidate();
        container.repaint();
    }
}