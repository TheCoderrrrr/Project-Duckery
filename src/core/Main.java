package core;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {

    public final static int FRAMES_PER_SECOND = 60;
    private static AppGameContainer appgc;

    private final static int OG_X = 1920;
    private final static int OG_Y = 1080;

    public static final int OPEN_ID = 0;
    public static final int GAME_ID = 1;
    public static final int END_ID = 2;

    private BasicGameState game;
    private BasicGameState open;
    private BasicGameState end;

    public Main(String name) {
        super(name);

        open = new Opening(OPEN_ID);
        game = new Game(GAME_ID);
        end = new End(END_ID);
    }

    public static int getScreenWidth() {
        return appgc.getScreenWidth();
    }
    public static int getScreenHeight() {
        return appgc.getScreenHeight();
    }

    //USE FOR DISPLAYS
    //returns a screen-adjusted x and y given original value (on 1920x 1080 screen)
    public static int getAdjustedX(int x){return (int)((float)x/OG_X*getScreenWidth()); }
    public static int getAdjustedY(int y){return (int)((float)y/OG_Y*getScreenHeight()); }

    //USE FOR CLICKING/MOUSEOVER
    //returns an original value (on 1920x 1080 screen) x&y given a screen-adjusted x and y
    public static int getOriginalX(int x){return (int)((float)x/(float)(getScreenWidth())  *OG_X); }
    public static int getOriginalY(int y){return (int)((float)y/(float)(getScreenHeight())   *OG_Y); }

    public void initStatesList(GameContainer gc) throws SlickException {
        addState(open);
        addState(game);
        addState(end);
    }

    public static void main(String[] args) {
        try{
            appgc = new AppGameContainer(new Main("Duckery"));
            System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");

            appgc.setDisplayMode(appgc.getScreenWidth(), appgc.getScreenHeight(), false);
            appgc.setTargetFrameRate(FRAMES_PER_SECOND);
            appgc.start();
            appgc.setVSync(true);

        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}