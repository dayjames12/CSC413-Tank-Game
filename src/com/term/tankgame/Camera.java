package com.term.tankgame;

public class Camera {

    private float x, y;

    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void update(GameObject object) {
        if (object.getId() == ID.RedTank) {
            x += ((object.getX() - x) - 352) * 0.05f;
            y += ((object.getY() - y) - 320) * 0.05f;

            if (x <= 0) x = 0;
            if (x >= Game.SCREEN_WIDTH + 625) x = Game.SCREEN_WIDTH + 625;
            if (y <= 0) y = 0;
            if (y >= Game.SCREEN_HEIGHT - 9) y = Game.SCREEN_HEIGHT - 9;
        }
        if (object.getId() == ID.BlueTank) {
            x += ((object.getX() - x) - 960) * 0.05f;
            y += ((object.getY() - y) - 384) * 0.05f;

            if (x <= -641) x = -641;
            if (x >= Game.SCREEN_WIDTH  - 17) x = Game.SCREEN_WIDTH - 17;
            if (y <= 0) y = 0;
            if (y >= Game.SCREEN_HEIGHT - 10) y = Game.SCREEN_HEIGHT - 10;
        }
    }

    public float getX() { return x; }
    public void setX(float x) { this.x = x; }
    public float getY() { return y; }
    public void setY(float y) { this.y = y; }
}
