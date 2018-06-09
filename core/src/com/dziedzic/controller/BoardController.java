package com.dziedzic.controller;

import com.dziedzic.model.Board;
import com.dziedzic.model.Field;


import java.util.Scanner;

public class BoardController {



    public void playerMove(Board b){
        System.out.println();
        System.out.print("Pick position (first row then column seperated with a dot)");
        Scanner scn = new Scanner(System.in);
        String pos = scn.nextLine();
        String[] splitPos = pos.split("\\.");
        b.addField(new Field(Integer.parseInt(splitPos[0])-1, Integer.parseInt(splitPos[1])-1, Board.State.X_TAKEN));
    }


//    public void computerMove(Board b){
//        double best = MiniMax.minmax(b, 0);
//        b.append(b.getBestNode());
//        b.reset();
//    }


}
