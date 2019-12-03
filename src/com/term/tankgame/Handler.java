package com.term.tankgame;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> objects = new LinkedList<>();

    void update() {
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).update();
        }
    }

    public void drawImage(Graphics g) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);
            tempObject.drawImage(g);
        }
    }

    public void addObject(GameObject object) {
        this.objects.add(object);
    }

    public void removeObject(GameObject object) {
        this.objects.remove(object);
    }

    public GameObject getGameObject(ID id){
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getId() == id) {
                return objects.get(i);
            }
        }
        return null;
    }
}
