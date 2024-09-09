package buttons;

public abstract class Button {
    private int x;
    private int y;
    private int w;
    private int h;

    public Button(int x, int y){
        this.x = x;
        this.y = y;
        w = 100;
        h = 100;
    }
    public Button(int x, int y, int w) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = w;
    }
    public Button(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    private boolean isMouseOver(int mX, int mY)
    {
        return (mX > x && mX < x + w && mY > y && mY < y + h);
    }
    public void mousePressed(int mX, int mY)
    {

    }

    public abstract void onClick();

}
