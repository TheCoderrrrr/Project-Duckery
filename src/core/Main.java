package core;

import core.messages.MessageManager;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {

    public final static int FRAMES_PER_SECOND = 60;
    private static AppGameContainer appgc;

    private static final int OPEN_ID = 0;
    private static final int GAME_ID = 1;

    private BasicGameState game;

    private BasicGameState open;

    public Main(String name) {
        super(name);


        open = new Opening(OPEN_ID);
        game = new Game(GAME_ID);
    }

    public static int getScreenWidth() {
        return appgc.getScreenWidth();
    }

    public static int getScreenHeight() {
        return appgc.getScreenHeight();
    }

    public void initStatesList(GameContainer gc) throws SlickException {
        addState(open);
        addState(game);
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