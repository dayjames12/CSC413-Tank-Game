package com.term.secondGame;

import java.awt.*;
import java.util.LinkedList;

public class Handler {
    LinkedList<GameObject> objectLinkedList = new LinkedList<>(); //linked list for game objects

    void update() { //runs through linked list and updates each object
        for (int i = 0; i < objectLinkedList.size(); i++) {
            objectLinkedList.get(i).update();
        }
    }

    public void drawImage(Graphics g) {
        for (int i = 0; i < objectLinkedList.size(); i++) {
            GameObject tempObject = objectLinkedList.get(i);
            tempObject.drawImage(g);
        }
}
    public void addObject(GameObject object) {
        this.objectLinkedList.add(object);
    } //add a new object to game

    public void removeObject(GameObject object) {
        this.objectLinkedList.remove(object);
    } //delete an object from game

    public GameObject getGameObject(ID id){
        for (int i = 0; i < objectLinkedList.size(); i++) {
            if (objectLinkedList.get(i).getId() == id) {
                return objectLinkedList.get(i);
            }
        }
        return null;
    }
}
