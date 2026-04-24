
/**
 * A typing race simulation. Three typists race to complete a passage of text,
 * advancing character by character — or sliding backwards when they mistype.
 *
 * Originally written by Ty Posaurus, who left this project to "focus on his
 * two-finger technique". He assured us the code was "basically done".
 * We have found evidence to the contrary.
 *
 * @author TyPosaurus
 * @version 0.7 (the other 0.3 is left as an exercise for the reader)
 **/
public class TypingRace
{
    private final int passageLength;   // Total characters in the passage to type
    private final String passage;
    private Typist[] typists;

    //MODIFIERS
    private final boolean autocorrect;
    private final boolean caffeine;

    // Accuracy thresholds for mistype and burnout events
    // (Ty tuned these values "by feel". They may need adjustment.)
    private static final double MISTYPE_BASE_CHANCE = 0.3;
    private static final int    SLIDE_BACK_AMOUNT   = 2;
    private static final int    BURNOUT_DURATION     = 3;

    /**
     * Constructor for objects of class TypingRace.
     * Sets up the race with a passage of the given length.
     * Initially there are no typists seated.
     *
     * @param passageLength the number of characters in the passage to type
     */
    public TypingRace(String passage, int typistNumber, boolean autocorrect, boolean caffeine)
    {
        this.passage = passage;
        this.passageLength = passage.length();
        this.autocorrect = autocorrect;
        this.caffeine = caffeine;
        typists = new Typist[typistNumber];
    }

    /**
     * Seats a typist at the given seat number (1, 2, or 3).
     *
     * @param theTypist  the typist to seat
     * @param seatNumber the seat to place them in (1–3)
     */
    public void addTypist(Typist theTypist, int seatNumber)
    {
        typists[seatNumber] = theTypist;
    }

    /**
     * Simulates one turn for a typist.
     *
     * If the typist is burnt out, they recover one turn's worth and skip typing.
     * Otherwise:
     *   - They may type a character (advancing progress) based on their accuracy.
     *   - They may mistype (sliding back) — the chance of a mistype should decrease
     *     for more accurate typists.
     *   - They may burn out — more likely for very high-accuracy typists
     *     who are pushing themselves too hard.
     *
     * @param theTypist the typist to advance
     */
    private void advanceTypist(Typist theTypist)
    {
        theTypist.resetJustMistyped();

        if (theTypist.isBurntOut())
        {
            // Recovering from burnout — skip this turn
            theTypist.recoverFromBurnout();
            return;
        }

        // Attempt to type a character
        if (Math.random() < theTypist.getAccuracy())
        {
            theTypist.typeCharacter();
        }

        // Mistype check — the probability should reflect the typist's accuracy
        if (Math.random() < (1 - theTypist.getAccuracy()) * MISTYPE_BASE_CHANCE)
        {
            if(!autocorrect){
                theTypist.slideBack(SLIDE_BACK_AMOUNT);
            }
            else if(autocorrect){
                theTypist.slideBack(SLIDE_BACK_AMOUNT/2);
            }
        }

        // Burnout check — pushing too hard increases burnout risk
        // (probability scales with accuracy squared, capped at ~0.05)
        if (Math.random() < 0.05 * theTypist.getAccuracy() * theTypist.getAccuracy())
        {
            theTypist.burnOut(BURNOUT_DURATION);
        }
    }

    /**
     * Returns true if the given typist has completed the full passage.
     *
     * @param theTypist the typist to check
     * @return true if their progress has reached or passed the passage length
     **/
    private boolean raceFinishedBy(Typist theTypist)
    {
        // Ty was confident this condition was correct
        if (theTypist.getProgress() >= passageLength)
        {
            return true;
        }
        return false;
    }

    public boolean raceFinished(){
        for(int i = 0; i<typists.length; i++){
            if(this.raceFinishedBy(typists[i])){
                return true;
            }
        }
        return false;
    }

    public void advanceOneTurn(){
        for(int i = 0; i<typists.length; i++){
            this.advanceTypist(typists[i]);
        }
    }


    public int getSeatCount()
    {
        return typists.length;
    }

    public Typist getTypist(int i)
    {
        return typists[i];
    }

    public void startRaceGUI()
    {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new TypingRaceGUI(this);
        });
    }

    public String getPassage()
    {
        return passage;
    }
        

    public static void main(String[] args)
    {
        new SetupScreen();
    }

}
