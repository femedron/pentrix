package com.pentrix.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;


public class TextContainer extends Container{
    Label label;
    String text;
    BitmapFont font;
    Skin skin;
    public TextContainer(double x, double y, double w, double h, String text) {
        super(x, y, w, h);
        this.text = text;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/truetypefont/MinecraftBold.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        font = generator.generateFont(parameter);
        generator.dispose(); // don't forget to dispose to avoid memory leaks!
        skin = new Skin(Gdx.files.internal("skins/default_skin/uiskin.json"));

        label = new Label(text,new Label.LabelStyle(font, Color.WHITE));
        updateLabelPosition();
    }
    public TextContainer(double x, double y, double w, double h, String text, double highestY) {
        this(x, y, w, h, text);
        setHighY(highestY);
        updateLabelPosition();
    }

    public void updateLabelPosition(){
        label.setAlignment(Align.center);
        label.setWidth((float) width);
        label.setHeight((float) height);
        label.setX((float) x);
        label.setY((float) y);
    }

    public void setText(String t){
        text = t;
        label.setText(t);
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        label.draw(batch, 1);
    }

//    public void setTextContent(String text){
//        this.text = text;
//    }
}
