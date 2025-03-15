package App_Doctor.Pantallas.SubPantallas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VisitaInfo extends JPanel implements ActionListener {
    JButton volver;
    JLabel diagnostico, medicamentosAplicados, documentosCitas;
    JPanel container;
    Historial_Medico parent;

    public VisitaInfo(String diag, String medicamentos, String documentos, Historial_Medico parent){
        this.parent = parent;
        init(diag, medicamentos, documentos);
    }

    public void init(String diag, String medicamentos, String documentos){
        volver = new JButton("Volver");
        diagnostico = new JLabel("Diagnostico del Paciente: " + diag);
        medicamentosAplicados = new JLabel("Medicamentos aplicados: " + medicamentos);
        documentosCitas = new JLabel("Documentos adjuntos: " + documentos);
        container = new JPanel();

        container.setLayout(new GridLayout(8,1));
        container.add(diagnostico);
        container.add(medicamentosAplicados);
        container.add(documentosCitas);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
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
            parent.reset();
        }
    }
    
}
