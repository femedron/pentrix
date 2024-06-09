package com.pentrix.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Container {

    Texture texture;
    Rectangle content;
    public int x,y,width,height;
    public Container(int x, int y, int w, int h){
        this.x=x;
        this.y=y;
        width = w;
        height = h;
        int offset = 3;
        content = new Rectangle();
        content.x = x+offset;
        content.y = y+offset;
        content.width = w-2*offset;
        content.height = h-2*offset;
        Pixmap pixmap = new Pixmap(w,h, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fillRectangle(0,0,w,h);
        pixmap.setColor(Color.BLACK);
        pixmap.fillRectangle(offset,offset, (int) content.width, (int) content.height);
        texture = new Texture(pixmap);
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, x,y,width,height);
        // batch.draw(...);
    }


}
