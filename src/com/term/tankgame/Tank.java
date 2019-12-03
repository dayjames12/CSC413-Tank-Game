package com.term.tankgame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Tank extends GameObject {
    private Handler handler;
    private Game game;
    private BufferedImage image;
    private int spawnX, spawnY;

    private float velX, velY;

    private float angle;
    private final int R = 3;
    private final int ROTATION_SPEED = 3;

    private boolean redSuperBullets;
    private boolean blueSuperBullets;



    public Tank(Game game, Handler handler, ID id, String imagePath, float x, float y) {
        super(game, handler, id, x, y);
        this.handler = handler;
        this.game = game;
        this.spawnX = (int)x;
        this.spawnY = (int)y;
        BufferedImageLoader loader = new BufferedImageLoader();
        this.image = loader.loadImage(imagePath);
        angle -= 90;
    }

    @Override
    public void update() {
        if (id == ID.RedTank) {
            if (game.getKeyInput().isKey(KeyEvent.VK_W)) { moveForwards(); }
            if (game.getKeyInput().isKey(KeyEvent.VK_A)) { rotateLeft(); }
            if (game.getKeyInput().isKey(KeyEvent.VK_S)) { moveBackwards(); }
            if (game.getKeyInput().isKey(KeyEvent.VK_D)) { rotateRight(); }
            if (game.getKeyInput().isKeyDown(KeyEvent.VK_F)) { shoot(); }
        }

        if (id == ID.BlueTank) {
            if (game.getKeyInput().isKey(KeyEvent.VK_UP)) { moveForwards(); }
            if (game.getKeyInput().isKey(KeyEvent.VK_LEFT)) { rotateLeft(); }
            if (game.getKeyInput().isKey(KeyEvent.VK_DOWN)) { moveBackwards(); }
            if (game.getKeyInput().isKey(KeyEvent.VK_RIGHT)) { rotateRight(); }
            if (game.getKeyInput().isKeyDown(KeyEvent.VK_CONTROL)) { shoot(); }
        }
        if (game.getKeyInput().isKey(KeyEvent.VK_ESCAPE)) System.exit(1);
        collision();
        x = Game.clamp(x, 50, 2470);
        y = Game.clamp(y, 35, 1390);

        if (HUD.RED_HEALTH == 0){
            x = spawnX;
            y = spawnY;
            HUD.RED_LIVES--;
            if (HUD.RED_LIVES == 0) {
                HUD.GAME_OVER = true;
            }
            redSuperBullets = false;
            HUD.RED_HEALTH = 100;
        }
        if (HUD.BLUE_HEALTH == 0){
            x = spawnX;
            y = spawnY;
            HUD.BLUE_LIVES--;
            if (HUD.BLUE_LIVES == 0) {
                HUD.GAME_OVER = true;
            }
            blueSuperBullets = false;
            HUD.BLUE_HEALTH = 100;
        }
    }

    public void moveForwards() {
        velX = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        velY = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += velX;
        y += velY;
    }

    public void moveBackwards() {
        velX = (int) Math.round(R * Math.cos(Math.toRadians(angle))) * -1;
        velY = (int) Math.round(R * Math.sin(Math.toRadians(angle))) * -1;
        x += velX;
        y += velY;
    }

    public void rotateLeft() {
        this.angle -= ROTATION_SPEED;
        if (angle > 360) angle = 0;
        if (angle < -360) angle = 0;
        velX = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        velY = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
    }

    public void rotateRight() {
        this.angle += ROTATION_SPEED;
        if (angle > 360) angle = 0;
        if (angle < -360) angle = 0;
        velX = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        velY = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
    }

    public void shoot() {
        if (HUD.RED_BULLETS > 0) {
            if (id == ID.RedTank) {
                handler.addObject(new Bullets(game, handler, ID.RedBullet, "/Bullets/T1B1.png", x + 17, y + 17, velX, velY, angle));
                if (redSuperBullets) {
                    handler.addObject(new Bullets(game, handler, ID.RedBullet, "/Bullets/T1B1.png", x + 17, y + 17, velX, velY, angle + 15));
                    handler.addObject(new Bullets(game, handler, ID.RedBullet, "/Bullets/T1B1.png", x + 17, y + 17, velX, velY, angle - 15));
                }
                HUD.RED_BULLETS--;
            }
        }
        if (HUD.BLUE_BULLETS > 0) {
            if (id == ID.BlueTank) {
                handler.addObject(new Bullets(game, handler, ID.BlueBullet, "/Bullets/T2B1.png", x + 17, y + 17, velX, velY, angle));
                if (blueSuperBullets) {
                    handler.addObject(new Bullets(game, handler, ID.BlueBullet, "/Bullets/T2B1.png", x + 17, y + 17, velX, velY, angle + 15));
                    handler.addObject(new Bullets(game, handler, ID.BlueBullet, "/Bullets/T2B1.png", x + 17, y + 17, velX, velY, angle - 15));
                }
                HUD.BLUE_BULLETS--;
            }
        }
        if (HUD.RED_BULLETS == 0 && HUD. BLUE_BULLETS == 0) {
            HUD.GAME_OVER = true;
        }
    }

    private void collision(){

        //for tanks collisions with each other
        if (handler.getGameObject(ID.RedTank).getBounds().intersects(handler.getGameObject(ID.BlueTank).getBounds())) {
            if ( x >= getBounds().x || y >= getBounds().y || (x <= getBounds().x + 50) || (y <= getBounds().y + 50)) {
                x += velX * -1;
                y += velY * -1;
            }
        }

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);

            //for walls
            if (tempObject.getId() == ID.Wall || tempObject.getId() == ID.BreakWall) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    x += velX * -1;
                    y += velY * -1;
                }
            }

            //for super health
            if (tempObject.getId() == ID.SuperHealth) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    if (this.getId() == ID.RedTank) {
                        HUD.RED_HEALTH += 50;
                    }
                    if (this.getId() == ID.BlueTank) {
                        HUD.BLUE_HEALTH += 50;
                    }
                    handler.removeObject(tempObject);
                }
            }

            //for super ammo
            if (tempObject.getId() == ID.SuperAmmo) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    if (this.getId() == ID.RedTank) {
                        HUD.RED_BULLETS += 75;
                    }
                    if (this.getId() == ID.BlueTank) {
                        HUD.BLUE_BULLETS += 75;
                    }
                    handler.removeObject(tempObject);
                }
            }

            //for super ammo
            if (tempObject.getId() == ID.SuperBullets) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    if (this.getId() == ID.RedTank) {
                        redSuperBullets = true;
                    }
                    if (this.getId() == ID.BlueTank) {
                        blueSuperBullets = true;
                    }
                    handler.removeObject(tempObject);
                }
            }

            // for blue bullets hitting red tank - also deletes the bullet object
            if (tempObject.getId() == ID.BlueBullet && this.getId() == ID.RedTank) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.RED_HEALTH -= 10;
                    handler.removeObject(tempObject);
                }
            }

            // for red bullets hitting blue tank - also deletes the bullet object after
            if (tempObject.getId() == ID.RedBullet && this.getId() ==  ID.BlueTank) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.BLUE_HEALTH -= 10;
                    handler.removeObject(tempObject);
                }
            }
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,50,50);
    }

    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        if (id == ID.BlueTank) {
            rotation.rotate(Math.toRadians(angle), this.image.getWidth() / 2.0, this.image.getHeight() / 2.0);
        }
        if (id == ID.RedTank) {
            rotation.rotate(Math.toRadians(angle + 180), this.image.getWidth() / 2.0, this.image.getHeight() / 2.0);
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.image, rotation, null);
    }
}
