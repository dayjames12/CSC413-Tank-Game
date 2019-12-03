package com.term.tankgame;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Bullets extends GameObject {
    private Game game;
    private Handler handler;
    private BufferedImage image;

    private float velX, velY;

    private float angle;
    private final int R = 3;
    private int bulletSpeed = 3;


    public Bullets(Game game, Handler handler, ID id, String imagePath, float x, float y, float velX, float velY, float angle) {
        super(game, handler, id, x, y);
        this.game = game;
        this.handler = handler;

        this.velX = (int) Math.round(R * Math.cos(Math.toRadians(angle))) * bulletSpeed;
        this.velY = (int) Math.round(R * Math.sin(Math.toRadians(angle))) * bulletSpeed;
        this.angle = angle;

        BufferedImageLoader loader = new BufferedImageLoader();
        this.image = loader.loadImage(imagePath);
    }

    public void update() {
        x += velX;
        y += velY;

        collision();
    }

    public void collision() {
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);

            //for walls
            if (tempObject.getId() == ID.Wall) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(this);
                }
            }
            if (tempObject.getId() == ID.BreakWall) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(this);
                    handler.removeObject(tempObject);
                }
            }
        }
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,12,12);
    }

    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(this.angle), this.image.getWidth() / 2.0, this.image.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.image, rotation,null);
    }
}
