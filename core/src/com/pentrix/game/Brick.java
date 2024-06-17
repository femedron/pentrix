package com.pentrix.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Brick {
    private double x,y,size;

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

    TextureRegion textureRegion;
    Rectangle collider;
    GameField gameField;
    double gap;
    Pentamino pentamino;

    public Brick(Pentamino pentamino){
        this.pentamino = pentamino;
        gameField = pentamino.gameField;
        this.size = gameField.brick_size;
        gap = gameField.brick_gap;
        initTexture();
        collider = new Rectangle();
        collider.width = (float) size;
        collider.height = (float) size;
    }
    private void initTexture(){
        Texture texture = new Texture(Gdx.files.internal("mine/brick16.png"));
        textureRegion = TextureRegion.split(texture, 16, 16)[0][0];  //todo match certain pattern with texture, color
    }

//    public boolean isOnLine(double yy){
//        return y <= yy && yy <= y+size;
//    }
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
//    public Point calcPatternPoint(){
//        int patternX = (int) ((getX() - gap - pentamino.x + size/2) / (gap+size));
//        int patternY = (int) ((getY() - gap - pentamino.y + size/2) / (gap+size));
//        patternY = 4 - patternY; // reverse order in pattern[]
//        return new Point(patternX,patternY);
//    }

    public void remove(){
        pentamino.removeBrick(this);
    }

    public boolean overlaps(Brick b){
        return collider.overlaps(b.collider);
    }
//    public boolean overlaps(Rectangle rect){
//        return collider.overlaps(rect);
//    }
    public void render(SpriteBatch batch){
        batch.draw(textureRegion, (float) x, (float) y, (float) size, (float) size);
    }
}
