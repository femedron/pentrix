package com.pentrix.game.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.pentrix.game.Pentrix;

public class SettingsScreen extends MainMenuScreen{
    public SettingsScreen(Pentrix game) {
        super(game);
    }

    @Override
    public void show() {
        init();

        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        TextButton back = new TextButton("Go back", skin);  //back to main menu
        Slider music = new Slider(0,100,1,false,skin);
        Slider sounds = new Slider(0,100,1,false,skin);

        table.add(new Label("Settings",skin)); //todo align center
        table.row();
        table.add(new Label("Music", skin));
        table.add(music);
        table.row();
        table.add(new Label("Sounds", skin));
        table.add(sounds);
        table.row();
        table.add(back); //todo align center

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
    }
}
