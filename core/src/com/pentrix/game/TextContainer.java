package com.pentrix.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextContainer extends Container{

    String text;
    public TextContainer(int x, int y, int w, int h, String text) {
        super(x, y, w, h);
        this.text = text;
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        new BitmapFont().draw(batch, text, content.x, content.y);
    }

//    public void setTextContent(String text){
//        this.text = text;
//    }
}
