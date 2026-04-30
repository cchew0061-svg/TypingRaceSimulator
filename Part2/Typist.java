import java.awt.*;

/**
 * Write a description of class Typist here.
 *
 * Starter code generously abandoned by Ty Posaurus, your predecessor,
 * who typed with two fingers and considered that "good enough".
 * He left a sticky note: "the slide-back thing is optional probably".
 * It is not optional. Good luck.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Typist
{
    // Fields of class Typist
    // Hint: you will need six fields. Think carefully about their types.
    // One of them tracks how far along the passage the typist has reached.
    // Another tracks whether the typist is currently burnt out.
    // A third tracks HOW MANY turns of burnout remain (not just whether they are burnt out).
    // The remaining three should be fairly obvious.

    private final String typistName;
    private char typistSymbol;
    private double typistAccuracy;
    private int progress;
    private int burnoutTurnsLeft;
    private boolean burnoutState;
    private boolean justMistyped;

    private double burnoutValue;
    private int extraBurnoutTurns;
    private boolean energyDrink;
    private Color colour;

    // Constructor of class Typist
    /**
     * Constructor for objects of class Typist.
     * Creates a new typist with a given symbol, name, and accuracy rating.
     *
     * @param typistSymbol  a single Unicode character representing this typist (e.g. '①', '②', '③')
     * @param typistName    the name of the typist (e.g. "TURBOFINGERS")
     * @param typistAccuracy the typist's accuracy rating, between 0.0 and 1.0
     */
    public Typist(char typistSymbol, String typistName, double typistAccuracy, double burnoutValue, int extraBurnoutTurns, boolean energyDrink, Color colour)
    {
        this.typistSymbol = typistSymbol;
        this.typistName = typistName;
        this.typistAccuracy = typistAccuracy;
        this.progress = 0;
        this.burnoutTurnsLeft = 0;
        this.burnoutState = false;
        this.justMistyped = false;
        this.burnoutValue = burnoutValue;
        this.extraBurnoutTurns = extraBurnoutTurns;
        this.energyDrink = energyDrink;
        this.colour = colour;
    }


    // Methods of class Typist

    /**
     * Sets this typist into a burnout state for a given number of turns.
     * A burnt-out typist cannot type until their burnout has worn off.
     *
     * @param turns the number of turns the burnout will last
     */
    public void burnOut(int turns)
    {
        this.burnoutState = true;
        this.burnoutTurnsLeft = turns;
    }

    /**
     * Reduces the remaining burnout counter by one turn.
     * When the counter reaches zero, the typist recovers automatically.
     * Has no effect if the typist is not currently burnt out.
     */
    public void recoverFromBurnout()
    {
        if(this.burnoutState == true){
            this.burnoutTurnsLeft = this.burnoutTurnsLeft - 1;
        }
        if(this.burnoutTurnsLeft == 0){
            this.burnoutState = false;
        }
    }

    /**
     * Returns the typist's accuracy rating.
     *
     * @return accuracy as a double between 0.0 and 1.0
     */
    public double getAccuracy()
    {
        return this.typistAccuracy; // placeholder - replace with correct implementation
    }

    /**
     * Returns the typist's current progress through the passage.
     * Progress is measured in characters typed correctly so far.
     * Note: this value can decrease if the typist mistypes.
     *
     * @return progress as a non-negative integer
     */
    public int getProgress()
    {
        return this.progress; // placeholder - replace with correct implementation
    }

    /**
     * Returns the name of the typist.
     *
     * @return the typist's name as a String
     */
    public String getName()
    {
        return this.typistName; // placeholder - replace with correct implementation
    }

    /**
     * Returns the character symbol used to represent this typist.
     *
     * @return the typist's symbol as a char
     */
    public char getSymbol()
    {
        return this.typistSymbol; // placeholder - replace with correct implementation
    }

    /**
     * Returns the number of turns of burnout remaining.
     * Returns 0 if the typist is not currently burnt out.
     *
     * @return burnout turns remaining as a non-negative integer
     */
    public int getBurnoutTurnsRemaining()
    {
        return this.burnoutTurnsLeft;        // placeholder - replace with correct implementation
    }

    /**
     * Resets the typist to their initial state, ready for a new race.
     * Progress returns to zero, burnout is cleared entirely.
     */
    public void resetToStart()
    {
        this.burnoutState = false;
        this.burnoutTurnsLeft = 0;
        this.progress = 0;
        this.justMistyped = false;
    }

    /**
     * Returns true if this typist is currently burnt out, false otherwise.
     *
     * @return true if burnt out
     */
    public boolean isBurntOut()
    {
        return this.burnoutState; // placeholder - replace with correct implementation
    }

    /**
     * Advances the typist forward by one character along the passage.
     * Should only be called when the typist is not burnt out.
     */
    public void typeCharacter()
    {
        this.progress = this.progress + 1;
    }

    /**
     * Moves the typist backwards by a given number of characters (a mistype).
     * Progress cannot go below zero — the typist cannot slide off the start.
     *
     * @param amount the number of characters to slide back (must be positive)
     */
    public void slideBack(int amount)
    {
        this.progress = this.progress - amount;
        if(this.progress < 0){
            this.progress = 0;
        }
        this.justMistyped = true;
    }

    /**
     * Sets the accuracy rating of the typist.
     * Values below 0.0 should be set to 0.0; values above 1.0 should be set to 1.0.
     *
     * @param newAccuracy the new accuracy rating
     */
    public void setAccuracy(double newAccuracy)
    {
        this.typistAccuracy = newAccuracy;
        if(this.typistAccuracy > 1){
            this.typistAccuracy = 1;
        }
        else if(this.typistAccuracy < 0){
            this.typistAccuracy = 0;
        }
    }

    /**
     * Sets the symbol used to represent this typist.
     *
     * @param newSymbol the new symbol character
     */
    public void setSymbol(char newSymbol)
    {
        this.typistSymbol = newSymbol;
    }

    public boolean hasJustMistyped(){
        return this.justMistyped;
    }

    public void resetJustMistyped(){
        this.justMistyped = false;
    }

    public double getBurnoutValue(){
        return this.burnoutValue;
    }

    public void setBurnoutValue(double burnoutValue){
        this.burnoutValue = burnoutValue;
    }

    public int getExtraBurnoutTurns(){
        return this.extraBurnoutTurns;
    }

    public boolean getEnergyDrink(){
        return this.energyDrink;
    }

    public Color getColour(){
        return this.colour;
    }
}
