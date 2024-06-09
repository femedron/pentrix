package com.pentrix.game.screens;

import java.util.Iterator;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import com.pentrix.game.Container;
import com.pentrix.game.Drop;
import com.pentrix.game.GameField;
import com.pentrix.game.TextContainer;

public class GameScreen extends BaseScreen{
    private int WIDTH, HEIGHT, SPEED = 200;
    Texture dropT,bucketT,brickT;
    Sound dropSound;
    Music rainMusic;
    OrthographicCamera camera;
    Rectangle brick;
    Array<Rectangle> raindrops;
    Array<Container> containers;
    long lastDropTime;
    int dropsGathered;
    Pixmap pixmap;
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
//        gameField = new Sprite((new Texture("droplet.png")));
//        gameField.setPosition(100, 100);
//        gameField.setColor(0.1f,0.1f,0.1f,1);
        gameField = new GameField(WIDTH/4, HEIGHT/4, WIDTH/4, HEIGHT/4);

        pixmap = new Pixmap( 32, 32, Pixmap.Format.RGBA8888 );
        pixmap.setColor(Color.GREEN);
        pixmap.fillRectangle(0,0, 32, 32);
        brickT = new Texture( pixmap );
        pixmap.dispose();
        brick = new Rectangle(200,200,32,32);

        // load the images for the droplet and the bucket, 64x64 pixels each
        dropT = new Texture(Gdx.files.internal("droplet.png"));

        // load the drop sound effect and the rain background "music"
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        rainMusic.setLooping(true);

        // create the camera and the SpriteBatch
        //camera = new OrthographicCamera();
        //camera.setToOrtho(false, 800, 480);

        containers = new Array<Container>();
        containers.add(new TextContainer(WIDTH/2, HEIGHT/2, 100, 100, "SAS"));

        // create the raindrops array and spawn the first raindrop
        raindrops = new Array<Rectangle>();
        //spawnRaindrop();

        /*createLabels();
        stage.act();
        stage.draw();*/
    }

    private void spawnRaindrop() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, WIDTH - 64);
        raindrop.y = HEIGHT;
        raindrop.width = 64;
        raindrop.height = 64;
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.LIGHT_GRAY);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell the camera to update its matrices.
        //camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        //game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        for(Container c: containers)
            c.render(game.batch);


        game.font.draw(game.batch, "Drops Collected: " + dropsGathered, 0, HEIGHT);
        gameField.render(game.batch);
//        int w,h;
//        w = WIDTH/3;
//        h = HEIGHT/2;
////        pixmap = new Pixmap(w,h, Pixmap.Format.RGBA8888);
////        pixmap.setColor(Color.WHITE);
////        pixmap.fillRectangle(0,0,w,h);
////        pixmap.setColor(Color.BLACK);
////        pixmap.fillRectangle(3,3,w-6,h-6);
////        game.batch.draw(new Texture(pixmap), w/2,h/3);
        game.batch.draw(brickT, brick.x, brick.y, brick.width, brick.height);
//        for (Rectangle raindrop : raindrops) {
//            game.batch.draw(dropT, raindrop.x, raindrop.y);
//        }

        game.batch.end();

        // process user input
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            //camera.unproject(touchPos);
            //bucket.x = touchPos.x - 64 / 2;
        }
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
        dropT.dispose();
        //bucketT.dispose();
        dropSound.dispose();
        rainMusic.dispose();
    }

}