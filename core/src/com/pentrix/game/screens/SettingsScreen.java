package com.pentrix.game.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.pentrix.game.AppPreferences;
import com.pentrix.game.Pentrix;

public class SettingsScreen extends MainMenuScreen{
    AppPreferences appPreferences;
    Slider music, sounds;
    public SettingsScreen(Pentrix game) {
        super(game);
        appPreferences = AppPreferences.instance;
    }

    @Override
    public void show() {
        init("skins/glassy/skin/glassy-ui.json");
        useFonts();

        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        table.defaults().pad(30);

        TextButton back = new TextButton("Go back", skin);  //back to main menu
        music = new Slider(0f,1f,0.1f,false,skin);
        music.setValue(appPreferences.getMusicVolume());
        music.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                appPreferences.setMusicVolume(music.getValue());
                appPreferences.setMusicEnabled(music.getValue() > 0);
                return false;
            }
        });

        sounds = new Slider(0f,1f,0.1f,false,skin);
        sounds.setValue(appPreferences.getSoundVolume());
        sounds.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                appPreferences.setSoundVolume(sounds.getValue());
                appPreferences.setSoundEffectsEnabled(sounds.getValue() > 0);
                return false;
            }
        });

        table.add(new Label("Settings",skin)).colspan(2);
        table.row();
        table.add(new Label("Music", skin));
        table.add(music);
        table.row();
        table.add(new Label("Sounds", skin));
        table.add(sounds);
        table.row();
        table.add(back).colspan(2);

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
    }
}
