package forer.earthquake.net;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;


@SuppressWarnings("serial")
@Singleton
public class EarthquakeView extends JFrame {

    private JLabel magOne = new JLabel();
    private JLabel locOne = new JLabel();
    private JLabel magTwo = new JLabel();
    private JLabel locTwo = new JLabel();
    private JLabel magThree = new JLabel();
    private JLabel locThree = new JLabel();
    private JLabel magFour = new JLabel();
    private JLabel locFour = new JLabel();
    private JLabel magFive = new JLabel();
    private JLabel locFive = new JLabel();

    public EarthquakeView() {
        setTitle("Past 5 Earthquakes in the Hour");
        setSize(500, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel homePanel = new JPanel();
        homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.Y_AXIS));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.insets = new Insets(3, 2, 3, 2);

        constraint.gridy = 1;
        mainPanel.add(new JLabel("First Earthquake: "), constraint);
        constraint.gridy = 2;
        mainPanel.add(new JLabel("Second Earthquake: "), constraint);
        constraint.gridy = 3;
        mainPanel.add(new JLabel("Third Earthquake: "), constraint);
        constraint.gridy = 4;
        mainPanel.add(new JLabel("Fourth Earthquake: "), constraint);
        constraint.gridy = 5;
        mainPanel.add(new JLabel("Fifth Earthquake: "), constraint);
        constraint.gridx = 1;
        constraint.gridy = 0;
        mainPanel.add(new JLabel("Magnitude   "), constraint);
        constraint.gridy = 1;
        mainPanel.add(magOne, constraint);
        constraint.gridy = 2;
        mainPanel.add(magTwo, constraint);
        constraint.gridy = 3;
        mainPanel.add(magThree, constraint);
        constraint.gridy = 4;
        mainPanel.add(magFour, constraint);
        constraint.gridy = 5;
        mainPanel.add(magFive, constraint);
        constraint.gridx = 2;
        constraint.gridy = 0;
        mainPanel.add(new JLabel("   Location   "), constraint);
        constraint.gridy = 1;
        mainPanel.add(locOne, constraint);
        constraint.gridy = 2;
        mainPanel.add(locTwo, constraint);
        constraint.gridy = 3;
        mainPanel.add(locThree, constraint);
        constraint.gridy = 4;
        mainPanel.add(locFour, constraint);
        constraint.gridy = 5;
        mainPanel.add(locFive, constraint);

        homePanel.add(mainPanel);
        add(homePanel);


    }

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new EarthquakeModule());
        EarthquakeView view = injector.getInstance(EarthquakeView.class);
        EarthquakeController controller = injector.getInstance(EarthquakeController.class);

        controller.refreshData();
        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                controller.stop();
            }
        });
        view.setVisible(true);
    }

    public JLabel[] getHourMagLabel() {
        JLabel[] magnitudes = new JLabel[]{magOne, magTwo, magThree, magFour, magFive};
        return magnitudes;
    }

    public JLabel[] getHourLocLabel() {
        JLabel[] locations = new JLabel[]{locOne, locTwo, locThree, locFour, locFive};
        return locations;
    }


}