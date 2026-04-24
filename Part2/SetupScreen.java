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

        //CHOOSE RACE LENGTH
        String[] lengthOptions = {"Short", "Medium", "Long", "Custom"};

        JComboBox<String> passageBox = new JComboBox<>(lengthOptions);

        JTextField customField = new JTextField(20);
        customField.setEnabled(false);

        passageBox.addActionListener(e -> {
            String selected = (String) passageBox.getSelectedItem();

            customField.setEnabled(selected.equals("Custom"));
        });
        frame.add(new JLabel("Passage Length:"));
        frame.add(passageBox);
        frame.add(customField);

        //CHOOSE GLOBAL MODIFIERS
        JCheckBox autocorrectBox = new JCheckBox("Autocorrect");
        JCheckBox caffeineBox = new JCheckBox("Caffeine Mode");
        JCheckBox nightBox = new JCheckBox("Night Shift");

        frame.add(autocorrectBox);
        frame.add(caffeineBox);
        frame.add(nightBox);

        frame.add(startButton);

        frame.setVisible(true);



        //START BUTTON
        startButton.addActionListener(e -> {
            int seats = slider.getValue();
            String selected = (String) passageBox.getSelectedItem();
            String passage;

            switch (selected) {
                case "Short":
                    passage = "This is a short passage.";
                    break;
                case "Medium":
                    passage = "This passage is a medium length. This is an extra sentence to make it medium.";
                    break;
                case "Long":
                    passage = "This passage is longer than the other default passages. That is because it has more sentences than the others. This is the last sentence of the passage.";
                    break;
                case "Custom":
                    passage = customField.getText().trim();
                    if(passage.isEmpty()){
                        passage = "You didn't input a custom text.";
                    }
                    break;
                default:
                    passage = "default text";
                    break;
            }

            new RacerConfigurationScreen(seats, passage, autocorrectBox.isSelected(), caffeineBox.isSelected(), nightBox.isSelected());

            frame.dispose();
        });

    }
}