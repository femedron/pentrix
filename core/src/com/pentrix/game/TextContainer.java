package com.pentrix.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextContainer extends Container{

    String text;
    public TextContainer(double x, double y, double w, double h, String text) {
        super(x, y, w, h);
        this.text = text;
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        new BitmapFont().draw(batch, text, (float)x, (float)y);
    }

//    public void setTextContent(String text){
//        this.text = text;
//    }
}
