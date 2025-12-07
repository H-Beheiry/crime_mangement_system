package GUI.officerBoard;
import java.awt.*;
import java.util.ArrayList;
import GUI.officerDashboard;
import core_classes.Case;
import core_classes.policeOfficer;
import system_mangement.crimeSystem;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;

public class viewHandledCasesDashboard extends JFrame {
    protected crimeSystem sys;
    protected policeOfficer officer;

    public viewHandledCasesDashboard(crimeSystem sys, policeOfficer officer){
        this.sys = sys;
        this.officer = officer;

        setSize(800, 500);
        setTitle("Handled Cases Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        String[] columnNames= {"Case ID", "Name", "Crime Type", "Start Date", "Description"};
        ArrayList<Case> casesHandled= officer.getCases();
        String[][] data= new String[casesHandled.size()][5];
        for (int i= 0; i < casesHandled.size(); i++) {
            Case c= casesHandled.get(i);
            data[i][0]= c.getCaseID();
            data[i][1]= c.getName();
            data[i][2]= c.getCrimeType();
            data[i][3]= c.getStartDate();
            data[i][4]= c.getDescription();
        }
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable casesTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(casesTable);

        add(scrollPane, BorderLayout.CENTER);

        JButton BackBtn= new JButton("Back To Dashboard");
        BackBtn.addActionListener(e -> back());
        add(BackBtn, BorderLayout.SOUTH);

    }
    public void back(){
        new officerDashboard(sys, officer).setVisible(true);
        this.dispose();
    }
}
