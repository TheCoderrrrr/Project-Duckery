import game.Clipboard;
import game.World;
import game.MoneyManager;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends BasicGameState {
    private int id;
    private static GameContainer gc;
    private static StateBasedGame sbg;

    private World world;
    private Clipboard clipboard;
    private MoneyManager wallet;

    public Game(int id)
    {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.gc = gc;
        this.sbg = sbg;

        clipboard = new Clipboard();
        wallet = new MoneyManager();
        world = new World(wallet, gc);


        gc.setShowFPS(true);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setBackground(Color.darkGray);
        world.render(g);
        clipboard.render(g);
        wallet.render(g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

        world.update();
        clipboard.update();
        wallet.update();
    }
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
        //load game from txt file
    }
    public void leave(GameContainer gc, StateBasedGame sbg)
    {
        //save the game in txt file
    }
    public void keyPressed(int key, char c)
    {
        world.keyPressed(key,c);
        clipboard.keyPressed(key, c);
    }
    public void mousePressed(int button , int x, int y)
    {
        world.mousePressed(button, x, y);
        clipboard.mousePressed(button, x, y);
    }
}
