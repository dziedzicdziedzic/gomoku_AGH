package com.dziedzic.model;


import java.util.LinkedList;

public class Board {

    public enum State{
        BLANK, X_TAKEN, O_TAKEN
    }

    private Field[][] board;
    private LinkedList<Field> taken;
    private Field lastAdded;
    private static boolean gameOver = false;
    private int size;

    public Board(int size){
        board = new Field[size][size];
        this.size = size;
        taken = new LinkedList<>();
    }

    public void addField(Field f){
        board[f.getxPos()][f.getyPos()] = f;
        if(!taken.contains(f)) {
            taken.add(f);
        }
        lastAdded = f;
    }

    public void removeField(Field f){
        board[f.getxPos()][f.getyPos()] = null;
        taken.remove(f);
    }


    public Field[][] getBoard() {
        return board;
    }

    public void setBoard(Field[][] board) {
        this.board = board;
    }

    public LinkedList<Field> getTaken() {
        return taken;
    }

    public void setTaken(LinkedList<Field> taken) {
        this.taken = taken;
    }

    public Field getLastAdded() {
        return lastAdded;
    }

    public void setLastAdded(Field lastAdded) {
        this.lastAdded = lastAdded;
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public static void setGameOver(boolean gameOver) {
        Board.gameOver = gameOver;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void checkForWin(Field f) {
        int xCoord = f.getxPos();
        int yCoord = f.getyPos();
        State value = f.getState();


        for(int i=0; i<5; ++i) {

            //vertical

            if (xCoord - 4 + i >= 0 && xCoord + i < size) {


                if (board[xCoord - 4 + i][yCoord] != null & board[xCoord - 3 + i][yCoord] != null & board[xCoord - 2 + i][yCoord] != null
                        & board[xCoord - 1 + i][yCoord] != null & board[xCoord + i][yCoord] != null) {
                    if (board[xCoord - 4 + i][yCoord].getState() == value & board[xCoord - 3 + i][yCoord].getState() == value & board[xCoord - 2 + i][yCoord].getState() == value
                            & board[xCoord - 1 + i][yCoord].getState() == value & board[xCoord + i][yCoord].getState() == value) {
                        gameOver = true;
                        break;
                    }
                }
            }

            //horizontal

            if (yCoord - 4 + i >= 0 && yCoord + i < size){

                if (board[xCoord][yCoord - 4 + i] != null & board[xCoord][yCoord - 3 + i] != null & board[xCoord][yCoord - 2 + i] != null
                        & board[xCoord][yCoord - 1 + i] != null & board[xCoord][yCoord + i] != null) {
                    if (board[xCoord][yCoord - 4 + i].getState() == value & board[xCoord][yCoord - 3 + i].getState() == value & board[xCoord][yCoord - 2 + i].getState() == value
                            & board[xCoord][yCoord - 1 + i].getState() == value & board[xCoord][yCoord + i].getState() == value) {
                        gameOver = true;
                        break;
                    }
                }
            }

            //left diagonal

            if (xCoord - 4 + i >= 0 && xCoord + i < size && yCoord - 4 + i >= 0 && yCoord + i < size){

                if (board[xCoord - 4 + i][yCoord - 4 + i] != null & board[xCoord - 3 + i][yCoord - 3 + i] != null & board[xCoord - 2 + i][yCoord - 2 + i] != null
                        & board[xCoord - 1 + i][yCoord - 1 + i] != null & board[xCoord + i][yCoord + i] != null) {
                    if (board[xCoord - 4 + i][yCoord - 4 + i].getState() == value & board[xCoord - 3 + i][yCoord - 3 + i].getState() == value & board[xCoord - 2 + i][yCoord - 2 + i].getState() == value
                            & board[xCoord - 1 + i][yCoord - 1 + i].getState() == value & board[xCoord + i][yCoord + i].getState() == value) {
                        gameOver = true;
                        break;
                    }
                }
            }

            //right diagonal

            if(xCoord+4-i<size && xCoord-i>=0 && yCoord - 4 + i >= 0 && yCoord + i < size){

                if (board[xCoord + 4 - i][yCoord - 4 + i] != null & board[xCoord + 3 - i][yCoord - 3 + i] != null & board[xCoord + 2 - i][yCoord - 2 + i] != null
                        & board[xCoord + 1 - i][yCoord - 1 + i] != null & board[xCoord - i][yCoord + i] != null) {
                    if (board[xCoord + 4 - i][yCoord - 4 + i].getState() == value & board[xCoord + 3 - i][yCoord - 3 + i].getState() == value & board[xCoord + 2 - i][yCoord - 2 + i].getState() == value
                            & board[xCoord + 1 - i][yCoord - 1 + i].getState() == value & board[xCoord - i][yCoord + i].getState() == value) {
                        gameOver = true;
                        break;
                    }
                }
            }
        }
    }

    public double heuristics(){
        return evaluate(State.O_TAKEN) - evaluate(State.X_TAKEN);
    }

    
    
    
    public double evaluate(State which){
        double score = 0;

        for (Field checkedNode : this.getTaken()) {
            int xCoord = checkedNode.getxPos();
            int yCoord = checkedNode.getyPos();

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (xCoord + i >= 0 && xCoord + i < this.getSize() && yCoord + j >= 0 && yCoord + j < this.getSize()) {

                        if (i == 0 && j == 0) continue;
                        if (this.getBoard()[xCoord + i][yCoord + j] != null) {
                            if (this.getBoard()[xCoord + i][yCoord + j].getState().equals(which)) {
                                score += 20;
                            } else if (this.getBoard()[xCoord + i][yCoord + j].getState().equals(State.BLANK)) {
                                score -=5;
                            } else {
                                score +=10;
                            }
                        }
                    }
                }
            }

            if(this.getBoard()[xCoord][yCoord].getState()==which){

                if (xCoord - 1 >= 0 && xCoord + 1 < this.getSize() && yCoord - 1 >= 0 && yCoord + 1 < this.getSize()) {
                    //vertically
                    if (this.getBoard()[xCoord - 1][yCoord] != null && this.getBoard()[xCoord + 1][yCoord] != null) {
                        if (this.getBoard()[xCoord - 1][yCoord].getState().equals(which) && this.getBoard()[xCoord + 1][yCoord].getState().equals(which)) {
                            score += 100;
                            if (xCoord - 2 >= 0) {
                                if (this.getBoard()[xCoord - 2][yCoord] != null) {
                                    if (this.getBoard()[xCoord - 2][yCoord].getState().equals(which)) {
                                        score += 200;
                                        if (xCoord - 3 >= 0) {
                                            if (this.getBoard()[xCoord - 3][yCoord] != null) {
                                                if (this.getBoard()[xCoord - 3][yCoord].getState().equals(which)) {
                                                    score += 5000;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (xCoord + 2 < size) {
                                if (this.getBoard()[xCoord + 2][yCoord] != null) {
                                    if (this.getBoard()[xCoord + 2][yCoord].getState().equals(which)) {
                                        score += 200;
                                        if (xCoord + 3 < size) {
                                            if (this.getBoard()[xCoord + 3][yCoord] != null) {
                                                if (this.getBoard()[xCoord + 3][yCoord].getState().equals(which)) {
                                                    score += 5000;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    //horizontally
                    if (this.getBoard()[xCoord][yCoord - 1] != null && this.getBoard()[xCoord][yCoord + 1] != null) {
                        if (checkedNode.getState().equals(which) && this.getBoard()[xCoord][yCoord - 1].getState().equals(which) && this.getBoard()[xCoord][yCoord + 1].getState().equals(which)) {
                            score += 100;

                            if (yCoord - 2 >= 0) {
                                if (this.getBoard()[xCoord][yCoord - 2] != null) {
                                    if (this.getBoard()[xCoord][yCoord - 2].getState().equals(which)) {
                                        score += 200;
                                        if (yCoord - 3 >= 0) {
                                            if (this.getBoard()[xCoord][yCoord - 3] != null) {
                                                if (this.getBoard()[xCoord][yCoord - 3].getState().equals(which)) {
                                                    score += 5000;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (yCoord + 2 < this.size) {
                                if (this.getBoard()[xCoord][yCoord + 2] != null) {
                                    if (this.getBoard()[xCoord][yCoord + 2].getState().equals(which)) {
                                        score += 200;
                                        if (yCoord + 3 < size) {
                                            if (this.getBoard()[xCoord][yCoord + 3] != null) {
                                                if (this.getBoard()[xCoord][yCoord + 3].getState().equals(which)) {
                                                    score += 5000;
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                    //diagonally left
                    if (this.getBoard()[xCoord - 1][yCoord - 1] != null && this.getBoard()[xCoord + 1][yCoord + 1] != null) {

                        if (this.getBoard()[xCoord - 1][yCoord - 1].getState().equals(which) && this.getBoard()[xCoord + 1][yCoord + 1].getState().equals(which)) {
                            score += 100;

                            if (xCoord - 2 >= 0 && yCoord - 2 >= 0) {
                                if (this.getBoard()[xCoord - 2][yCoord - 2] != null) {
                                    if (this.getBoard()[xCoord - 2][yCoord - 2].getState().equals(which)) {
                                        score += 200;
                                        if (xCoord - 3 >= 0 && yCoord - 3 >= 0) {
                                            if (this.getBoard()[xCoord - 3][yCoord - 3] != null) {
                                                if (this.getBoard()[xCoord - 3][yCoord - 3].getState().equals(which)) {
                                                    score += 5000;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (xCoord + 2 < size && yCoord + 2 < size) {
                                if (this.getBoard()[xCoord + 2][yCoord + 2] != null) {
                                    if (this.getBoard()[xCoord + 2][yCoord + 2].getState().equals(which)) {
                                        score += 200;
                                        if (xCoord + 3 < size && yCoord + 3 < size) {
                                            if (this.getBoard()[xCoord + 3][yCoord + 3] != null) {
                                                if (this.getBoard()[xCoord + 3][yCoord + 3].getState().equals(which)) {
                                                    score += 5000;
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                    //diagonally right
                    if (this.getBoard()[xCoord - 1][yCoord + 1] != null && this.getBoard()[xCoord + 1][yCoord - 1] != null) {
                        if (this.getBoard()[xCoord - 1][yCoord + 1].getState().equals(which) && this.getBoard()[xCoord + 1][yCoord - 1].getState().equals(which)) {
                            score += 100;

                            if (xCoord - 2 >= 0 && yCoord + 2 < size) {
                                if (this.getBoard()[xCoord - 2][yCoord + 2] != null) {
                                    if (this.getBoard()[xCoord - 2][yCoord + 2].getState().equals(which)) {
                                        score += 200;
                                        if (xCoord - 3 >= 0 && yCoord + 3 < size) {
                                            if (this.getBoard()[xCoord - 3][yCoord + 3] != null) {
                                                if (this.getBoard()[xCoord - 3][yCoord + 3].getState().equals(which)) {
                                                    score += 5000;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (xCoord + 2 < size && yCoord - 2 >= 0) {
                                if (this.getBoard()[xCoord + 2][yCoord - 2] != null) {
                                    if (this.getBoard()[xCoord + 2][yCoord - 2].getState().equals(which)) {
                                        score += 200;
                                        if (xCoord + 3 < size && yCoord - 3 >= 0) {
                                            if (this.getBoard()[xCoord + 3][yCoord - 3] != null) {
                                                if (this.getBoard()[xCoord + 3][yCoord - 3].getState().equals(which)) {
                                                    score += 5000;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
        return score;
    }

    public Board deepCopy(){
        Board copied = new Board(this.size);
        for(int i=0; i<this.size; i++){
            for (int j=0; j<this.size; j++) {
                copied.board[i][j] = board[i][j];
            }
        }
        copied.taken = new LinkedList<>(this.taken);
        lastAdded = this.lastAdded;

        return copied;
    }






//
//
//
//
//    public static void main(String[] args) {
//        Board b = new Board(10);
//        b.addField(new Field(7,7,State.X_TAKEN));
//        b.addField(new Field(6,7,State.X_TAKEN));
//        b.addField(new Field(6,8,State.O_TAKEN));
//        b.addField(new Field(6,6,State.O_TAKEN));
//
//        BoardView boardView = new BoardView();
//        LinkedList<Field> movesPossible = MinMax.getMoves(b);
//        boardView.printBoard(b);
//        System.out.println("\n" + b.heuristics());
//    }

}
