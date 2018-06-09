//package com.dziedzic.view;
//
//import com.dziedzic.model.Board;
//import controller.BoardController;
//import controller.MinMax;
//import javafx.application.Application;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.stage.Stage;
//import model.Board;
//import model.Field;
//import model.CustomButton;
//
//
//public class BoardView extends Application {
//
////    public Scene printBoard(Board b){
////
////        Group root = new Group();
////
////        for(int i=0; i<b.getSize(); i++){
////            for (int j = 0; j<b.getSize(); j++){
////                CustomButton btn = new CustomButton();
////                btn.setScaleX(0.1);
////                btn.setScaleY(0.1);
////                btn.setTranslateX(i*30.0d);
////                btn.setTranslateY(j*30.0d);
////                btn.setPrefHeight(10);
////                btn.setPrefWidth(10);
////
////                btn.setxPos(i);
////                btn.setyPos(j);
////
////                btn.setOnAction(evt -> {
////                    System.out.println("click happening");
////                    b.addField(new Field(btn.getxPos(), btn.getyPos(), Board.State.X_TAKEN));
////                    b.checkForWin(b.getLastAdded());
////                    if (Board.isGameOver()){
////                        System.out.println("end");
////                    }
////                    MinMax.minmax(b,0);
////
////                });
////
////
////
////                final ImageView iv = new ImageView();
////                //this.getChildren().add(iv);
////
////                if (b.getBoard()[i][j]==null){
////                    iv.setImage(new Image("view/assets/blank_asset.bmp"));
////                }
////                else {
////                    switch (b.getBoard()[i][j].getState()) {
////                        case BLANK:
////                            iv.setImage(new Image("view/assets/blank_asset.bmp"));
////                            break;
////                        case X_TAKEN:
////                            iv.setImage(new Image("view/assets/o_asset.bmp"));
////                            break;
////                        case O_TAKEN:
////                            iv.setImage(new Image("view/assets/x_asset.bmp"));
////                            break;
////                    }
////                }
////                btn.setGraphic(iv);
////                root.getChildren().add(btn);
////            }
////        }
////
////        return new Scene(root,800, 600);
////
////    }
////
////    private void handleClick(){
////
////    }
//
//    @Override
//    public void start(Stage stage) throws Exception {
//        BoardView bv = new BoardView();
//        Board b = new Board(15);
//        BoardController bc = new BoardController();
//
//        b.addField(new Field(4,4,Board.State.X_TAKEN));
//        b.addField(new Field(4,5,Board.State.X_TAKEN));
//        b.addField(new Field(4,6,Board.State.X_TAKEN));
//        b.addField(new Field(4,7,Board.State.X_TAKEN));
//
//        Group root = new Group();
//
//        for(int i=0; i<b.getSize(); i++){
//            for (int j = 0; j<b.getSize(); j++){
//                CustomButton btn = new CustomButton();
//                btn.setScaleX(0.1);
//                btn.setScaleY(0.1);
//                btn.setTranslateX(i*30.0d);
//                btn.setTranslateY(j*30.0d);
//                btn.setPrefHeight(10);
//                btn.setPrefWidth(10);
//
//                btn.setxPos(i);
//                btn.setyPos(j);
//
//                btn.setOnMouseClicked(evt -> {
//                    System.out.println("click happening");
//
////                    b.addField(new Field(btn.getxPos(), btn.getyPos(), Board.State.X_TAKEN));
////                    b.checkForWin(b.getLastAdded());
////                    if (Board.isGameOver()){
////                        System.out.println("end");
////                    }
////                    MinMax.minmax(b,0);
//
//                });
//
//
//
//                final ImageView iv = new ImageView();
//
//
//                //this.getChildren().add(iv);
//
//                if (b.getBoard()[i][j]==null){
//                    iv.setImage(new Image("view/assets/blank_asset.bmp"));
//                }
//                else {
//                    switch (b.getBoard()[i][j].getState()) {
//                        case BLANK:
//                            iv.setImage(new Image("view/assets/blank_asset.bmp"));
//                            break;
//                        case X_TAKEN:
//                            iv.setImage(new Image("view/assets/x_asset.bmp"));
//                            break;
//                        case O_TAKEN:
//                            iv.setImage(new Image("view/assets/o_asset.bmp"));
//                            break;
//                    }
//                }
//                btn.setGraphic(iv);
//                root.getChildren().add(btn);
//            }
//        }
//
//        stage.setTitle("Dziedzic gomoku");
//        stage.setScene(new Scene(root, 800, 600));
//        stage.show();
//
//        //while(!Board.isGameOver()){
//
////            bc.playerMove(b);
////            b.checkForWin(b.getLastAdded());
////            if(Board.isGameOver()){
////                break;
////            }
////            MinMax.minmax(b,0);
////            System.out.println("\n"  + b.evaluate(Board.State.O_TAKEN) +" " + b.evaluate(Board.State.X_TAKEN));
////            b.checkForWin(b.getLastAdded());
//        //}
//        //bv.printBoard(b);
//        //System.out.println("Game Over");
//    }
//}
