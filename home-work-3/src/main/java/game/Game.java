package game;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Game {

    private final int maxScore;

    private int currentScore;

    private final ExecutorService service;

    public Game (int maxScore) {
        if (maxScore <= 0) {
            throw new IllegalStateException ("Max score <= 0");
        }
        this.maxScore = maxScore;
        this.service = Executors.newFixedThreadPool(2);
    }

    public void start () {
        service.submit (new Player (this, "ping", true));
        service.submit (new Player (this, "pong", false));
    }

    public void kick (Player player) {
        currentScore ++;
        System.out.println (player + " current score=" + currentScore);
    }

    public boolean isStart () {
        return currentScore > 0;
    }

    public boolean isOver () {
        return currentScore == maxScore;
    }

    public static void main (String[] args) {
        Game game = new Game (20);
        game.start ();

    }

}
