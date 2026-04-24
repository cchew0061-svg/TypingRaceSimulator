import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class RacerConfigurationScreen extends JFrame{
    private TypistConfigurationPanel[] panels;
    private boolean autocorrect;
    private boolean caffeine;
    private boolean night;

    public RacerConfigurationScreen(int seatCount, String passage, boolean autocorrect, boolean caffeine, boolean night)
    {
        this.autocorrect = autocorrect;
        this.caffeine = caffeine;
        this.night = night;
        setTitle("Configure Racers");
        setSize(500, 400);
        setVisible(true);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        panels = new TypistConfigurationPanel[seatCount];

        for (int i = 0; i < seatCount; i++)
        {
            panels[i] = new TypistConfigurationPanel(i + 1);
            add(panels[i]);
        }

        JButton start = new JButton("Start Race");

        start.addActionListener(e -> startRace(passage));

        add(start);

        setVisible(true);
    }  

    private void startRace(String passage)
    {

        TypingRace race = new TypingRace(passage, panels.length, autocorrect, caffeine);

        for (int i = 0; i < panels.length; i++)
        {
            race.addTypist(panels[i].createTypist(night), i);
        }

        new TypingRaceGUI(race);

        dispose();
    }
}
