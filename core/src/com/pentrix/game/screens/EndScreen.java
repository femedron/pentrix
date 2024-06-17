package com.pentrix.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.pentrix.game.AppPreferences;
import com.pentrix.game.BestResult;
import com.pentrix.game.Pentrix;
import com.pentrix.game.parameters.GameParameters;
import jdk.jfr.internal.tool.Main;

public class EndScreen extends MainMenuScreen{
    Sound gameOverSound;
    GameScreen gameScreen;
    public EndScreen(Pentrix game, GameScreen gs, GameParameters gp) {
        super(game);
        gameParameters = gp;
        gameScreen = gs;
        gameOverSound = Gdx.audio.newSound(Gdx.files.internal("sounds/gameover.mp3"));
    }

    @Override
    public void show() {
        init(null);
        useFonts();
        game.stopMusic();

        int mode,score,level, topScore,topLevel;
        mode = gameParameters.mode;
        score = gameScreen.score;
        level = gameScreen.level;
        BestResult best = game.bestResults[mode-1];
        topScore = best.bestScore;
        topLevel = best.bestLevel;


        long id = gameOverSound.play();
        gameOverSound.setVolume(id, AppPreferences.instance.getSoundVolume());
        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        table.defaults().pad(20);

        TextButton back = new TextButton("OK", skin);

        table.add(new Label("Game over",new Label.LabelStyle(minecraft50, Color.WHITE))).colspan(3);
        table.row();
        table.add(new Label("Mode: "+mode,skin)).colspan(3);
        table.row();
        table.add(new Label("Your score: "+score,skin));
        table.add(new Label("|",skin));
        table.add(new Label("Top score: "+topScore,skin));
        table.row();
        table.add(new Label("Your level: "+level,skin));
        table.add(new Label("|",skin));
        table.add(new Label("Top level: "+topLevel,skin));
        table.row();
        table.add(back).colspan(3);

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        if(score > topScore)
            topScore = score;
        if(level > topLevel)
            topLevel = level;
        game.bestResults[mode-1] = new BestResult(mode, topScore, topLevel);
    }

    @Override
    public void dispose() {
        super.dispose();
        gameOverSound.dispose();
    }
}
