package com.pentrix.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.pentrix.game.*;

import java.security.Key;

public class GameScreen extends BaseScreen{
    private int WIDTH, HEIGHT, SPEED = 200;
    public final long BASE_TIME_GAP = 30000000; // 30 ms
    OrthographicCamera camera;
    Viewport vp;
    Array<Container> containers;
    int dropsGathered;
    GameField gameField;
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
    public GameScreen(final Drop game, int w, int h) {
        super(game);
        WIDTH = w;
        HEIGHT = h;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        vp = new ExtendViewport(w,h , camera);

        containers = new Array<Container>();
        gameField = new GameField(WIDTH/5, HEIGHT/5, WIDTH/3, 600, this);
        containers.add(gameField);
        containers.add(new TextContainer(WIDTH/2, HEIGHT/2, 100, 100, "SAS"));
    }

    @Override
    public void resize(int width, int height) {
        vp.update(width, height);
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLUE);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

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

        game.batch.begin();
        for(Container c: containers)
            c.render(game.batch);
        game.font.draw(game.batch, "Drops Collected: " + dropsGathered, 0, HEIGHT);
        game.batch.end();
//        for (Rectangle raindrop : raindrops) {
//            game.batch.draw(dropT, raindrop.x, raindrop.y);
//        }


        // process user input
//        if (Gdx.input.isTouched()) {
//            Vector3 touchPos = new Vector3();
//            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//            //camera.unproject(touchPos);
//            //bucket.x = touchPos.x - 64 / 2;
//        }
//        if (Gdx.input.isKeyPressed(Keys.LEFT))
//            bucket.x -= SPEED * Gdx.graphics.getDeltaTime();
//        if (Gdx.input.isKeyPressed(Keys.RIGHT))
//            bucket.x += SPEED * Gdx.graphics.getDeltaTime();

        // make sure the bucket stays within the screen bounds
//        if (bucket.x < 0)
//            bucket.x = 0;
//        if (bucket.x > WIDTH - 64)
//            bucket.x = WIDTH - 64;

        // check if we need to create a new raindrop
//        if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
//            spawnRaindrop();

//        Iterator<Rectangle> iter = raindrops.iterator();
//        while (iter.hasNext()) {
//            Rectangle raindrop = iter.next();
//            raindrop.y -= SPEED * Gdx.graphics.getDeltaTime();
//            if (raindrop.y + 64 < 0)
//                iter.remove();
//            if (raindrop.overlaps(bucket)) {
//                dropsGathered++;
//                dropSound.play();
//                iter.remove();
//            }
       // }
    }

    @Override
    public void dispose() {
        //dropT.dispose();
        //bucketT.dispose();
//        dropSound.dispose();
//        rainMusic.dispose();
    }

}