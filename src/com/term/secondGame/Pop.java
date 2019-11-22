package com.term.secondGame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Pop extends GameObject {
    private BufferedImage image;
    private BufferedImageLoader imageLoader;

    private double x, y;
    private double spawnX, spawnY;
    private double velX, velY;
    private boolean start = false;

    public Pop(double x, double y, ID id, Game game, Handler handler, String imagePath) {
        super(x, y, id, game, handler, imagePath);

        this.x = x;
        this.y = y;
        this.spawnX = x;
        this.spawnY = y;

        imageLoader = new BufferedImageLoader();
        this.image = imageLoader.loadImage(imagePath);
    }

    @Override
    public void update() {
        if (this.getGame().getKeyInput().isKey(KeyEvent.VK_SPACE) && !start) {
            start = true;
            velX = 2;
            velY = -2;
        }

        if (x < 22  || x > 588) { velX *= -1; }
        if (y < 22) { velY *= -1; }
        if (y > 487) {
            x = spawnX;
            y = spawnY;
            velX = 0;
            velY = 0;
            start = false;
        }
        collision();
        x += velX;
        y += velY;
    }

    private void collision() {
        for (int i = 0; i < this.getHandler().objectLinkedList.size(); i++) {
            GameObject tempObject = this.getHandler().objectLinkedList.get(i);
            if (tempObject.getId() != ID.Pop && this.getBounds().intersects(tempObject.getBounds())){
                if (tempObject.getId() == ID.ColorBlock && this.getBounds().intersects(tempObject.getBounds())){
                    this.getHandler().removeObject(tempObject);
                }
                velY *= -1; break;
            }
        }
    }

    @Override
    public void drawImage(Graphics g) {
        g.drawImage(this.image,(int)x,(int)y,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,35,35);
    }
}
