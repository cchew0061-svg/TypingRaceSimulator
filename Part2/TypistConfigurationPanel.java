import java.awt.*;
import javax.swing.*;

class TypistConfigurationPanel extends JPanel
{
    private JTextField nameField;
    private JComboBox<String> styleBox;
    private JComboBox<String> keyboardBox;
    private JCheckBox wristSupport;
    private JCheckBox energyDrink;
    private JCheckBox headphones;
    private JTextField symbolField;
    private Color chosenColour = Color.GREEN;

    public TypistConfigurationPanel(int number)
    {
        setLayout(new FlowLayout());
        add(new JLabel("Player " + number));

        nameField = new JTextField("Player " + number, 10);

        styleBox = new JComboBox<>(new String[]{
            "Touch Typist",
            "Hunt & Peck",
            "Phone Thumbs",
            "Voice-to-Text"
        });

        keyboardBox = new JComboBox<>(new String[]{
            "Mechanical",
            "Membrane",
            "Touchscreen",
            "Stenography"
        });

        wristSupport = new JCheckBox("Wrist Support");
        energyDrink = new JCheckBox("Energy Drink");
        headphones = new JCheckBox("Noise Cancelling Headphones");

        symbolField = new JTextField("@");

        JButton colourButton = new JButton("Choose Colour");
        JPanel colourPreview = new JPanel();
        colourPreview.setBackground(chosenColour);
        colourPreview.setPreferredSize(new Dimension(20, 20));

        colourButton.addActionListener(e -> {
            Color selected = JColorChooser.showDialog(
                this,
                "Choose Player Colour",
                chosenColour
            );

            if (selected != null)
            {
                chosenColour = selected;
                colourPreview.setBackground(chosenColour);
            }
        });

        add(nameField);
        add(symbolField);
        add(colourButton);
        add(colourPreview);
        add(styleBox);
        add(keyboardBox);
        add(wristSupport);
        add(energyDrink);
        add(headphones);
    }

    public Typist createTypist(boolean night)
    {
        double accuracy = 0.6;
        double burnoutValue = 1;
        int extraBurnoutTurns = 0;

        //TYPING STYLE
        if(styleBox.getSelectedItem().equals("Touch Typist")){
            accuracy = 0.8;
            burnoutValue = 1;
            extraBurnoutTurns = 1;
        }
        if(styleBox.getSelectedItem().equals("Hunt & Peck")){
            accuracy = 0.5;
            burnoutValue = 1.1;
            extraBurnoutTurns = 0;
        }
        if(styleBox.getSelectedItem().equals("Phone Thumbs")){
            accuracy = 0.4;
            burnoutValue = 0.8;
            extraBurnoutTurns = -1;
        }
        if(styleBox.getSelectedItem().equals("Voice-to-Text")){
            accuracy = 0.3;
            burnoutValue = 0.5;
            extraBurnoutTurns = -1;
        }
        //KEYBOARD TYPES
        if(keyboardBox.getSelectedItem().equals("Mechanical")){
            accuracy = accuracy * 1.2;
            burnoutValue = burnoutValue * 0.8;
        }
        if(keyboardBox.getSelectedItem().equals("Membrane")){
            accuracy = accuracy * 0.7;
            burnoutValue = burnoutValue * 0.6;
            extraBurnoutTurns = extraBurnoutTurns - 1;
        }
        if(keyboardBox.getSelectedItem().equals("Touchscreen")){
            accuracy = accuracy * 0.5;
            burnoutValue = burnoutValue * 0.3;
            extraBurnoutTurns = extraBurnoutTurns + 1;
        }
        if(keyboardBox.getSelectedItem().equals("Stenography")){
            accuracy = accuracy * 1.4;
            extraBurnoutTurns = extraBurnoutTurns + 2;
            burnoutValue = burnoutValue * 1.2;
        }
        //ADD ONS
        if(wristSupport.isSelected()){
            extraBurnoutTurns = extraBurnoutTurns - 1;
        }
        if(headphones.isSelected()){
            accuracy = accuracy * 1.1;
        }

        //NIGHT
        if(night){
            accuracy = accuracy * 0.8;
        }

        if(accuracy > 1){
            accuracy = 1;
        }
        else if(accuracy < 0){
            accuracy = 0;
        }

        char typistSymbol;
        if(symbolField.getText().trim() == null){
            typistSymbol = '!';
        }
        else{
            typistSymbol = (symbolField.getText().trim()).charAt(0);
        }

        return new Typist(typistSymbol, nameField.getText(), accuracy, burnoutValue, extraBurnoutTurns, energyDrink.isSelected(), chosenColour);
    }
}