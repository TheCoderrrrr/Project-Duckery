package core;

import open.IntroAnimation;
import open.TitleScreen;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

//plays through the animation and the title screen
public class Opening extends BasicGameState {
    private int id;
    private static GameContainer gc;
    private static StateBasedGame sbg;

    private static TitleScreen title;
    private static IntroAnimation anim;

    private static boolean titOn;

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
        Images.loadImages();
        titOn = true;

        title = new TitleScreen();
        anim = new IntroAnimation();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        if (titOn)
        {
            title.render(g);
        }

        if (!titOn && !anim.getDone())
        {
            anim.render(g);
        }
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
        if (titOn)
        {
            titOn = false;
        }
        else if(!titOn && !anim.getDone())
        {
            anim.changeSlide();
        }
        if (anim.getDone()){
            sbg.enterState(Main.GAME_ID);
        }

    }
    public void mousePressed(int button , int x, int y)
    {

    }
}
