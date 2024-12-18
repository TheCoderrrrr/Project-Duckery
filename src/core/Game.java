package core;
import game.managers.ResourceManager;
import game.managers.PopupManager;
import core.messages.MessageManager;
import game.Clipboard;
import game.popups.PayDay;
import game.World;
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
    private PopupManager popups;
    private boolean end;

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

        MessageManager.init();

        Images.loadImages();
        Fonts.loadFonts();
        Sounds.loadSounds();

        clipboard = new Clipboard();
        world = new World(gc);
        popups = new PopupManager();

        end = false;
        gc.setShowFPS(true);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setBackground(Color.darkGray);
        world.render(g);

        clipboard.render(g);
        MessageManager.render(g);
        ResourceManager.render(g);
        popups.render(g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        world.update();
        clipboard.update();
        MessageManager.update();
        popups.update();

        if (World.getWar() && (ResourceManager.getPercentConquered() >=1 || ResourceManager.getPercentConquered()<0))
        {
            end = true;
            sbg.enterState(Main.END_ID);
        }

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
//        x = Main.getAdjustedX(x);
//        y = Main.getAdjustedY(y);


        x = Main.getOriginalX(x);
        y = Main.getOriginalY(y);

        world.mousePressed(button, x, y);
        clipboard.mousePressed(button, x, y);
        popups.mousePressed(x, y);
    }

    @Override
    public void mouseWheelMoved(int newValue) {
        super.mouseWheelMoved(newValue);
        world.mouseWheelMoved(newValue);
    }
}
// this is a test