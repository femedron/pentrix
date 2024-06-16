package com.pentrix.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.pentrix.game.*;
import com.pentrix.game.Container;
import com.pentrix.game.parameters.GameParameters;

import java.awt.*;

public class GameScreen extends BaseScreen{
    GameParameters gp;
    private final double width,height;
    OrthographicCamera camera;
    Viewport vp;
    Array<Container> containers;
    GameField gameField;
    TextContainer score, scoreTop,mode,level;
    FigureContainer figureContainer;

    /*font
    private void createLabels(){
        stage = new Stage(new ScreenViewport());
        int Help_Guides = 12;
        int row_height = Gdx.graphics.getWidth() / 12;
        int col_width = Gdx.graphics.getWidth() / 12;
//        addBackgroundGuide(Help_Guides);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Amble-Light.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 30;
        parameter.borderWidth = 1;
        parameter.color = Color.YELLOW;
        parameter.shadowOffsetX = 3;
        parameter.shadowOffsetY = 3;
        parameter.shadowColor = new Color(0, 0.5f, 0, 0.75f);
        BitmapFont font24 = generator.generateFont(parameter); // font size 24 pixels
        generator.dispose();

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font24;

        Label label2 = new Label("True Type Font (.ttf) - Gdx FreeType",labelStyle);
        label2.setSize(100,100);
        label2.setPosition(100,100);
        stage.addActor(label2);
    }
//    void addBackgroundGuide(int columns){
//        Texture texture = new Texture(Gdx.files.internal("background.jpg"));
//        texture.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
//
//        TextureRegion textureRegion = new TextureRegion(texture);
//        textureRegion.setRegion(0,0,texture.getWidth()*columns,texture.getWidth()*columns);
//        Image background = new Image(textureRegion);
//        background.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getWidth());
//        background.setPosition(0,Gdx.graphics.getHeight()-background.getHeight());
//        stage.addActor(background);
//    }
     */
    public GameScreen(final Pentrix game, GameParameters gp) {
        super(game);
        this.gp = gp;
        width = gp.width;
        height = gp.height;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, (float) width, (float) height);
        vp = new ExtendViewport((float) width, (float) height, camera);

        createContainers();

        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode){
                    case Keys.LEFT:
                        gameField.setMoveOption(MoveOption.Left);
                        break;
                    case Keys.RIGHT:
                        gameField.setMoveOption(MoveOption.Right);
                        break;
                    case Keys.DOWN:
                        gameField.setMoveOption(MoveOption.Down);
                        break;
                    case Keys.UP:
                        gameField.setRotateFlag(true);
                        break;
                }
                return super.keyDown(keycode);
            }

            @Override
            public boolean keyUp(int keycode) {
                switch (keycode){
                    case Keys.LEFT:
                    case Keys.RIGHT:
                    case Keys.DOWN:
                        gameField.setMoveOption(MoveOption.None);
                        break;
                    case Keys.UP:
                        gameField.setRotateFlag(false);
                        break;
                }
                return super.keyUp(keycode);
            }
        });
    }

    private void createContainers(){
        containers = new Array<Container>();

        gameField = new GameField(
                width/3-200,
                height/15,
                width/3,
                height*13/15,
                gp);

        double xx = gameField.ox+gameField.owidth+100;
        double yGap = (3*gameField.height/8)/5;
        score = new TextContainer(
                xx,
                0,
                gameField.width,
                gameField.height/8,
                "Score: ",
                gameField.y + gameField.height);
        scoreTop = new TextContainer(
                xx,
                0,
                gameField.width,
                gameField.height/8,
                "Top score: ",
                score.oy - yGap);
        mode = new TextContainer(
                xx,
                0,
                gameField.width,
                gameField.height/8,
                "Mode: ",
                scoreTop.oy - yGap);
        level = new TextContainer(
                xx,
                0,
                gameField.width,
                gameField.height/8,
                "Level: ",
                mode.oy - yGap);
        figureContainer = new FigureContainer(
                xx,
                level.oy - yGap,
                gameField.width,
                level.oy - yGap - gameField.y,
                gameField,
                gp);

        containers.add(gameField);
        containers.add(score);
        containers.add(scoreTop);
        containers.add(mode);
        containers.add(level);
        containers.add(figureContainer);
    }

    @Override
    public void resize(int width, int height) {
        vp.update(width, height);
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.GREEN);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        for(Container c: containers)
            c.render(game.batch);
        game.batch.end();
    }

    @Override
    public void dispose() {
        //todo

        //dropT.dispose();
        //bucketT.dispose();
//        dropSound.dispose();
//        rainMusic.dispose();
    }

}