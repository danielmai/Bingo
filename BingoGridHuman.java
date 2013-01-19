import javax.swing.JComponent;
import java.awt.RenderingHints;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.Color;
/**
 * Write a description of class BingoBoardHuman here.
 * 
 * @author Daniel Mai, Baotuan Nguyen
 * @version 02/27/12
 */
public class BingoGridHuman extends BingoGrid
{
    /**
     * Constructs BingoGridHuman
     */
    public BingoGridHuman() {
        super();        

        indentX = 50;
        indentY = 250;        
        initializeGrid();
    }

    /**
     * Highlights the square if it is clicked
     */
    public void highlightSquare(int x, int y) {
        for (int row = 0; row < WIDTH; row++) {
            for (int col = 0; col < LENGTH; col++) {
                BingoSquare square = grid[row][col];
                if (row == 2 && col == 2) {}
                else if (square.contains(x, y)) {
                    if (square.getIsClicked()) {
                        square.setIsClicked(false);
                    } else {
                        square.setIsClicked(true);
                    }
                    break;
                }
            }
        }
        repaint();
    }

    /**
     * Checks if a number in the grid has been called
     * if it has been called, set the status for that BingoSquare to be true
     */
    public void isCalled() {        
        for (int x : BingoNumbers.numbers) {
            for (int row = 0; row < WIDTH; row++) {
                for (int col = 0; col < LENGTH; col++) {
                    if (x == grid[row][col].getValue()) {
                        grid[row][col].setStatus(true);
                    }
                }
            }
        }
    }

    /**
     * Draws the grid
     * @param g the graphics component  
     */
    public void paintComponent(Graphics g) {        
        Graphics2D g2 = (Graphics2D) g;

        //font anti-aliasing
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int fontSize = 25; //font for everything else
        Font font = new Font("SansSerif", Font.PLAIN, fontSize);

        int letterFont = 48; //font for B I N G O letters
        Font font2 = new Font("SansSerif", Font.BOLD, letterFont);

        g2.setFont(font);

        //draws the board
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid.length; col++) {
                BingoSquare square = grid[row][col];

                g2.draw(square);

                //highlights the square if it is clicked
                if (square.getIsClicked() ) {
                    g2.setColor(Color.YELLOW);
                    g2.fill(square);                    
                    g2.setColor(Color.BLACK);
                    g2.draw(square);
                }
                
                //highlights the square if it is part of the winning sequence
                if (square.getIsWinner()) {
                    g2.setColor(Color.GREEN);
                    g2.fill(square);
                    g2.setColor(Color.BLACK);
                    g2.draw(square);
                }

                //the middle space is a freebie
                if (row == 2 && col == 2) {}
                else {
                    int value = grid[row][col].getValue();
                    int xCoord = (int)square.getX() + (SQUARE_SIZE / 4);
                    int yCoord = (int)square.getY() + (SQUARE_SIZE / 2) + (SQUARE_SIZE / 8);
                    if (value < 10)
                        g2.drawString(" " + value + "", xCoord, yCoord);
                    else
                        g2.drawString(value + "", xCoord, yCoord);
                }

                //prints the letters B I N G O above the board
                if (row == 0) {
                    g2.setFont(font2);
                    g2.drawString(BINGO[col], (int)square.getX() + (SQUARE_SIZE / 6), (int)square.getY() - (SQUARE_SIZE / 4) );
                    g2.setFont(font);
                }

                //writes "Human" below the board
                if (row == grid.length - 1 && col == 1) {
                    g2.setFont(font2);
                    g2.setColor(Color.LIGHT_GRAY);
                    g2.drawString("Human", (int)square.getX() + SQUARE_SIZE / 4 - 5, (int)square.getY() + SQUARE_SIZE + ((SQUARE_SIZE / 3) * 2));
                    g2.setFont(font);
                    g2.setColor(Color.BLACK);
                }
            }
        }

        //prints message if grid has won
        g2.setColor(Color.RED);
        g2.drawString(winnerMessage, 50, 190);
    }
}
