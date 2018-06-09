package com.dziedzic.model;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class CustomButton extends ImageButton {

    private Board.State state;
    private int xPos;
    private int yPos;

    public CustomButton(Skin skin) {
        super(skin);
    }

    public CustomButton(Skin skin, String styleName) {
        super(skin, styleName);
    }

    public CustomButton(ImageButtonStyle style) {
        super(style);
    }

    public CustomButton(Drawable imageUp) {
        super(imageUp);
    }

    public CustomButton(Drawable imageUp, Drawable imageDown) {
        super(imageUp, imageDown);
    }

    public CustomButton(Drawable imageUp, Drawable imageDown, Drawable imageChecked) {
        super(imageUp, imageDown, imageChecked);
    }


    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public Board.State getState() {
        return state;
    }

    public void setState(Board.State state) {
        this.state = state;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

}