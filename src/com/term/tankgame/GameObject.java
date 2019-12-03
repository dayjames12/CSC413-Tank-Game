package com.term.tankgame;

import java.awt.*;

public abstract class GameObject {

    private Game game;
    private Handler handler;
    protected ID id;

    protected float x,y;

    public GameObject(Game game, Handler handler,  ID id, float x, float y) {
        this.game = game;
        this.handler = handler;
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public abstract void update();
    public abstract void drawImage(Graphics g);
    public abstract Rectangle getBounds();

    public ID getId() { return id; }
    public void setId(ID id) { this.id = id; }
    public float getX() { return x; }
    public void setX(float x) { this.x = x; }
    public float getY() { return y; }
    public void setY(float y) { this.y = y; }
}
