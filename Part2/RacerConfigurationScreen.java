import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
        JLabel touchTypistDescription = new JLabel("Touch Typist style increases accuracy, but increases burnout turns by 1.");
        JLabel huntPeckDescription = new JLabel("Hunt & Peck style increases chance of burnout very slightly but has moderate accuracy.");
        JLabel phoneThumbsDescription = new JLabel("Phone Thumbs style decreases accuracy slightly, but lowers burnout chances slightly and reduces burnout turns by 1.");
        JLabel voiceTextDescription = new JLabel("Voice to Text style decreases accuracy massively, but decreases burnout chance greatly and decreases burnout turns by 1");
        JLabel mechanicalDescription = new JLabel("Mechanical keyboard increases accuracy and decreases burnout chances.");
        JLabel membraneDescription = new JLabel("Membrane keyboard decreases accuracy, but also decreases burnout chance greatly and decreases burnout turns by 1.");
        JLabel touchscreenDescription = new JLabel("Touchscreen keyboard decreases accuracy greatly and increases burnout turns by 1, but decreases burnout chance massively.");
        JLabel stenographyDescription = new JLabel("Stenography keyboard increases accuracy greatly, but increases burnout chance slightly and increases burnout turns by 2.");
        JLabel wristDescription = new JLabel("Wrist Support reduces burnout turns by 1.");
        JLabel energyDescription = new JLabel("Energy Drink increases accuracy in the first half of the race, but decreases accuracy in the second half of the race.");
        JLabel headphonesDescription = new JLabel("Noise Cancelling Headphones increases accuracy slightly.");


        start.addActionListener(e -> startRace(passage));

        add(touchTypistDescription);
        add(huntPeckDescription);
        add(phoneThumbsDescription);
        add(voiceTextDescription);
        add(Box.createVerticalStrut(10));
        add(mechanicalDescription);
        add(membraneDescription);
        add(touchscreenDescription);
        add(stenographyDescription);
        add(Box.createVerticalStrut(10));
        add(wristDescription);
        add(energyDescription);
        add(headphonesDescription);
        add(Box.createVerticalStrut(10));
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
