package com.pentrix.game.parameters;

import com.badlogic.gdx.math.Rectangle;

public class Mode2Parameters extends GameParameters{
    public Mode2Parameters(){
        super();
        mode = 2;
        gameWidth = 1026;
        gameHeight = 806;
        gameField = new Rectangle(114,62,342,682);
        bricksInFigure = 4;
        brickCountX = 10;
        brickCountY = 20;
        bonuses = true;
        shortDescription = "Tetris with bonuses";
        description = "4-brick tetraminoes, with random bonuses: \n" +
                "unbreakable brick, game tempo change, infinite spin (no falling)\n\n" + description;
    }
}
