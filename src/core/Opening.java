package core;

import open.TitleScreen;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Opening extends BasicGameState {
    private int id;
    private static GameContainer gc;
    private static StateBasedGame sbg;

    private static TitleScreen title;

    public Opening(int id)
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

        title = new TitleScreen();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        title.render(g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        title.update();

    }
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException
    {

    }
    public void leave(GameContainer gc, StateBasedGame sbg)
    {

    }
    public void keyPressed(int key, char c)
    {
        sbg.enterState(1);
    }
    public void mousePressed(int button , int x, int y)
    {

    }
}
