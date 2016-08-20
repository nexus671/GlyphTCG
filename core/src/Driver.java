import Game.Game;
import Game.Player;

/**
 * Created by acurr on 5/25/2016.
 */
public class Driver {
    public static void main(String args[]) {
        Game game = new Game(new Player("Sam"), new Player("Joey"));

        game.startGame();
    }
}
