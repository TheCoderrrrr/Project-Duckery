package core;

import game.ResourceManager;
import open.TitleScreen;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

//displays either a win or loss at the end fo the game.
public class End extends BasicGameState {
    private int id;
    private static GameContainer gc;
    private static StateBasedGame sbg;

    private static TitleScreen title;

    public End(int id)
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

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        if (ResourceManager.getWarEffort() >=1)
        {
            g.setBackground(Color.green);
            g.drawString("you win!", Main.getScreenWidth()/2, Main.getScreenHeight()/2);
        }
        else if(ResourceManager.getWarEffort()<0)
        {
            g.setBackground(Color.red);
            g.drawString("you win!", Main.getScreenWidth()/2, Main.getScreenHeight()/2);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

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

