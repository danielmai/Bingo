import javax.swing.JFrame;
/**
 * Runs BingoGame
 * 
 * @author Daniel Mai, Baotuan Nguyen
 * @version 3/2/2012
 */
public class Runner
{
    public static void main(String[] args) {
        BingoGame game = new BingoGame();
        
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
    }
}
