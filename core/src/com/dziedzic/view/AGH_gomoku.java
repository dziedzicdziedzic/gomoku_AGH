package com.dziedzic.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.dziedzic.controller.MinMax;
import com.dziedzic.model.Board;
import com.dziedzic.model.CustomButton;
import com.dziedzic.model.Field;

import java.util.LinkedList;

public class AGH_gomoku extends ApplicationAdapter {
    SpriteBatch batch;
    Board board;
    int boardSize = 15;
    LinkedList<CustomButton> buttonList;
    Stage stage;
    String blankPath, xPath, oPath;
    Texture blankTexture, oTexture, xTexture;
    boolean opponentTurn = false;

    @Override
    public void create() {
        batch = new SpriteBatch();
        board = new Board(boardSize);
//        board.addField(new Field(1,1,Board.State.O_TAKEN));
//        board.addField(new Field(0,1,Board.State.O_TAKEN));
//        board.addField(new Field(0,0,Board.State.O_TAKEN));
//        board.addField(new Field(4,7,Board.State.X_TAKEN));
        stage = new Stage(new ScreenViewport());

        blankPath = "/home/dziedzic/IdeaProjects/gomoku/core/src/com/dziedzic/view/assets/blank_asset.bmp";
        xPath = "/home/dziedzic/IdeaProjects/gomoku/core/src/com/dziedzic/view/assets/x_asset.bmp";
        oPath = "/home/dziedzic/IdeaProjects/gomoku/core/src/com/dziedzic/view/assets/o_asset.bmp";

        blankTexture = new Texture(blankPath);
        xTexture = new Texture("/home/dziedzic/IdeaProjects/gomoku/core/src/com/dziedzic/view/assets/x_asset.bmp");
        oTexture = new Texture("/home/dziedzic/IdeaProjects/gomoku/core/src/com/dziedzic/view/assets/o_asset.bmp");

        buttonList = new LinkedList<>();
        buttonList = generateButtonList();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        //System.out.println("XX");
        if(opponentTurn) {
            for (CustomButton btn2 : buttonList) {
                if (board.getLastAdded().getxPos() == btn2.getxPos() && board.getLastAdded().getyPos() == btn2.getyPos()) {
                    if (btn2.getState() == Board.State.BLANK){
                        System.out.println("blank");
                        btn2.setVisible(false);
                    }
                    if (btn2.getState() == Board.State.O_TAKEN){
                        System.out.println("ooo");
                        btn2.setVisible(true);
                    }
                }
            }
            opponentTurn=false;
        }
       // renderBoard();
        //batch.draw(img, 0, 0);
        batch.end();
        stage.getBatch();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        //img.dispose();
    }

    private LinkedList<CustomButton> generateButtonList() {
        LinkedList<CustomButton> buttons = new LinkedList<CustomButton>();
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {


                CustomButton btnBlank = new CustomButton(new SpriteDrawable(new Sprite(blankTexture)));
                CustomButton btnX = new CustomButton(new SpriteDrawable(new Sprite(xTexture)));
                CustomButton btnO = new CustomButton(new SpriteDrawable(new Sprite(oTexture)));


                btnBlank.setxPos(i);
                btnX.setxPos(i);
                btnO.setxPos(i);

                btnBlank.setyPos(j);
                btnX.setyPos(j);
                btnO.setyPos(j);

                btnBlank.setSize(30,30);
                btnX.setSize(30,30);
                btnO.setSize(30,30);

                btnBlank.setPosition(30*i, 570 - 30*j);
                btnX.setPosition(30*i, 570 - 30*j);
                btnO.setPosition(30*i, 570 - 30*j);

                btnBlank.setState(Board.State.BLANK);
                btnX.setState(Board.State.X_TAKEN);
                btnO.setState(Board.State.O_TAKEN);

                btnBlank.setVisible(true);
                btnX.setVisible(false);
                btnO.setVisible(false);


                btnBlank.addListener(new ClickListener() {
                    public void clicked(InputEvent event, float x, float y){
                        board.addField(new Field(btnBlank.getxPos(), btnBlank.getyPos(), Board.State.X_TAKEN));
                        btnBlank.setVisible(false);
                        btnX.setVisible(true);
                        System.out.println("in");
                        MinMax.minmax(board,0);
                        System.out.println(board.getLastAdded().getState());
                        System.out.println(board.getLastAdded().getxPos() +" " + board.getLastAdded().getyPos());
                        opponentTurn=true;
                    }
                });

                stage.addActor(btnBlank);
                stage.addActor(btnX);
                stage.addActor(btnO);
                buttons.add(btnBlank);
                buttons.add(btnX);
                buttons.add(btnO);
            }
        }
        return buttons;
    }
//
//    private void renderBoard() {
//        for(CustomButton customButton: buttonList){
//            customButton.setBackground(new SpriteDrawable(new Sprite(xTexture)));
//            customButton.setVisible(true);
//        }
//
//    }
}
//,new SpriteDrawable(new Sprite(xTexture)),new SpriteDrawable(new Sprite(xTexture))