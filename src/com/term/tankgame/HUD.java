package com.term.tankgame;

import java.awt.*;

public class HUD {

    private Handler handler;

    public static boolean GAME_OVER = false;
    public static int RED_HEALTH = 100;
    public static int BLUE_HEALTH = 100;
    public static int RED_BULLETS = 50;
    public static int BLUE_BULLETS = 50;
    public static int RED_LIVES = 3;
    public static int BLUE_LIVES = 3;

    public HUD(Handler handler){
        this.handler = handler;
    }

    public void update() {
        RED_HEALTH = (int)Game.clamp(RED_HEALTH,0,100);
        BLUE_HEALTH = (int)Game.clamp(BLUE_HEALTH,0,100);
        RED_BULLETS = (int)Game.clamp(RED_BULLETS,0,500);
        BLUE_BULLETS = (int)Game.clamp(BLUE_BULLETS,0,500);
        RED_LIVES = (int)Game.clamp(RED_LIVES,0,3);
        BLUE_LIVES = (int)Game.clamp(BLUE_LIVES,0,3);
    }

    public void drawImage(Graphics g) {
        GameObject redTank = handler.getGameObject(ID.RedTank);
        GameObject blueTank = handler.getGameObject(ID.BlueTank);

        //red health
        g.setColor(Color.gray);
        g.fillRect((int)redTank.getX(),(int)redTank.getY() + 50,50,10);
        g.setColor(Color.getHSBColor( (1f * RED_HEALTH) / 360, 1f, 1f));
        g.fillRect((int)redTank.getX(),(int)redTank.getY() + 50, RED_HEALTH/2, 10);

        //blue health
        g.fillRect((int)blueTank.getX(),(int)blueTank.getY() + 50,50,10);
        g.setColor(Color.getHSBColor( (1f * BLUE_HEALTH) / 360, 1f, 1f));
        g.fillRect((int)blueTank.getX(),(int)blueTank.getY() + 50, BLUE_HEALTH/2, 10);


    }
}
