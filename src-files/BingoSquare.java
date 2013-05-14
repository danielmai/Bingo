import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
/**
 * Bingo squares that contain a unique number between 1-75.
 * 
 * @author Daniel Mai, Baotuan Nguyen
 * @version 02/27/12
 */
public class BingoSquare extends Rectangle
{
    //private static ArrayList<Integer> numbers = new ArrayList<Integer>();
    private int value;
    private boolean status; //true if the value has been called by the number generator
    private boolean isClicked; //true if the user has clicked on the box
    private static final int RANGE = 15; //range of the numbers per column.
    //e.g., the B-column can only have numbers ranging from 1 to 15.
    private boolean isWinner; //this square is part of the winning sequence

    private Random generator = new Random();

    /**
     * Constructor for BingoSquare
     * Contraints of the number of the square is determined by its column location
     * @param col the column of the square
     */
    public BingoSquare(int x1, int y1, int width, int height) {
        super(x1, y1, width, height);
        status = false;
        isClicked = false;
        isWinner = false;
    }

    /**
     * Mutator method for value
     */
    public void setValue(int newValue) {
        value = newValue;
    }

    /**
     * Accessor method for value
     */
    public int getValue() {
        return value;
    }

    /**
     * Changes the status of the square.
     * @param newStatus the new status of status
     */
    public void setStatus(boolean newStatus) {
        status = newStatus;
    }

    /**
     * Returns the current status of the square
     * @return the status of the square
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * Changes the isClicked boolean of the square
     * @param newIsClicked the new isClicked boolean
     */
    public void setIsClicked(boolean newIsClicked) {
        isClicked = newIsClicked;
    }

    /**
     * Returns the isClicked boolean of the square
     * @return true if the square has been clicked
     *         false if the square has not been clicked
     */
    public boolean getIsClicked() {
        return isClicked;
    }

    /**
     * Changes the isWinner boolean of the square
     *
     * @param newIsWinner the new isWinner boolean
     */
    public void setIsWinner(boolean newIsWinner) {
        isWinner = newIsWinner;
    }

    /**
     * Returns the isWinner boolean of the square
     * @return the isWinner boolean
     */
    public boolean getIsWinner() {
        return isWinner;
    }

    /**
     * Creates a unique number for the square.
     * @param col the column of the square
     */
    public int createNum(int col) {
        int temp = RANGE * (col - 1) + (generator.nextInt(15) + 1);
        value = temp;
        return temp;
    }
}
