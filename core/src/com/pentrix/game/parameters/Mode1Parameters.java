package com.pentrix.game.parameters;

import com.badlogic.gdx.math.Rectangle;

public class Mode1Parameters extends GameParameters{
    public Mode1Parameters(){
        super();
        mode = 1;
        gameWidth = 1026;
        gameHeight = 806;
        gameField = new Rectangle(114,62,342,682);
        bricksInFigure = 4;
        brickCountX = 10;
        brickCountY = 20;
    }
}
