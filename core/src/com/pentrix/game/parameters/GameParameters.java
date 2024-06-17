package com.pentrix.game.parameters;


import com.badlogic.gdx.math.Rectangle;

//level start parameters
public class GameParameters {

    public static final int width = 1200, height = 900;
    public  int gameWidth, gameHeight;
    public  int baseBrickSize = 9; //texture size DO NOT CHANGE
    public  int brickGap, brickCountX, brickCountY;
    public  long baseTimeGap, fallTimeGap;
    public  int bricksInFigure;
    public  boolean bonuses;
    public  int lineReward;
    public int mode;
    public  Rectangle gameField;
    public GameParameters(){
        baseTimeGap = 30000000;
        fallTimeGap = baseTimeGap * 7;
        brickGap = 2;
        lineReward = 100;
    }

}
