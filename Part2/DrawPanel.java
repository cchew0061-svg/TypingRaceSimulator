import java.awt.*;
import javax.swing.*;

public class DrawPanel extends JPanel
{
    private TypingRace race;

    public DrawPanel(TypingRace race)
    {
        this.race = race;
    }

    public void startAnimation(){
        new Thread(() -> {
            while (!race.raceFinished())
            {
                race.advanceOneTurn();
                repaint();

                try { Thread.sleep(200); } catch (Exception e) {}
            }

            Typist winner = race.getWinner();

            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(
                    null,
                    "The winner is: " + winner.getName() +
                    "\nThe winner's accuracy is: " + winner.getAccuracy()
                );
            });
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
        String passage = race.getPassage();

        int progress = t.getProgress();

        if (progress > passage.length())
        {
            progress = passage.length();
        }

        String typed = passage.substring(0, progress);
        String remaining = passage.substring(progress);

        g.drawString(t.getName(), 10, y);

        int x = 120;

        g.setColor(t.getColour());
        g.drawString(typed, x, y);

        int typedWidth = g.getFontMetrics().stringWidth(typed);

        g.setColor(t.getColour());
        g.drawString(String.valueOf(t.getSymbol()), x + typedWidth, y);

        int cursorWidth = g.getFontMetrics().stringWidth(String.valueOf(t.getSymbol()));

        g.setColor(Color.BLACK);
        g.drawString(remaining, x + typedWidth + cursorWidth, y);
    }
}