package main.java;

import processing.core.PApplet;
import processing.core.PImage;

public class Crab {
    private int crabPosX, crabPosY;
    public FieldState playerStatus;
    private PImage img;

    public Crab(FieldState playerStatus, int posX, int posY) {
        this.playerStatus = playerStatus;
        this.crabPosX = posX;
        this.crabPosY = posY;
        if (this.playerStatus == FieldState.GREEN) {
            img = Images.greenCrab;
        } else if (this.playerStatus == FieldState.RED) {
            img = Images.redCrab;
        } else if (this.playerStatus == FieldState.BLOCKED) {
            img = Images.blockedField;
        }
    }

    public void draw(PApplet canvas) {
        canvas.image(img, crabPosX * 45 + 88, crabPosY * 45 + 169, 45, 45);
    }

    public int getCrabPosX() {
        return crabPosX;
    }

    public int getCrabPosY() {
        return crabPosY;
    }

    public FieldState getPlayerStatus() {
        return playerStatus;
    }

    public void setCrabPosX(int crabPosX) {
        this.crabPosX = crabPosX;
    }

    public void setCrabPosY(int crabPosY) {
        this.crabPosY = crabPosY;
    }
}