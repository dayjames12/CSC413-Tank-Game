package com.term.tankgame;

import java.awt.*;

public class BreakWall extends GameObject {

    private Handler handler;

    public BreakWall(Game game, Handler handler, ID id, float x, float y) {
        super(game, handler, id, x, y);
        this.handler = handler;
    }

    @Override
    public void update() {

    }

    @Override
    public void drawImage(Graphics g) {
        g.setColor(Color.red);
//        g.drawRect((int)x,(int)y,32,32);
        g.fillRect((int)x,(int)y,32,32);
    }

    public void collision() {
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }
}
