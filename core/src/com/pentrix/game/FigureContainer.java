package com.pentrix.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FigureContainer extends Container{
    Pentamino figure;
    public FigureContainer(int x, int y, int w, int h, Pentamino figure) {
        super(x, y, w, h);
        this.figure = figure;
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
       // figure.render(batch,x,y);
    }
//    public void setFigure(Pentamino figure){
//
//    }
}
