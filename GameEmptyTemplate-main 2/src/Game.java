import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;

public class Game extends PApplet {
    private ArrayList<Asteroid> asteroidList;
    private ArrayList<Bullet> bulletList;

    public int overalDegrees = 90;

    public float x;
    public float y;
    private float gridRotation;
    public PVector trianglePosition;
    private PVector velocity;
    public PVector acceleration;
    private PVector rotation;
    private float maxSpeed = 1000.0f; // Maximum speed of the ship\
    PImage asteroid;
    private int a = 0;
    public static ArrayList<Bullet> bullets = new ArrayList<>();

    public void saveGameState() {
        try {
            PrintWriter writer = new PrintWriter("game_state.txt");

            // Save player's position
            writer.println(x);
            writer.println(y);

            // Save asteroid information
            for (Asteroid asteroid : asteroidList) {
                writer.println(asteroid.getStartX());
                writer.println(asteroid.getStartY());
                writer.println(asteroid.getSize());
            }

            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadGameState() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("game_state.txt"));

            // Load player's position
            x = Float.parseFloat(reader.readLine());
            y = Float.parseFloat(reader.readLine());

            // Clear the current list of asteroids
            asteroidList.clear();

            // Load asteroid information
            String startXStr, startYStr, sizeStr;
            while ((startXStr = reader.readLine()) != null &&
                    (startYStr = reader.readLine()) != null &&
                    (sizeStr = reader.readLine()) != null) {
                int startX = Integer.parseInt(startXStr);
                int startY = Integer.parseInt(startYStr);
                int size = Integer.parseInt(sizeStr);
                asteroidList.add(new Asteroid(startX, startY, size, 0, 255, 0));
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void keyPressed() {
        if (key == 's' || key == 'S') {
            saveGameState(); // Call saveGameState when the 's' or 'S' key is pressed
        }

        if (key == 'l' || key == 'L'){
            loadGameState(); // Call loadGameState when the 'l' or 'L' key is pressed
        }
    }





    public void settings() {
        size(800, 1000);
    }

    public void setup() {


        asteroidList = new ArrayList<>();

        for (int i = 0; i < 500; i++) {
            asteroidList.add(new Asteroid((int)(Math.random() * 10000), (int)(Math.random() * 10000),
                    (int)(Math.random() * 100), 0, 255, 0));
        }

        x = 0;
        y = 0;
        gridRotation = 0;
        trianglePosition = new PVector(400, 400);

        velocity = new PVector(x, y);
        acceleration = new PVector(0, 0);
    }

    public void draw() {

        // Calculate the forward vector
        PVector forwardVector = PVector.fromAngle(gridRotation);
//        try{
//            System.out.println(bullets.get(0).getStartX()+" "+bullets.get(0).getStartY());
//
//        }catch (Exception e){
//
//        }



        if (keyPressed) {
            if (keyCode == LEFT) {
                gridRotation += radians(5);
                overalDegrees += 5;
            } else if (keyCode == RIGHT) {
                gridRotation -= radians(5);
                overalDegrees -= 5;
            }

            if (key == ' '){
                Bullet b = new Bullet(this, overalDegrees);
            }


            if (key == CODED) {
                if (keyCode == UP) {
                    // Apply acceleration in the forward direction
                    acceleration.add(forwardVector.mult(5F));
                } else if (keyCode == DOWN){
                    // Apply acceleration in the reverse direction
                    acceleration.add(forwardVector.mult(-5F));
                }

            }
        }


        // Update velocity based on acceleration
        velocity.add(acceleration);

        // Limit the velocity to the maximum speed
        velocity.limit(maxSpeed);

        // Update the position based on velocity
        y += velocity.x;
        x += velocity.y;

        // Reset acceleration to zero
        acceleration.mult(0);


        if (Math.random() < 0.015) {
            asteroidList.add(new Asteroid((int)(Math.random() * 10000), (int)(Math.random() * 10000),
                    (int)(Math.random() * 100), 0, 255, 0));
        }

        background(255);

        // Translate and rotate around the triangle's position
        pushMatrix();
        translate(trianglePosition.x, trianglePosition.y); // Translate to the triangle's position
        rotate(gridRotation);


        fill(0);
        for (Asteroid asteroid : asteroidList) {
            pushMatrix(); // Apply a separate transformation for each asteroid
            translate(asteroid.getStartX() - 5000 + x, asteroid.getStartY() - 5000 + y);
            ellipse(0, 0, asteroid.getSize(), asteroid.getSize());
            if (dist(trianglePosition.x - 400,trianglePosition.y - 400,asteroid.getStartX() - 5000 + x, asteroid.getStartY() - 5000 + y) <= asteroid.getSize()){
               exit();
            }

            popMatrix(); // Restore the transformation for the next asteroid
        }

        // Restore the transformation matrix for the grid and triangle


        Bullet.drawBullets(this);
        popMatrix();



        fill(0);
        triangle(trianglePosition.x, trianglePosition.y - 20, trianglePosition.x - 20, trianglePosition.y + 20, trianglePosition.x + 20, trianglePosition.y + 20);


    }

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}


