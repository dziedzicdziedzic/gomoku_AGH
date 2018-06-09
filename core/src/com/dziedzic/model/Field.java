package com.dziedzic.model;

public class Field {
    private int xPos;
    private int yPos;
    private Board.State state;

    public Field() {
    }

    public Field(int xPos, int yPos, Board.State state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.state = state;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public Board.State getState() {
        return state;
    }

    public void setState(Board.State state) {
        this.state = state;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Field)) {
            return false;
        }
        Field other = (Field) obj;
        if(this.getxPos() == other.getxPos() && this.getyPos() == other.getyPos()) return true;
        return false;
    }
}
