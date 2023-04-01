package project;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import project.entity.Fish;
import project.entity.Lava;
import project.entity.ObjectTime;
import project.entity.Timer;
import project.entity.character;
import project.entity.Thorn;
import project.entity.Gate;


import java.io.IOException;
import java.util.ArrayList;

public class Map {

    private BufferedImage bg[] = new BufferedImage[2];
    private ArrayList<Lava> lava = new ArrayList<Lava>();
    private ArrayList<Fish> fish = new ArrayList<Fish>();
    private ArrayList<Thorn> thorn = new ArrayList<Thorn>();
    private ArrayList<ObjectTime> time = new ArrayList<ObjectTime>();

    private Gate gate;
    private Timer timeCount;

    private int score = 0;

    public Map() {
        init();
    }

    public void draw(Graphics g) {
        g.drawImage(bg[0], 0, 0, null);

        for (int i = 0; i < lava.size(); i++) {
            lava.get(i).draw(g);
        }

        for (int i = 0; i < fish.size(); i++) {
            fish.get(i).draw(g);
        }

        for (int i = 0; i < time.size(); i++) {
            time.get(i).draw(g);
        }
        for (int i = 0; i < thorn.size(); i++) {
            thorn.get(i).draw(g);
        }

        gate.draw(g);

        g.drawImage(bg[1], 0, 0, null);
        timeCount.draw(g);

    }

    public void update() {

        for (int i = 0; i < lava.size(); i++) {
            lava.get(i).update();
        }
        for (int i = 0; i < fish.size(); i++) {
            fish.get(i).update();
        }

        timeCount.update();

    }

    public void init() {
        try {
            bg[0] = ImageIO.read(getClass().getResourceAsStream("../assets/background.png"));
            bg[1] = ImageIO.read(getClass().getResourceAsStream("../assets/Background OOP1.png"));

            lava.add(new Lava(609, 522));
            lava.add(new Lava(500, 134));

            fish.add(new Fish(500, 480));
            fish.add(new Fish(450, 80));

            time.add(new ObjectTime(250, 630));
            time.add(new ObjectTime(700, 600));
            // time.add(new ObjectTime(200,400));
            // time.add(new ObjectTime(600,300));
            // time.add(new ObjectTime(250,630));
            // time.add(new ObjectTime(200,200));

            thorn.add(new Thorn(500, 655));
            thorn.add(new Thorn(35, 153));

            gate= new Gate(860, 65);
            timeCount = new Timer();

        } catch (IOException e) {
            System.err.println("Error loading map from file: " + e.getMessage());
        }
    }

 
    public boolean isCollision(int x, int y) {
        for (int i = 0; i < lava.size(); i++) {
            if (collision.isCharacterCollisionObject(x, y, lava.get(i).getImage(), lava.get(i).getX(),
                    lava.get(i).getY())) {
                character.isDie = true;
                return true;
            }
        }
        for (int i = 0; i < thorn.size(); i++) {
            if (collision.isCharacterCollisionObject(x, y, thorn.get(i).getImage(), thorn.get(i).getX(),
                    thorn.get(i).getY())) {
                character.isDie = true;
                return true;
            }
        }
        for (int i = 0; i < fish.size(); i++) {
            if (collision.isCharacterCollisionObject(x, y, fish.get(i).getImage(), fish.get(i).getX(),
                    fish.get(i).getY())) {
                score++;
                fish.remove(i);
                return true;
            }
        }

        for (int i = 0; i < time.size(); i++) {
            if (collision.isCharacterCollisionObject(x, y, time.get(i).getImage(), time.get(i).getX(),
                    time.get(i).getY())) {
                score++;
                timeCount.countdownTime = timeCount.countdownTime + timeCount.plusSecond;
                time.remove(i);
                return true;
            }
        }
        if (collision.isCharacterCollisionObject(x, y, gate.getImage(), gate.getX(),
                gate.getY())) {
                    gate.setStep(1);
        }
        else {
            gate.setStep(0);
        }
        return false;
    }

    public int getMapWidth() {
        return bg[1].getWidth();
    }

    public int getMapHeight() {
        return bg[1].getHeight();
    }
    public BufferedImage getBackground() {
        return bg[1];
    }

}
