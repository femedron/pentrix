package com.pentrix.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.pentrix.game.Drop;


public class MainMenuScreen extends BaseScreen {
    private int WIDTH, HEIGHT;
    OrthographicCamera camera;

    public MainMenuScreen(final Drop game, int w, int h) {
        super(game);
        WIDTH = w;
        HEIGHT = h;

        //camera = new OrthographicCamera();
        //camera.setToOrtho(false, WIDTH, HEIGHT);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.005f, 0.1f, 0.1f, 1);

        //camera.update();
        //game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Welcome to Pentrix!!! ", 100, 150);
        game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game, WIDTH, HEIGHT));//todo
            dispose();
        }
    }

}

