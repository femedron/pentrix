package com.pentrix.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.pentrix.game.Pentrix;
import com.pentrix.game.parameters.GameParameters;
import com.pentrix.game.parameters.Mode1Parameters;
import com.pentrix.game.parameters.Mode3Parameters;


public class MainMenuScreen extends BaseScreen {
    int WIDTH = GameParameters.width,HEIGHT = GameParameters.height;
    BaseScreen instance;
    Pentrix game;
    Stage stage;
    Skin skin;
    Texture backgroundTexture = new Texture(Gdx.files.internal("mine/navalnyi.jpg"));
    Image bg;

    public MainMenuScreen(Pentrix game) {
        super(game);
        instance = this;
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }
    void init(){
        Image bg = new Image(backgroundTexture);
        bg.setSize(stage.getWidth(), stage.getHeight());
        this.bg = bg;
        stage.addActor(bg);

        skin = new Skin(Gdx.files.internal("skins/default_skin/uiskin.json"));
    }
    @Override
    public void show() {
        init();

        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Game name on top todo
        Label gameNameLabel = new Label("Pentrix", skin);
        table.add(gameNameLabel).colspan(2).expandX().padTop(20);
        table.row();

        // Two lines of text in top right corner
        Label line1Label = new Label("Top score:", skin);
        Label line2Label = new Label("Last score:", skin);
        table.add().expandX();
        table.add(line1Label).right().padRight(20);
        table.row();
        table.add().expandX();
        table.add(line2Label).right().padRight(20);
        table.row();



        //create buttons
        TextButton restart = new TextButton("Restart", skin);
        TextButton newGame = new TextButton("New Game", skin);
        TextButton leaderboard = new TextButton("Leaderboard", skin);
        TextButton settings = new TextButton("Settings", skin);
        TextButton quit = new TextButton("Quit (TEMPORARY: END SCREEN (GAME OVER)", skin);


        //todo: try to make buttons big, while preserving font sharpness (mb try using freetype fonts)
        newGame.setTransform(true);
        newGame.scaleBy(0.5f);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/truetypefont/Amble-Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 24;
        BitmapFont font24 = generator.generateFont(parameter);
        generator.dispose(); // don't forget to dispose to avoid memory leaks!

        //add buttons to table
//        table.add(newGame).fillX().uniformX();
//        table.row();//.pad(10, 0, 10, 0);
//        table.add(leaderboard).fillX().uniformX();
//        table.row();
//        table.add(settings).fillX().uniformX();
//        table.row();
//        table.add(quit).fillX().uniformX();
        table.add(restart).left().padLeft(20).padBottom(10);  // todo: this button could be hidden, keep in mind when positioning
        table.row();
        table.add(newGame).left().padLeft(20).padBottom(10);
        table.row();
        table.add(leaderboard).left().padLeft(20).padBottom(10);
        table.row();
        table.add(settings).left().padLeft(20).padBottom(10);
        table.row();
        table.add(quit).left().padLeft(20).padBottom(10);
        table.row();

        // Text on the right (game tutorial)
        Label textLabel = new Label("This is some text on the right sideccccccccccccccccccccc" +
                "cccccccccccccccccccccccccccccccccccccccccccccccccccccccccc.", skin);
        table.add().expandX();
        table.add(textLabel).right().padRight(20);

        restart.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game, new Mode1Parameters()));
            }
        });
        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor){
                game.setScreen(new ChooseModeScreen(game));
            }
        });
        leaderboard.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor){
                game.setScreen(new LeaderboardScreen(game));
            }
        });

        settings.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new SettingsScreen(game));
            }
        });

        quit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new EndScreen(game));
            }
        });
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.005f, 0.1f, 0.1f, 1);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        bg.setSize(width,height);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}

