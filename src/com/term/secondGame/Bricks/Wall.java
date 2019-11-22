package com.term.secondGame.Bricks;

import com.term.secondGame.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Wall extends GameObject {
    BufferedImage image;
    BufferedImageLoader imageLoader;

    public Wall(int x, int y, ID id, Game game, Handler handler, String imagePath) {
        super(x, y, id, game, handler, imagePath);
        imageLoader = new BufferedImageLoader();
        image = imageLoader.loadImage(this.getImagePath());
    }

    @Override
    public void update() { }

    @Override
    public void drawImage(Graphics g) {
        g.drawImage(this.image,(int)this.getX(),(int)this.getY(),null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)this.getX(), (int)this.getY(), 20, 20);
    }
}
