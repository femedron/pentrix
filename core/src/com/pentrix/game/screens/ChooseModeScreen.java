package com.pentrix.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.pentrix.game.Pentrix;
import com.pentrix.game.parameters.*;

public class ChooseModeScreen extends MainMenuScreen{
    public ChooseModeScreen(Pentrix game) {
        super(game);
        backgroundTexture = new Texture(Gdx.files.internal("mine/bogdan.png"));
    }

    @Override
    public void show() {
        init(null);
        useFonts();

        Label.LabelStyle bigLabel = new Label.LabelStyle(minecraft50, Color.WHITE);
        skin.get(Label.LabelStyle.class).fontColor = Color.BLACK;
        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        table.defaults().pad(30).fill();

        TextButton mode1 = new TextButton("1", skin);
        TextButton mode2 = new TextButton("2", skin);
        TextButton mode3 = new TextButton("3", skin);
        TextButton mode4 = new TextButton("4", skin);
        TextButton back = new TextButton("Go back", skin);  //back to main menu
        TextButton quit = new TextButton("Quit", skin);

        Mode1Parameters mp1 = new Mode1Parameters();
        Mode2Parameters mp2 = new Mode2Parameters();
        Mode3Parameters mp3 = new Mode3Parameters();
        Mode4Parameters mp4 = new Mode4Parameters();
        table.add(new Label("Choose game mode",bigLabel)).colspan(2);
        table.row();
        table.add(mode1);
        table.add(new Label(mp1.shortDescription,skin));
        table.row();
        table.add(mode2);
        table.add(new Label(mp2.shortDescription,skin));
        table.row();
        table.add(mode3);
        table.add(new Label(mp3.shortDescription,skin));
        table.row();
        table.add(mode4);
        table.add(new Label(mp4.shortDescription,skin));
        table.row();
        table.add(back);
        table.add(quit);


        mode1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new ModeDescriptionScreen(game, new Mode1Parameters()));
            }
        });
        mode2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new ModeDescriptionScreen(game, new Mode2Parameters()));
            }
        });
        mode3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new ModeDescriptionScreen(game, new Mode3Parameters()));
            }
        });
        mode4.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new ModeDescriptionScreen(game, new Mode4Parameters()));
            }
        });

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
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
