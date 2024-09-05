package buttons;

public class Button {
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



}
