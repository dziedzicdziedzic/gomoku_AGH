package com.dziedzic.controller;

import com.dziedzic.model.Board;
import com.dziedzic.model.Field;

import java.util.LinkedList;
import java.util.concurrent.RecursiveTask;


public class MinMax extends RecursiveTask<Double> {
    public final static int TURNS = 2;

    private Board board;
    private int level;


    public MinMax(Board board, int level) {
        this.board = board;
        this.level = level;
    }

    public static double minmax(Board b, int level){

        Field bestField = new Field();
        double lowestScore = Double.POSITIVE_INFINITY;
        double highestScore = Double.NEGATIVE_INFINITY;


        if (level==TURNS){
            return b.heuristics();
        }
        LinkedList<Field> getMoves = getMoves(b);
        for(Field f: getMoves){
            Board clonedBoard = b.deepCopy();
            f.setState(level%2==0? Board.State.O_TAKEN: Board.State.X_TAKEN);
            clonedBoard.addField(f);

            double score = minmax(clonedBoard, level +1);
            if(level%2==0){
                if (highestScore < score){
                    highestScore = score;
                    bestField = f;
                }
            }
            else{
                if(lowestScore > score){
                    lowestScore = score;
                    bestField = f;
                }
            }
        }
        b.addField(bestField);
        return level%2==0 ? highestScore:lowestScore;
    }

    public void performCompute() {
        this.compute();
    }

    @Override
    protected Double compute() {

        Field bestField = new Field();
        double lowestScore = Double.POSITIVE_INFINITY;
        double highestScore = Double.NEGATIVE_INFINITY;


        LinkedList<MinMax> subtasks = new LinkedList<>();
        LinkedList<Field> getMoves = getMoves(board);
        for (Field f : getMoves) {
            Board clonedBoard = board.deepCopy();
            f.setState(level % 2 == 0 ? Board.State.O_TAKEN : Board.State.X_TAKEN);
            clonedBoard.addField(f);
            MinMax newMinMax = new MinMax(clonedBoard, level + 1);
            subtasks.add(newMinMax);
            for (MinMax m : subtasks) {
                if (m.level == TURNS) {
                    return m.board.heuristics();
                }
                m.fork();
            }
        }
        for (MinMax m : subtasks) {

            double score = m.join();
            if (level % 2 == 0) {
                if (highestScore < score) {
                    highestScore = score;
                    bestField = m.board.getLastAdded();
                }
            } else {
                if (lowestScore > score) {
                    lowestScore = score;
                    bestField = m.board.getLastAdded();
                }
            }
        }
        board.addField(bestField);
        return level % 2 == 0 ? highestScore : lowestScore;
    }





    public static LinkedList<Field> getMoves(Board board){
        LinkedList<Field> moves = new LinkedList<>();
        for(Field f: board.getTaken()){
            int x = f.getxPos();
            int y = f.getyPos();
            for(int i =-1; i<=1; i++){
                for(int j=-1; j<=1; j++){
                    if(i==0 && j==0) continue;
                    if (x+i>=0 && y+j>=0 && x+i< board.getSize() && y+j<board.getSize()) {
                        if (board.getBoard()[x + i][y + j] == null) {
                            Field added = new Field(x+i, y+j, Board.State.BLANK);
                            if (!moves.contains(added)) {
                                moves.add(added);
                            }
                        }
                    }
                }
            }
        }
        return  moves;
    }
}
