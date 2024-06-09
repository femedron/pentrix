package com.pentrix.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pentrix.game.screens.MainMenuScreen;


public class Drop extends Game {

    public SpriteBatch batch;
    public BitmapFont font;
    int width,height;

    public Drop(int w, int h){
        width = w; height = h;
    }

    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont(); // use libGDX's default Arial font
        this.setScreen(new MainMenuScreen(this, width, height));
    }

    public void render() {
        super.render(); // important!
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }

}