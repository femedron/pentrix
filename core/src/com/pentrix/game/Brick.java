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

    public Brick(double size){
        this.size = size;
        initTexture();
        collider = new Rectangle();
        collider.width = (float) size;
        collider.height = (float) size;
    }
    private void initTexture(){
        Texture texture = new Texture(Gdx.files.internal("raw/brick.png"));
        textureRegion = TextureRegion.split(texture, 9, 9)[0][0];  //todo match certain pattern with texture, color
    }

    public boolean overlaps(Brick b){
        return collider.overlaps(b.collider);
    }
    public boolean overlaps(Rectangle rect){
        return collider.overlaps(rect);
    }
    public void render(SpriteBatch batch){
        batch.draw(textureRegion, (float) x, (float) y, (float) size, (float) size);
    }
}
