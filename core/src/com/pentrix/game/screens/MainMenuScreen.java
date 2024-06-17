package com.pentrix.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.pentrix.game.Pentrix;
import com.pentrix.game.parameters.GameParameters;
import com.pentrix.game.parameters.Mode1Parameters;
import com.pentrix.game.parameters.Mode3Parameters;


public class MainMenuScreen extends BaseScreen {
    GameParameters gameParameters;
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
    void init(String skinPath){
        Image bg = new Image(backgroundTexture);
        bg.setSize(stage.getWidth(), stage.getHeight());
        this.bg = bg;
        stage.addActor(bg);

        if(skinPath == null)
            skinPath = "skins/default_skin/uiskin.json";
        skin = new Skin(Gdx.files.internal(skinPath));

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

    void useFonts(){
        TextButton.TextButtonStyle buttonStyle = skin.get(TextButton.TextButtonStyle.class);
        buttonStyle.font = font50;
        buttonStyle.fontColor = Color.WHITE;

        Label.LabelStyle labelStyle = skin.get(Label.LabelStyle.class);
        labelStyle.font = font30;
        labelStyle.fontColor = Color.WHITE;
    }
    @Override
    public void show() {
        init(null);
        useFonts();

        game.resumeMusic();

        Label.LabelStyle bigLabel = new Label.LabelStyle(minecraft50, Color.WHITE);

        Container<Label> container = new Container<Label>(new Label("Pentrix", bigLabel));
        container.setFillParent(true);
        container.center().top().padTop(50);
        stage.addActor(container);
        // Create a table that fills the screen. Everything else will go inside this table.
        Table tableL = new Table();
        Table tableR = new Table();
        tableL.setFillParent(true);
        tableL.center().left();
        tableL.defaults().center().left().padLeft(20).spaceBottom(10);
        stage.addActor(tableL);

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


        //tableR.setBackground();

        Label.LabelStyle lol = new Label.LabelStyle(font30, Color.BLACK);
//        lol.background = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("mine/table.jpg"))));
//        lol.background.setBottomHeight(lol.background.getBottomHeight()+100);
//        lol.background.setTopHeight(lol.background.getTopHeight()+100);
        // Text on the right (game tutorial)
        Label textLabel = new Label("Clear as many rows as possible by arranging the falling shapes of 4 (or even try 5!) blocks each, dont let them fill the screen!\n\n" +
                "Achieving line streaks gives much more points, while moving to harder next level is done after line clearing.\n\n" +
                "Control the shape with arrow buttons (arrow up -> rotate the figure). \nWhile in game: press <ESC> to EXIT to menu\n\n\n" +
                "          Authors:\n" +
                "          Stetsyk M.,\n" +
                "          Polianytsia A.", lol);
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

        settings.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new SettingsScreen(game));
            }
        });

        quit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
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

