import java.awt.*;
import javax.swing.*;

public class SetupScreen
{
    public SetupScreen()
    {
        JFrame frame = new JFrame("Setup Race");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JLabel label = new JLabel("Number of Competitors: 3");

        JSlider slider = new JSlider(2, 6, 3);

        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        slider.addChangeListener(e -> {
            int value = slider.getValue();
            label.setText("Number of Competitors: " + value);
        });

        JButton startButton = new JButton("Start Race");

        startButton.addActionListener(e -> {
            int seats = slider.getValue();

            TypingRace race = new TypingRace(40, seats);

            for (int i = 0; i < seats; i++)
            {
                race.addTypist(new Typist('@', "Player " + (i + 1), Math.random()), i);
            }

            new TypingRaceGUI(race);

            frame.dispose();
        });

        frame.add(label);
        frame.add(slider);
        frame.add(startButton);

        frame.setVisible(true);
    }
}