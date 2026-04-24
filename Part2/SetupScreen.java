import java.awt.*;
import javax.swing.*;

public class SetupScreen
{
    public SetupScreen()
    {
        //CHOOSE RACER NUMBERS
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

        frame.add(label);
        frame.add(slider);
        frame.add(startButton);

        frame.setVisible(true);

        //CHOOSE RACE LENGTH
        String[] options = {"Short", "Medium", "Long", "Custom"};

        JComboBox<String> passageBox = new JComboBox<>(options);

        JTextField customField = new JTextField(20);
        customField.setEnabled(false);

        passageBox.addActionListener(e -> {
            String selected = (String) passageBox.getSelectedItem();

            customField.setEnabled(selected.equals("Custom"));
        });
        frame.add(new JLabel("Passage Length:"));
        frame.add(passageBox);
        frame.add(customField);





        //START BUTTON
        startButton.addActionListener(e -> {
            int seats = slider.getValue();

            String selected = (String) passageBox.getSelectedItem();

            final String passage;

            switch (selected) {
                case "Short":
                    passage = "The quick brown fox.";
                    break;
                case "Medium":
                    passage = "The quick brown fox jumps over the lazy dog near the river bank.";
                    break;
                case "Long":
                    passage = "The quick brown fox jumps over the lazy dog while several typists compete furiously in an intense keyboard race.";
                    break;
                case "Custom":
                    passage = customField.getText();
                    break;
                default:
                    passage = "The quick brown fox.";
                    break;
            }

            TypingRace race = new TypingRace(passage, seats);

            for (int i = 0; i < seats; i++)
            {
                race.addTypist(new Typist('@', "Player " + (i + 1), Math.random()), i);
            }

            new TypingRaceGUI(race);

            frame.dispose();
        });

    }
}