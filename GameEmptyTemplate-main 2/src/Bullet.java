import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Bullet {
    private int X;
    public int Y;
    private int x2;
    private int y2;

    private int size;
    private int angle;


    PImage image;

    public Bullet(Game g, int angle) {
        this.angle = angle;
        this.X = (int)g.x;
        this.Y = (int)g.y;
        this.x2=(int)g.x;
        this.y2=(int)g.y;
        this.size = 10;

        Game.bullets.add(this);
    }

    // Getters
    public int getStartX() {
        return X;
    }

    public int getStartY() {
        return Y;
    }

    public int getSize() {
        return size;
    }


    // Setters
    public void setStartX(int startX) {
        this.X = startX;
    }

    public void setStartY(int startY){
        this.Y = startY;
    }

    public void move(Game g) {
        this.Y = Y++;

    }

    public static void drawBullets(Game g){
        for (int i = 0; i < Game.bullets.size(); i++) {
            g.fill(0,0,255);
            Game.bullets.get(i).Y -= 5*Math.sin(Math.toRadians(90-(Game.bullets.get(i).angle-90)));
            Game.bullets.get(i).X -= 5*Math.cos(Math.toRadians(90-(Game.bullets.get(i).angle-90)));

            g.ellipse(Game.bullets.get(i).X-Game.bullets.get(i).x2,Game.bullets.get(i).Y-Game.bullets.get(i).y2,Game.bullets.get(i).size, Game.bullets.get(i).size);

        }
    }
}


