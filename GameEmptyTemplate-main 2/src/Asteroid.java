import processing.core.PImage;

public class Asteroid {
    private int startX;
    private int startY;
    private int size;
    private int r;
    private int g;
    private int b;

    PImage image;

    public Asteroid(int startX, int startY, int size, int r, int g, int b) {
        this.startX = startX;
        this.startY = startY;
        this.size = size;
        this.r = r;
        this.g = g;
        this.b = b;
        //this.image=image;
    }

    // Getters
    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getSize() {
        return size;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    // Setters
    public void setStartX(int startX) {
        this.startX = startX;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }


}
