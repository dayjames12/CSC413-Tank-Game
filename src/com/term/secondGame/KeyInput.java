package com.term.secondGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {
    private final int NUM_KEYS = 256;
    private boolean[] keys = new boolean[NUM_KEYS]; //tracks current keys pressed
    private boolean[] keysLast = new boolean[NUM_KEYS]; //tracks the last keys pressed

    public KeyInput(){ }

    public void update(){ //moves over current keys to last keys
        System.arraycopy(keys, 0, keysLast, 0, NUM_KEYS);
    }

    public boolean isKey(int keyCode){
        return keys[keyCode];
    } //if key is pressed
    public boolean isKeyUp(int keyCode){ return !keys[keyCode] && keysLast[keyCode]; } //if it is released
    public boolean isKeyDown(int keyCode){ return keys[keyCode] && !keysLast[keyCode]; } //if it is only down - useful for single presses

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) { keys[e.getKeyCode()] = true; }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}