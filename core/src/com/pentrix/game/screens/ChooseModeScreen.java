package com.pentrix.game.screens;

import com.badlogic.gdx.Gdx;
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
        init();

        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        //create buttons todo: 4 buttons on the left, 'back' and 'quit' buttons in bottom
        TextButton mode1 = new TextButton("1", skin);
        TextButton mode2 = new TextButton("2", skin);
        TextButton mode3 = new TextButton("3", skin);
        TextButton mode4 = new TextButton("4", skin);
        TextButton back = new TextButton("Go back", skin);  //back to main menu
        TextButton quit = new TextButton("Quit", skin);

        table.add(mode1).left().padLeft(20).padBottom(10);
        table.add(new Label("Mode 1 short description",skin));
        table.row();
        table.add(mode2).left().padLeft(20).padBottom(10);
        table.add(new Label("Mode 2 short description",skin));
        table.row();
        table.add(mode3).left().padLeft(20).padBottom(10);
        table.add(new Label("Mode 3 short description",skin));
        table.row();
        table.add(mode4).left().padLeft(20).padBottom(10);
        table.add(new Label("Mode 4 short description",skin));
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
