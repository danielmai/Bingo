
/**
 * Write a description of class BingoNumbers here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.ArrayList;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class BingoNumbers extends JComponent
{
    public static ArrayList <Integer> numbers = new ArrayList<Integer>();

    //     /**
    //      * Constructs BingoNumbers while generating a number
    //      */
    //     public BingoNumbers() {
    //         generateNumber();
    //     }

    /**
     * Checks if <code>value</code> already exists in the ArrayList
     * @param value the value to check
     * @return  true if a duplicate is found
     */
    public boolean isFound(int value)
    {
        for (int x : numbers) {
            if (value == (int) x)
                return true;
        }
        return false;
    }

    /**
     * Creates a unique number from 1-75 and puts it into the ArrayList
     */
    public void generateNumber() {
        boolean exit = false;
        Random generator = new Random();

        while (!exit && numbers.size() != 75) {
            int newNumber = generator.nextInt(75) + 1;  //1 - 75
            if (!isFound(newNumber)) {
                numbers.add(newNumber);
                exit = true;
            }
        }
    }

    /**
     * Displays the numbers
     * @param g the graphics object
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        //font anti-aliasing
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        int fontSize = 45;
        int bigFont = 80;
        Font font = new Font("SansSerif", Font.PLAIN, fontSize);
        g2.setFont(font);

        String call = "Calling: ";
        g2.drawString(call, BingoGame.WIDTH / 2 - 85, 70);
        if (!numbers.isEmpty()) {
            int number = numbers.get(numbers.size() - 1);
            int xCoord = BingoGame.WIDTH / 2 - 100;
            int yCoord = 145;
            g2.setFont(new Font("SansSerif", Font.BOLD, bigFont));
            if (number <= 15) {
                g2.drawString("B-" + number, xCoord, yCoord);
            } else if (number <= 30) {
                g2.drawString(" I-" + number, xCoord, yCoord);
            } else if (number <= 45) {
                g2.drawString("N-" + number, xCoord, yCoord);
            } else if (number <= 60) {
                g2.drawString("G-" + number, xCoord, yCoord);
            } else if (number <= 75) {
                g2.drawString("O-" + number, xCoord, yCoord);
            }
        }
    }
}
