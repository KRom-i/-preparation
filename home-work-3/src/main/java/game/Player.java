package game;

public class Player implements Runnable {

    private final Game game;

    private final String name;

    private final boolean first;

    Player (Game game, String name, boolean first) {
        this.game = game;
        this.name = name;
        this.first = first;
    }

    @Override
    public void run () {

        while (true) {

            synchronized (game) {

                while (!game.isStart () && !first) {
                    try {
                        game.wait ();
                    } catch (InterruptedException e) {
                        e.printStackTrace ();
                    }
                }

                if (game.isOver ()) {
                    game.notifyAll ();
                    break;
                }

                game.kick (this);

                game.notifyAll ();

                try {
                    game.wait ();
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }
            }

        }

    }

    @Override
    public String toString () {
        return name;
    }
}
