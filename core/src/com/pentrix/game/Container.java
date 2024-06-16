package com.pentrix.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Container {
    public final double offset = 2;
    Texture texture;
    public double ox,oy,owidth,oheight;  // outer
    public double x,y,width,height;
    public Container(double xx, double yy, double ww, double hh){
        x = xx; y = yy; width = ww; height = hh;
        ox = x - offset; oy = y - offset; owidth = width + 2 * offset; oheight = height + 2 * offset;
        Pixmap pixmap = new Pixmap((int) owidth, (int) oheight, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fillRectangle(0,0, (int) owidth, (int) oheight);
        pixmap.setColor(Color.BLACK);
        pixmap.fillRectangle((int) offset, (int) offset, (int) width, (int) height);
        texture = new Texture(pixmap);
    }
    public void setHighY(double yy){
        y = yy - height;
        oy = y-offset;
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, (float) ox, (float) oy, (float) owidth, (float) oheight);
    }


}
