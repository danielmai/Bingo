import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JToolTip;

import java.awt.Container;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
/**
 * Bingo squares that contain a unique number between 1-75.
 * 
 * @author Daniel Mai, Baotuan Nguyen
 * @version 03/06/12
 */

public class BingoGame extends JFrame
{
    public static final int WIDTH = 900;
    public static final int LENGTH = 700;

    private JPanel panel;
    private JButton reset;
    private JButton bingo;
    private JButton nextNum;
    private JButton start;
    private JButton stop;
    private Container layout;
    private Container boxLayout;

    private BingoNumbers bingoNumbers;
    private BingoGrid dummyGrid;
    private BingoGridHuman humanGrid;
    private BingoGridComputer computerGrid;

    private MouseListener mouseListener;
    private ActionListener timer;
    private ActionListener buttonListener;
    private final int DELAY;
    private boolean startGame = false;
    private boolean winner = false;

    /**
     * Constructs the game window
     */
    public BingoGame() {
        super("Bingo Game by Daniel Mai and Baotuan Nguyen - March 5, 2012");
        setSize(WIDTH, LENGTH);

        panel = new JPanel();

        //JButtons
        reset = new JButton("Reset");
        bingo = new JButton("Bingo!");
        nextNum = new JButton("Call Next Number");
        start = new JButton("Start");
        stop = new JButton("Stop");

        //add JButtons to the panel
        panel.add(start);
        panel.add(stop);
        panel.add(reset);
        panel.add(bingo);
        panel.add(nextNum);

        //creates tooltips for buttons
        reset.setToolTipText("Resets the game");
        bingo.setToolTipText("I have bingo!");
        nextNum.setToolTipText("Call next number");
        start.setToolTipText("Starts/resumes the game");
        stop.setToolTipText("Stops/pauses the game");

        //adds ActionListeners to buttons
        buttonListener = new ButtonListener();
        reset.addActionListener(buttonListener);
        bingo.addActionListener(buttonListener);
        nextNum.addActionListener(buttonListener);
        start.addActionListener(buttonListener);
        stop.addActionListener(buttonListener);

        layout = this.getContentPane();
        layout.add(panel, "South");
        setVisible(true);

        mouseListener = new MouseClickListener();
        timer = new MyTimer();
        DELAY = 2500;
        Timer t = new Timer(DELAY, timer);
        t.start();

        humanGrid = new BingoGridHuman();
        computerGrid = new BingoGridComputer();
        bingoNumbers = new BingoNumbers();

        add(humanGrid); 
        setVisible(true);

        add(computerGrid);
        setVisible(true);

        add(bingoNumbers);
        setVisible(true);

        humanGrid.addMouseListener(mouseListener);
        setVisible(true);
    }

    //the timer
    class MyTimer implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (startGame && !winner) {
                bingoNumbers.generateNumber();
                humanGrid.isCalled();
                computerGrid.highlightSquare();
                if (computerGrid.checkWin()) {
                    computerGrid.setWinnerMessage("WINNER: COMPUTER");
                    winner = true;
                }
                humanGrid.setWinnerMessage("");
                bingoNumbers.repaint();
                computerGrid.repaint();
            }
        }
    }

    //The Mouse listener
    class MouseClickListener implements MouseListener {
        public void mousePressed(MouseEvent event) {
            int x = event.getX();
            int y = event.getY();
            humanGrid.highlightSquare(x, y);            
        }

        public void mouseReleased(MouseEvent event) {}

        public void mouseClicked (MouseEvent event) {}

        public void mouseEntered(MouseEvent event) {}

        public void mouseExited(MouseEvent event) {}
    }

    //the button listener
    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Object source = event.getSource();
            if (source == reset) { //resets the boards and the bingo number callings
                humanGrid.initializeGrid();
                computerGrid.initializeGrid();
                bingoNumbers.numbers.clear();
                winner = false;
            } else if (source == bingo) { //checks if the human grid has won
                if (!winner) {
                    if (humanGrid.checkWin()) {
                        humanGrid.setWinnerMessage("WINNER: HUMAN");
                        winner = true;
                    } else {
                        humanGrid.setWinnerMessage("Sorry, you haven't gotten bingo.");
                    }
                }
            } else if (source == nextNum) { //calls the next bingo number and checks for winner
                if (!winner) {
                    bingoNumbers.generateNumber();
                    humanGrid.setWinnerMessage("");
                    computerGrid.highlightSquare();   
                    humanGrid.isCalled();         
                    if (computerGrid.checkWin()) {
                        computerGrid.setWinnerMessage("WINNER: COMPUTER");
                        winner = true;
                    }
                }
            } else if (source == start) { //starts the game
                startGame = true;
            } else if (source == stop) { //stops/pauses the game
                startGame = false;
            }
            computerGrid.highlightSquare();
            humanGrid.repaint();
            computerGrid.repaint();
            bingoNumbers.repaint();
            layout.repaint();
        }
    }
}