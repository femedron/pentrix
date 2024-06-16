package com.pentrix.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.pentrix.game.parameters.GameParameters;

public class FigureContainer extends Container{
    GameParameters gp;
    GameField gf;
    Pentamino figure; //may be null
    public FigureContainer(double x, double highY, double w, double h, GameField gf, GameParameters gp) {
        super(x, 0, w, h);
        setHighY(highY);
        this.gp = gp;
        this.gf = gf;
        gf.setFigureContainer(this);
        releaseAndUpdate();
    }

    public Pentamino releaseAndUpdate(){
        Pentamino old = figure;
        setFigure(new Pentamino(MathUtils.random(1,18),0,0,gp.bricksInFigure,gf));
        return old;
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        if(figure != null)
            figure.render(batch);
    }
    public void setFigure(Pentamino figure){
        this.figure = figure;
        figure.rotate(true);
        double gapsX = figure.x00 + width - figure.x01;
        double gapsY = figure.y00 + height - figure.y01;
        figure.putFigure(x + gapsX/2 - (figure.x00 - figure.x), y + gapsY/2 - (figure.y00 - figure.y));
    }
}
