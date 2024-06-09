package com.pentrix.game.screens;

import com.badlogic.gdx.Screen;
import com.pentrix.game.Drop;

public abstract class BaseScreen implements Screen {
    final Drop game;

    public BaseScreen (Drop game) {
        this.game = game;
    }
    @Override
    public void resize (int width, int height) {
    }

    @Override
    public void show () {
    }

    @Override
    public void hide () {
    }

    @Override
    public void pause () {
    }

    @Override
    public void resume () {
    }

    @Override
    public void dispose () {
    }
}
