package com.pentrix.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;


public class GameField extends Container{

    final double PENTAMINO_SIZE = (5*(9+1)+1) * 2;

    Array<Pentamino> pentaminoes;

    public GameField(int x, int y, int w, int h) {
        super(x, y, w, h);
        pentaminoes = new Array<>();
        addPentamino(2); //todo
    }

    public void addPentamino(int seed){
        addPentamino(new Pentamino(seed,0,0,PENTAMINO_SIZE));
    }
    public void addPentamino(Pentamino p){
        p.x = x + width/2 - PENTAMINO_SIZE;
        p.y = y + height - PENTAMINO_SIZE;
        pentaminoes.add(p);
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        for (Pentamino p: pentaminoes) {
            p.render(batch);
        }
    }
}
