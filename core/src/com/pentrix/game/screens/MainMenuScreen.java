package com.pentrix.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
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
    BitmapFont font30, font50, minecraft50;

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

        genFonts();
    }

    void genFonts(){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/truetypefont/Amble-Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        font50 = generator.generateFont(parameter);
        parameter.size = 30;
        font30 = generator.generateFont(parameter);
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/truetypefont/MinecraftBold.otf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        minecraft50 = generator.generateFont(parameter);
        generator.dispose(); // don't forget to dispose to avoid memory leaks!
    }
    @Override
    public void show() {
        init();

        TextButton.TextButtonStyle buttonStyle = skin.get(TextButton.TextButtonStyle.class);
        buttonStyle.font = font50;
        buttonStyle.fontColor = Color.WHITE;

        Label.LabelStyle labelStyle = skin.get(Label.LabelStyle.class);
        labelStyle.font = font30;
        labelStyle.fontColor = Color.WHITE;

        Label.LabelStyle bigLabel = new Label.LabelStyle(minecraft50, Color.WHITE);

        Container<Label> container = new Container<Label>(new Label("Pentrix", bigLabel));
        container.setFillParent(true);
        container.center().top().padTop(50);
        stage.addActor(container);
        // Create a table that fills the screen. Everything else will go inside this table.
        Table tableL = new Table();
        Table tableR = new Table();
        tableL.setDebug(true);
        tableL.setFillParent(true);
        tableL.center().left();
        tableL.defaults().center().left().padLeft(20).spaceBottom(10);
        stage.addActor(tableL);

        tableR.setDebug(true);
        tableR.setFillParent(true);
        tableR.center().right();
        tableR.defaults().center().right().padRight(20).spaceBottom(10);
        stage.addActor(tableR);

        // Two lines of text in top right corner
        Label line1Label = new Label("Top score:", skin);
        Label line2Label = new Label("Last score:", skin);
        tableL.add(line1Label);
        tableL.row();
        tableL.add(line2Label).spaceBottom(100);
        tableL.row();

        //create buttons
        TextButton restart = new TextButton("Restart", skin);
        Container restartContainer = new Container<>(restart);
        TextButton newGame = new TextButton("New Game", skin);
        TextButton leaderboard = new TextButton("Leaderboard", skin);
        TextButton settings = new TextButton("Settings", skin);
        TextButton quit = new TextButton("Quit", skin);

        tableL.add(restartContainer);
        //restartContainer.setVisible(false);
        tableL.row();
        tableL.add(newGame);
        tableL.row();
        tableL.add(leaderboard);
        tableL.row();
        tableL.add(settings);
        tableL.row();
        tableL.add(quit);
        tableL.row();


        // Text on the right (game tutorial)
        Label textLabel = new Label("This is some text on the right sideccccccccccccccccccccc" +
                "cccccccccccccccccccccccccccccccccccccccccccccccccccccccccc.", skin);
        textLabel.setWrap(true);
        tableR.add(textLabel).prefWidth(WIDTH/3).maxHeight(HEIGHT*3/4);





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

