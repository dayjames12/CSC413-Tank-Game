package com.term.secondGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Katch extends GameObject {
    private BufferedImage image;
    private BufferedImageLoader imageLoader;

    double x;
    private double velX;

    //imagepath is the direct imagepath to image
    public Katch(double x, double y, ID id, Game game, Handler handler, String imagePath) {
        super(x, y, id, game, handler, imagePath);

        this.x = x;
        this.velX = 1.5;

        imageLoader = new BufferedImageLoader();
        this.image = imageLoader.loadImage(imagePath);
    }

    @Override
    public void update() {
        if (this.getGame().getKeyInput().isKey(KeyEvent.VK_ESCAPE)) { System.exit(3); }

        if (this.getGame().getKeyInput().isKey(KeyEvent.VK_LEFT) || this.getGame().getKeyInput().isKey(KeyEvent.VK_A)) {
            this.setX(this.getX() - velX);
        }
        if (this.getGame().getKeyInput().isKey(KeyEvent.VK_RIGHT) || this.getGame().getKeyInput().isKey(KeyEvent.VK_D)) {
            this.setX(this.getX() + velX);
        }
        this.setX(Game.clamp(this.getX(),21,541));
    }

    @Override
    public void drawImage(Graphics g) {
        g.drawImage(this.image,(int)this.getX(),(int)this.getY(),null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)this.getX(),(int)this.getY(),40,20);
    }
}
