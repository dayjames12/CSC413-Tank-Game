/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.term.tankgame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Game extends JPanel  {

    public static final int SCREEN_WIDTH = 1297;
    public static final int SCREEN_HEIGHT = 746;
    private Graphics2D miniBuffer, leftBuffer, rightBuffer;
    private JFrame jf;
    private KeyInput keyInput;
    private HUD hud;
    private Handler handler;
    private Camera redCam, blueCam;
    BufferedImage level;

    public static void main(String[] args) {
        Thread x;
        Game trex = new Game();
        trex.init();
        try {
            while (true) {
                trex.handler.update();
                trex.keyInput.update();
                trex.hud.update();
                trex.redCam.update(trex.handler.getGameObject(ID.RedTank));
                trex.blueCam.update(trex.handler.getGameObject(ID.BlueTank));
                trex.repaint();
                Thread.sleep(1000 / 144);
            }
        } catch (InterruptedException ignored) {
        }
    }

    private void init() {
        this.jf = new JFrame("Tank Wars");

        handler = new Handler();
        hud = new HUD(handler);
        redCam = new Camera(0,0);
        blueCam = new Camera(700,400);
        keyInput = new KeyInput();

        BufferedImageLoader loader = new BufferedImageLoader();
        level = loader.loadImage("/Tile/level1.png");
        loadLevel(level);

        this.jf.setLayout(new BorderLayout());
        this.jf.add(this);

        this.jf.addKeyListener(keyInput);
        this.jf.setSize(Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT + 30);
        this.jf.setResizable(false);
        jf.setLocationRelativeTo(null);

        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jf.setVisible(true);
    }

    public void loadLevel(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {
                int pixel = image.getRGB(xx,yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (red == 255 && green == 255 && blue == 255) { handler.addObject(new BreakWall(this, handler, ID.BreakWall, xx*32, yy*32)); }
                if (red == 255 && green != 255 && blue != 255) { handler.addObject(new Wall(this, handler, ID.Wall, xx*32, yy*32)); }
                if (red == 255 && green != 255 && blue == 255) { handler.addObject(new Supers(this, handler, ID.SuperHealth, "/Super/health.png", xx*32, yy*32)); }
                if (red != 255 && green == 255 && blue == 255) { handler.addObject(new Supers(this, handler, ID.SuperAmmo, "/Super/ammo.png", xx*32, yy*32)); }
                if (red == 255 && green == 255 && blue != 255) { handler.addObject(new Supers(this, handler, ID.SuperBullets, "/Super/superBullets.png", xx*32, yy*32)); }
                if (red != 255 && green == 255 && blue != 255) { handler.addObject(new Tank(this,handler, ID.RedTank,"/Tanks/T1-1.png",xx*32,yy*32)); }
                if (red != 255 && green != 255 && blue == 255) { handler.addObject(new Tank(this,handler, ID.BlueTank,"/Tanks/T2-1.png",xx*32,yy*32)); }
            }
        }
    }

    public static float clamp(float var, float min, float max) { // to keep things in bound
        if (var >= max) { return max; }
        else if (var <= min) { return min; }
        else { return var; }
    }

    public KeyInput getKeyInput() { return keyInput; }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        BufferedImage miniWorld = new BufferedImage(2560, 1472, BufferedImage.TYPE_INT_RGB);
        BufferedImage leftWorld = new BufferedImage(Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
        BufferedImage rightWorld = new BufferedImage(Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);

        miniBuffer = miniWorld.createGraphics();
        leftBuffer = leftWorld.createGraphics();
        rightBuffer = rightWorld.createGraphics();

        leftBuffer.translate(-redCam.getX(), -redCam.getY());
        rightBuffer.translate(-blueCam.getX(), -blueCam.getY());

        handler.drawImage(leftBuffer);
        handler.drawImage(rightBuffer);
        handler.drawImage(miniBuffer);

        hud.drawImage(leftBuffer);
        hud.drawImage(rightBuffer);

        leftBuffer.translate(redCam.getX(), redCam.getY());
        rightBuffer.translate(blueCam.getX(), blueCam.getY());


        BufferedImage left,right;
        left = leftWorld.getSubimage(0,0,SCREEN_WIDTH/2,SCREEN_HEIGHT);
        right = rightWorld.getSubimage(SCREEN_WIDTH/2 - 9,0,SCREEN_WIDTH/2,SCREEN_HEIGHT);

        g2.drawImage(left,0,0,null);
        g2.drawImage(right,SCREEN_WIDTH/2 - 9,0,null);

        g.setColor(Color.white);
        g.drawLine(SCREEN_WIDTH/2 - 9,0,SCREEN_WIDTH/2 - 9,SCREEN_WIDTH);

        //red stats here instead of in hud class because i dont want them being translated
        g.setColor(Color.gray);
        g.fillRect(40, 70, 120, 50);
        g.setColor(Color.black);
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        g.drawString("HEALTH: " + HUD.RED_HEALTH, 42, 85);
        g.drawString("AMMO: " + HUD.RED_BULLETS, 42, 100);
        g.drawString("LIVES: " + HUD.RED_LIVES, 42, 115);

        //blue stats here instead of in hud class because i dont want them being translated
        g.setColor(Color.gray);
        g.fillRect(1056, 70, 120, 50);
        g.setColor(Color.black);
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        g.drawString("HEALTH: " + HUD.BLUE_HEALTH, 1058, 85);
        g.drawString("AMMO: " + HUD.BLUE_BULLETS, 1058, 100);
        g. drawString("LIVES : " + HUD.BLUE_LIVES, 1058, 115);

        // here instead of in hud class because i dont want them being translated
        if (HUD.GAME_OVER) {
            g.setColor(Color.white);
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 72));
            g.drawString("GAME OVER", 410, 300);
        }

        g2.scale(.15,.15);
        float opacity = 0.75f;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g2.drawImage(miniWorld,2980,3450,null);
    }
}