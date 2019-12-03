package com.term.tankgame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Supers extends GameObject{

    private BufferedImage image;

    public Supers(Game game, Handler handler, ID id, String imagePath, float x, float y) {
        super(game, handler, id, x, y);

        BufferedImageLoader loader = new BufferedImageLoader();
        this.image = loader.loadImage(imagePath);
    }

    @Override
    public void update() {

    }

    @Override
    public void drawImage(Graphics g) {
        g.drawImage(image, (int)getX(),(int)getY(),null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }
}
