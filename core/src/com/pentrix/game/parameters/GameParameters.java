package com.pentrix.game.parameters;


//level start parameters
public class GameParameters {

    public static final int width = 1136, height = 870;
    public final int baseBrickSize = 9; //texture size DO NOT CHANGE
    public final int brickGap, brickCountX, brickCountY;
    public final long baseTimeGap, fallTimeGap;
    public final int bricksInFigure;
    public final boolean bonuses;
    public GameParameters(){
        baseTimeGap = 30000000;
        fallTimeGap = baseTimeGap * 7;
        brickGap = 2;
        bricksInFigure = 5;  // todo for 4
        brickCountX = 13;
        brickCountY = 26;
        bonuses = false;
    }

}
