package com.pentrix.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Container {

    Texture texture;
    public Rectangle content;
    public double ox,oy,owidth,oheight;  // outer
    public double x,y,width,height;
    public Container(int x, int y, int w, int h){
        this.ox=x;
        this.oy=y;
        owidth = w;
        oheight = h;
        int offset = 3;
        this.x = ox + offset;
        this.y = oy + offset;
        this.width = owidth - 2*offset;
        this.height = oheight - 2*offset;
        content = new Rectangle();
        content.x = (float) this.x;
        content.y = (float) this.y;
        content.width = (float) this.width;
        content.height = (float) this.height;
        Pixmap pixmap = new Pixmap(w,h, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fillRectangle(0,0,w,h);
        pixmap.setColor(Color.BLACK);
        pixmap.fillRectangle(offset,offset, (int) content.width, (int) content.height);
        texture = new Texture(pixmap);
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, (float) ox, (float) oy, (float) owidth, (float) oheight);
        // batch.draw(...);
    }


}
