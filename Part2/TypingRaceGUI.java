import javax.swing.*;

public class TypingRaceGUI
{
    public TypingRaceGUI(TypingRace race)
    {
        JFrame frame = new JFrame("Typing Race");

        DrawPanel panel = new DrawPanel(race);

        frame.add(panel);
        frame.setSize(800, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        panel.startAnimation();
    }
}