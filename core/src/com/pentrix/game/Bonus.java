package com.pentrix.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Bonus {
    private double x,y,size, gap;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
        collider.x = (float) x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
        collider.y = (float) y;
    }

    Texture texture;
    Rectangle collider;
    GameField gameField;
    public Bonus(GameField gf, double fromY){
        gameField = gf;
        gap = gameField.brick_gap;
        size = gf.brick_size/2;


        loadTexture();
        spawn(fromY, gf.y + gf.height);
    }
    void loadTexture(){
        texture = new Texture(Gdx.files.internal("mine/star.png"));
    }
    void generateBonus(){
        switch (MathUtils.random(0,1)){
            case 0:
                    gameField.decreaseTempo();
            break;
            case 1:
                    gameField.setInfSpin(true);
            break;
        }
    }

    void spawn(double fromY, double toY){
        x = MathUtils.random((float) gameField.x, (float) (gameField.x+gameField.width));
        y = MathUtils.random((float)fromY, (float)toY);
        collider = new Rectangle();
        collider.width = (float) size;
        collider.height = (float) size;
        collider.x = (float) x;
        collider.y = (float) y;
    }

    public Point calcMatrixPoint(){
        double xx = getX(), yy = getY();
        int orderX = (int) ((xx - gameField.x - gap + size/2) / (gap + size));
        int orderY = (int) ((yy - gameField.y - gap + size/2) / (gap + size));
        return new Point(orderX, orderY);
    }
    public void setCoords(int mx, int my){
        setX(gameField.x + gap + (mx)*(gap+size));
        setY(gameField.y + gap + (my)*(gap+size));
    }

    public void collect(){
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/collect.mp3"));
        long id = sound.play();
        sound.setVolume(id, AppPreferences.instance.getSoundVolume());
        generateBonus();
        gameField.bonus = null;
    }

    public boolean overlaps(Brick b){
        return collider.overlaps(b.collider);
    }

    public boolean overlaps(Pentamino p){
        for (Brick brick: p.bricks) {
            if(overlaps(brick))
                return true;
        }
        return false;
    }
    //    public boolean overlaps(Rectangle rect){
//        return collider.overlaps(rect);
//    }
    public void render(SpriteBatch batch){
        batch.draw(texture, (float) x, (float) y, (float) size, (float) size);
    }
}
