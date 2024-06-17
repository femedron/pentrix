package com.pentrix.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.pentrix.game.Pentrix;
import com.pentrix.game.parameters.GameParameters;

import javax.swing.*;

public class ModeDescriptionScreen extends MainMenuScreen{
    public ModeDescriptionScreen(Pentrix game, GameParameters gameParameters) {
        super(game, null);
        this.gameParameters = gameParameters;
        backgroundTexture = new Texture(Gdx.files.internal("mine/mode_desc.jpg"));
    }

    @Override
    public void show() {
        init(null);
        useFonts();

        Label.LabelStyle bigLabel = new Label.LabelStyle(minecraft50, Color.WHITE);
        skin.get(Label.LabelStyle.class).fontColor = Color.WHITE;
        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        table.defaults().pad(20).fill().minWidth(WIDTH/3).maxWidth(WIDTH/2);

        TextButton play = new TextButton("Play", skin);
        TextButton back = new TextButton("Go back", skin);  //back to main menu
        TextButton quit = new TextButton("Quit", skin);

        Label label = new Label(gameParameters.description,skin);
        label.setWrap(true);

        Label cap = new Label("Mode "+gameParameters.mode,bigLabel);
        cap.setAlignment(Align.center);
        table.add(cap);
        table.row();
        table.add(label);
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
