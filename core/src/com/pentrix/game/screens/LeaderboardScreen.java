package com.pentrix.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.pentrix.game.Pentrix;
import com.pentrix.game.parameters.GameParameters;

public class LeaderboardScreen extends MainMenuScreen{
    public LeaderboardScreen(Pentrix game, BaseScreen parent) {
        super(game, parent);
    }

    @Override
    public void show() {
        init();

        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        TextButton back = new TextButton("Go back", skin);  //back to main menu

        table.add(new Label("Leaderboard",skin));
        table.row();
        table.add(new Label("1. xxx",skin));
        table.row();
        table.add(new Label("2. xxx",skin));
        table.row();
        table.add(new Label("3. xxx",skin));
        table.row();
        table.add(back);

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(parent);
            }
        });
    }
}
