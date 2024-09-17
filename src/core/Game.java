package core;

import game.Clipboard;
import game.News;
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
    private News news;

//    public boolean timerRunning;
//    public int elapseTime;

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

        Images.loadImages();

        clipboard = new Clipboard();
        wallet = new MoneyManager();
        world = new World(wallet, gc);
        news = new News();



        gc.setShowFPS(true);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        g.setBackground(Color.darkGray);
        world.render(g);
        clipboard.render(g);
        wallet.render(g);
        news.render(g);

//        g.drawString("Time: " + formatTime(elapseTime) + "\n elapse time:"+ elapseTime, 1697, 42);
//        timerRunning = true;
//
//        if (elapseTime == 2)
//        {
//            g.setColor(Color.white);
//            g.fillRect(0, 0, Main.getScreenWidth(), Main.getScreenHeight());
//            g.setColor(Color.black);
//            g.drawString("NEWS INCOMING!!!", Main.getScreenWidth() / 2, Main.getScreenHeight() / 2);
//            System.out.println("News");
//            timerRunning = false;
//        }


    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        world.update();
        clipboard.update();
        wallet.update();
        news.update(delta);

//        if(timerRunning) {
//            elapseTime ++;
//        }
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
        System.out.println(x + " : " + y);
    }

    @Override
    public void mouseWheelMoved(int newValue) {
        super.mouseWheelMoved(newValue);
        world.mouseWheelMoved(newValue);
    }

//    public String formatTime(int elapseTime)
//    {
//        int totalSeconds = elapseTime / 1000;
//        int minutes = totalSeconds / 60;
//        int seconds = totalSeconds % 60;
//
//        return String.format("%02d:%02d", minutes, seconds);
//    }
}