package com.pentrix.game.parameters;

import com.badlogic.gdx.math.Rectangle;

public class Mode4Parameters extends GameParameters{
    public Mode4Parameters(){
        super();
        bonusDelay = 10; //10s
        mode = 4;
        gameWidth = 1332; // 379 * 3
        gameHeight = 1032; // 756 + 2 * 756/12
        gameField = new Rectangle(148,73,444,886); // 126 == 379/3 ; 63 == 756/12
        bricksInFigure = 5;
        brickCountX = 13;
        brickCountY = 26;
        bonuses = true;
        shortDescription = "Pentrix with bonuses";
        description = "5-brick pentaminoes, with collectable bonuses: \n" +
                "game tempo change, infinite spin (no falling)\n\n" + description;
    }
}
