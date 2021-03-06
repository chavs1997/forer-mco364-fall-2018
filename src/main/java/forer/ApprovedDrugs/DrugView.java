package forer.ApprovedDrugs;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

@Singleton
public class DrugView extends JFrame {

    private static JComboBox<String> drugIDs = new JComboBox<>();

    JComboBox<String> getDrugIDs() {
        return drugIDs;
    }

    private static JLabel name = new JLabel();
    private static JLabel formula = new JLabel();
    private static JLabel weight = new JLabel();
    private static JLabel species = new JLabel();
    private static JLabel rings = new JLabel();
    private static JLabel image = new JLabel();

    public DrugView() {

        setTitle("Approved Drugs");
        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.insets = new Insets(3, 2, 3, 2);

        topPanel.add(new JLabel("Select a drug ID: "), constraint);
        constraint.gridy = 1;
        topPanel.add(new JLabel("   Name: "), constraint);
        constraint.gridy = 2;
        topPanel.add(new JLabel("   Formula: "), constraint);
        constraint.gridy = 3;
        topPanel.add(new JLabel("   Molecular Weight: "), constraint);
        constraint.gridy = 4;
        topPanel.add(new JLabel("   Species: "), constraint);
        constraint.gridy = 5;
        topPanel.add(new JLabel("   Aromatic Rings: "), constraint);

        constraint.gridy = 0;
        constraint.gridx = 1;
        topPanel.add(drugIDs, constraint);
        constraint.gridy = 1;
        topPanel.add(name, constraint);
        constraint.gridy = 2;
        topPanel.add(formula, constraint);
        constraint.gridy = 3;
        topPanel.add(weight, constraint);
        constraint.gridy = 4;
        topPanel.add(species, constraint);
        constraint.gridy = 5;
        topPanel.add(rings, constraint);

        mainPanel.add(topPanel);
        mainPanel.add(image);

        add(mainPanel);
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new DrugModule());
        DrugView view = injector.getInstance(DrugView.class);
        DrugController controller = injector.getInstance(DrugController.class);
        controller.requestDrugFeed();
        drugIDs.addActionListener(e -> {
            try {
                controller.fillInData((String) drugIDs.getSelectedItem(), name, formula, weight, species, rings, image);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                controller.stop();
            }
        });
        view.setVisible(true);
    }

}
