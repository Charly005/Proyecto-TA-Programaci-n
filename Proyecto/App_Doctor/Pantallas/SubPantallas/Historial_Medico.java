package App_Doctor.Pantallas.SubPantallas;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import App_Doctor.Pantallas.Pantalla_List_Paciente;

public class Historial_Medico extends JPanel implements ActionListener{
    String[] vistDoctor = {"Fecha: 10/03/2024     Motivo: Control rutinario", "Fecha: 15/03/2024     Motivo: Dolor de garganta", "Fecha: 20/03/2024     Motivo: Revisión de alergias", "Fecha: 25/03/2024     Motivo: Dolor en el pecho"};
    String[] diagnostico = {"Presión arterial alta", "Faringitis aguda", "Alergia al polen", "Ansiedad"};
    String[] medicamentosAplicados = {"Losartán 50 mg, Amlodipino 5 mg", "Amoxicilina 500 mg cada 8 horas, Paracetamol 500 mg cada 6 horas", "Loratadina 10 mg una vez al día", "Diazepam 5 mg según necesidad"};
    String[] documentosCitas = {"Receta_10_03_2024.pdf, Informe_Presion_10_03_2024.pdf", "Receta_15_03_2024.pdf, Cultivo_Garganta_15_03_2024.pdf", "Pruebas_Alergia_20_03_2024.pdf, Recomendaciones_Alergia_20_03_2024.pdf", "Electrocardiograma_25_03_2024.pdf, Informe_Psiquiatria_25_03_2024.pdf"}; 
    JButton volver;
    JPanel container;
    JPanel container_base;
    Pantalla_List_Paciente parent;

    public Historial_Medico(Pantalla_List_Paciente parent){
        this.parent = parent;
        init();
    }

    public void init(){
        volver = new JButton("Volver");
        container_base = new JPanel();
        container = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();

        container_base.setLayout(new GridLayout(vistDoctor.length,1));
        for(int i = 0; i < vistDoctor.length; i++){
            JButton visita = new JButton(vistDoctor[i]);
            container_base.add(visita);
            visita.setActionCommand(Integer.toString(i));
            visita.addActionListener(this);
            visita.setBorderPainted(false);
            visita.setContentAreaFilled(false);
            visita.setFocusPainted(false);
            visita.setOpaque(false);
        }

        container.setLayout(new GridLayout(1,1));
        container.add(container_base);

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
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == volver){
            JPanel newPanel = new ContainerGen(parent);
            ((Pantalla_List_Paciente) parent).cambPantalla(newPanel, "Lista Pacientes");
        } else{
            int index = Integer.parseInt(e.getActionCommand());
        String diag = diagnostico[index];
        String medic = medicamentosAplicados[index];
        String doc = documentosCitas[index];
        container.removeAll();
        container.add(new VisitaInfo(diag, medic, doc, this));
        container.revalidate();
        container.repaint();
        }
    }

    public void reset(){
        container.removeAll();
        container.add(container_base);
        container.revalidate();
        container.repaint();
    }
}
