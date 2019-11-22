package com.term.secondGame;

import java.awt.*;

public abstract class GameObject {
    private Handler handler;

    private double x, y;
    private String imagePath;
    private ID id;
    private Game game;


    public GameObject(double x, double y, ID id, Game game, Handler handler, String imagePath) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.game = game;
        this.handler = handler;
        this.imagePath = imagePath;
    }

    public abstract void update();
    public abstract void drawImage(Graphics g);
    public abstract Rectangle getBounds();


    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    public ID getId() { return id; }
    public void setId(ID id) { this.id = id; }
    public Game getGame() { return this.game; }
    public Handler getHandler() { return this.handler; }

}
