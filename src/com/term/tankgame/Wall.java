package com.term.tankgame;

import java.awt.*;

public class Wall extends GameObject {


    public Wall(Game game, Handler handler, ID id, float x, float y) {
        super(game, handler, id, x, y);
    }

    @Override
    public void update() {

    }

    @Override
    public void drawImage(Graphics g) {
        g.setColor(Color.green);
//        g.drawRect((int)x,(int)y,32,32);
        g.fillRect((int)x,(int)y,32,32);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }
}
