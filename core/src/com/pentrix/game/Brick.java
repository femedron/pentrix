package com.pentrix.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Brick {
    Rectangle collider;
    double x;
    double y;
    double size; //collision borders
    double offset; // to outside collider

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
        collider.x = (float) (x-offset);
        collider.y = (float) (y-offset);
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
    TextureRegion textureRegion;


    public Brick(double size, double offset){
        this.size = size;
        initTexture();
        this.offset = offset;
    }
    private void initTexture(){
        Texture texture = new Texture(Gdx.files.internal("raw/brick.png"));
        textureRegion = TextureRegion.split(texture, 9, 9)[0][0];  //todo match certain pattern with texture, color
    }

    public boolean collides(Brick brick){//todo
        if(brick.collider.overlaps(collider)){
            return true;
        }
        return false;
    }

    public void render(SpriteBatch batch){
        batch.draw(textureRegion, (float) x, (float) y, (float) size, (float) size);
    }
}
