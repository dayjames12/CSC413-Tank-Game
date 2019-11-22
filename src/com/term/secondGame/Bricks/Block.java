package com.term.secondGame.Bricks;

import com.term.secondGame.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Block extends GameObject {
    BufferedImage image;
    BufferedImageLoader imageLoader;

    //imagePath for blocks are strings that the switch case uses to get the needed image path
    public Block(int x, int y, ID id, Game game, Handler handler, String imagePath) {
        super(x, y, id, game, handler, imagePath);

        switch (imagePath){
            case "purple": setImagePath("/Block1.gif"); break;
            case "yellow": setImagePath("/Block2.gif"); break;
            case "red": setImagePath("/Block3.gif"); break;
            case "green": setImagePath("/Block4.gif"); break;
            case "teal": setImagePath("/Block5.gif"); break;
            case "blue": setImagePath("/Block6.gif"); break;
            case "white": setImagePath("/Block7.gif"); break;
            case "solid": setImagePath("/Block_solid.gif"); break;
            case "life": setImagePath("/Block_life.gif"); break;
            case "double": setImagePath("/Block_double.gif"); break;
            case "split": setImagePath("/Block_split.gif");


        }
        imageLoader = new BufferedImageLoader();
        image = imageLoader.loadImage(this.getImagePath());
    }

    @Override
    public void update() {

    }

    @Override
    public void drawImage(Graphics g) {
        g.drawImage(this.image,(int)this.getX(),(int)this.getY(),null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)this.getX(), (int)this.getY(), 40, 20);
    }
}
