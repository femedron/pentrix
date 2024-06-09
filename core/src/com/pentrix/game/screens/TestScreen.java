package com.pentrix.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.pentrix.game.Drop;

public class TestScreen extends BaseScreen{
    private Stage stage;

    public TestScreen(Drop game) {
        super(game);
        create();
    }

    public void create () {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    public void resize (int width, int height) {
        // See below for what true means.
        stage.getViewport().update(width, height, true);
    }

    public void render (float d) {
        float delta = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    public void dispose () {
        stage.dispose();
    }

}
