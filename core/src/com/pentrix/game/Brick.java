package com.pentrix.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Brick {
    public double x,y,size;
    TextureRegion textureRegion;
    public Brick(double size){
        this.size = size;
        initTexture();
    }
    private void initTexture(){
        Texture texture = new Texture(Gdx.files.internal("raw/brick.png"));
        textureRegion = TextureRegion.split(texture, 9, 9)[0][0];  //todo match certain pattern with texture, color
    }

    public void render(SpriteBatch batch){
        batch.draw(textureRegion, (float) x, (float) y, (float) size, (float) size);
    }
}
