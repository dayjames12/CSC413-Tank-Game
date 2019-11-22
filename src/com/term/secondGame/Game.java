package com.term.secondGame;

import com.term.secondGame.Bricks.Block;
import com.term.secondGame.Bricks.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Game extends JPanel {
    public static final int SCREEN_WIDTH = 655;
    public static final int SCREEN_HEIGHT = 487;

    BufferedImage level;

    private BufferedImageLoader imageLoader; //class to load images where needed (either for objects or the level)
    private Handler handler; //keeps track of all GameObjects
    private KeyInput keyInput;

    public static void main(String[] args) {
        Game game = new Game();
        game.init();
        try {
            while (true) {
                game.handler.update();
                game.keyInput.update();
                game.repaint();
                Thread.sleep(1000 / 144);
            }
        } catch (InterruptedException ignored) {
        }
    }

    private void init() {
        JFrame frame = new JFrame("Super Rainbow Reef");

        handler = new Handler();
        imageLoader = new BufferedImageLoader();
        keyInput = new KeyInput();

        level = imageLoader.loadImage("/level1.png");
        loadLevel(level);

        frame.setLayout(new BorderLayout());
        frame.add(this);
        frame.addKeyListener(keyInput);
        frame.setSize(Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT + 30);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.requestFocus();
    }

    public void loadLevel(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = image.getRGB(x,y);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (red == 0 && green == 0 && blue == 0) { handler.addObject(new Katch(x * 20, y * 20, ID.Katch, this, handler, "/Katch.png")); }
                if (red == 255 && green == 255 && blue == 255) { handler.addObject(new Pop(x * 20, y * 20, ID.Pop, this, handler, "/Pop.png")); }
                if (red == 255 && green == 0 && blue == 0) { handler.addObject(new Wall(x * 20, y * 20, ID.Wall, this, handler, "/Wall.png")); }
                if (red == 255 && green == 255 && blue == 0) { handler.addObject(new Block(x * 20, y * 20, ID.SolidBlock, this, handler, "solid")); }
                if (red == 25 && green == 50 && blue == 75) { handler.addObject(new Block(x * 20, y * 20, ID.ColorBlock, this, handler, "blue")); }
                if (red == 50 && green == 75 && blue == 100) { handler.addObject(new Block(x * 20, y * 20, ID.ColorBlock, this, handler, "red")); }
                if (red == 75 && green == 100 && blue == 125) { handler.addObject(new Block(x * 20, y * 20, ID.ColorBlock, this, handler, "yellow")); }
                if (red == 100 && green == 125 && blue == 150) { handler.addObject(new Block(x * 20, y * 20, ID.ColorBlock, this, handler, "green")); }
                if (red == 125 && green == 150 && blue == 175) { handler.addObject(new Block(x * 20, y * 20, ID.ColorBlock, this, handler, "purple")); }
                if (red == 150 && green == 175 && blue == 200) { handler.addObject(new Block(x * 20, y * 20, ID.ColorBlock, this, handler, "white")); }
                if (red == 175 && green == 200 && blue == 225) { handler.addObject(new Block(x * 20, y * 20, ID.SplitBlock, this, handler, "split")); }
                if (red == 200 && green == 225 && blue == 250) { handler.addObject(new Block(x * 20, y * 20, ID.DoubleBlock, this, handler, "double")); }
                if (red == 225 && green == 250 && blue == 250) { handler.addObject(new Block(x * 20, y * 20, ID.LifeBlock, this, handler, "life")); }
            }
        }
    }

    public static double clamp(double var, double min, double max) { // to keep things in bound
        if (var >= max) { return max; }
        else if (var <= min) { return min; }
        else { return var; }
    }

    public KeyInput getKeyInput() { return keyInput; }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);

        BufferedImage background = imageLoader.loadImage("/Background1.bmp");
        BufferedImage world = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);

        Graphics2D buffer = background.createGraphics();
//        g2.drawImage(world,0,0,null);

        g2.drawImage(background,0,0,null); //draws the background image
        handler.drawImage(g2);
    }
}
