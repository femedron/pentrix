package com.pentrix.game.parameters;


import com.badlogic.gdx.math.Rectangle;

public class Mode3Parameters extends GameParameters{
    public Mode3Parameters(){
        super();
        gameWidth = 1332; // 379 * 3
        gameHeight = 1032; // 756 + 2 * 756/12
        gameField = new Rectangle(148,73,444,886); // 126 == 379/3 ; 63 == 756/12
        bricksInFigure = 5;
        brickCountX = 13;
        brickCountY = 26;
    }
}
