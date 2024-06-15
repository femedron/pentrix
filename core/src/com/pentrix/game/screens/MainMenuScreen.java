package com.pentrix.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pentrix.game.Drop;
import com.pentrix.game.parameters.GameParameters;


public class MainMenuScreen extends BaseScreen {
//    OrthographicCamera camera; todo


    public MainMenuScreen(final Drop game) {
        super(game);

//        camera = new OrthographicCamera();
//        camera.setToOrtho(false, width, height);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.005f, 0.1f, 0.1f, 1);
//        camera.update();
//        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Welcome to Pentrix!!! ", 100, 150);
        game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game, new GameParameters()));
            dispose();
        }
    }

}

