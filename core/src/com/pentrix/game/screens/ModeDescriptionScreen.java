package com.pentrix.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.pentrix.game.Pentrix;
import com.pentrix.game.parameters.GameParameters;

public class ModeDescriptionScreen extends MainMenuScreen{
    GameParameters gameParameters;
    public ModeDescriptionScreen(Pentrix game, GameParameters gameParameters) {
        super(game);
        this.gameParameters = gameParameters;
        backgroundTexture = new Texture(Gdx.files.internal("mine/eminem.jpg"));
    }

    @Override
    public void show() {
        init();

        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        TextButton play = new TextButton("Play", skin);
        TextButton back = new TextButton("Go back", skin);  //back to main menu
        TextButton quit = new TextButton("Quit", skin);

        table.add(new Label("Mode full description",skin));
        table.row();
        table.add(play);
        table.row();
        table.add(back);
        table.row();
        table.add(quit);

        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game, gameParameters));
            }
        });

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new ChooseModeScreen(game));
            }
        });

        quit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
    }

}
