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

        int y = 50;

        for (int i = 0; i < race.getSeatCount(); i++)
        {
            drawTypist(g, race.getTypist(i), y);
            y += 50;
        }
    }

    private void drawTypist(Graphics g, Typist t, int y)
    {
        if (t == null) return;

        String passage = race.getPassage();
        int progress = t.getProgress();

        int x = 50;

        String typed = passage.substring(0, Math.min(progress, passage.length()));
        String remaining = passage.substring(Math.min(progress, passage.length()));

        g.setColor(t.getColour());
        g.drawString(typed, x, y);

        FontMetrics fm = g.getFontMetrics();
        int typedWidth = fm.stringWidth(typed);

        g.drawString(String.valueOf(t.getSymbol()), x + typedWidth, y);

        g.setColor(Color.BLACK);
        g.drawString(remaining, x + typedWidth + 10, y);

        g.drawString(t.getName(), x, y - 20);
    }
}