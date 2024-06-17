package com.pentrix.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.pentrix.game.Pentrix;
import com.pentrix.game.parameters.GameParameters;
import jdk.jfr.internal.tool.Main;

public class EndScreen extends MainMenuScreen{
    public EndScreen(Pentrix game) {
        super(game);
    }

    @Override
    public void show() {
        init();

        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        TextField entry = new TextField("lal", skin);
        TextButton back = new TextButton("Skip", skin);

        table.add(new Label("Game over",skin));
        table.row();
        table.add(new Label("Your score:",skin));
        table.row();
        table.add(new Label("Top score:",skin));
        table.row();
        table.add(new Label("Enter your name:",skin));
        table.row();
        table.add(entry);
        table.row();
        table.add(back);

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
        entry.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new LeaderboardScreen(game));
            }
        });

    }
}
