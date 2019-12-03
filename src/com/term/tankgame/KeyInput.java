package com.term.tankgame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {
    private final int NUM_KEYS = 256;
    private boolean[] keys = new boolean[NUM_KEYS];
    private boolean[] keysLast = new boolean[NUM_KEYS];


    public KeyInput(){
    }

    public void update(){
        for (int i = 0; i < NUM_KEYS; i++){
            keysLast[i] = keys[i];
        }
    }

    public boolean isKey(int keyCode){
        return keys[keyCode];
    }
    public boolean isKeyUp(int keyCode){ return !keys[keyCode] && keysLast[keyCode]; }
    public boolean isKeyDown(int keyCode){ return keys[keyCode] && !keysLast[keyCode]; }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) { keys[e.getKeyCode()] = true; }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
