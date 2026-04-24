import java.awt.*;
import javax.swing.*;

public class DrawPanel extends JPanel
{
    private TypingRace race;

    public DrawPanel(TypingRace race)
    {
        this.race = race;
    }

    public void startAnimation()
    {
        new Thread(() -> {
            while (!race.raceFinished())
            {
                race.advanceOneTurn();
                repaint();

                try {
                    Thread.sleep(200);
                } catch (Exception e) {}
            }
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int height = 60;
        for(int i = 0; i < race.getSeatCount(); i++){
            drawTypist(g, race.getTypist(i), height);
            height = height + 60;
        }
    }

    private void drawTypist(Graphics g, Typist t, int y)
    {
        if (t == null) return;

        int x = t.getProgress() * 10;

        g.fillRect(x, y, 40, 20);
        g.drawString(t.getName(), 10, y - 5);
    }
}