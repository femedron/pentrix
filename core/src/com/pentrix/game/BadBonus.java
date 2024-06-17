package com.pentrix.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class BadBonus extends Bonus{
    public BadBonus(GameField gf, double fromY) {
        super(gf, fromY);
    }
    @Override
    void generateBonus(){
        switch (MathUtils.random(0,2)){
            case 0:
                gameField.spawnToughBrick();
                break;
            case 1:
                gameField.increaseTempo();
                break;
            case 2:
                gameField.setInfSpin(false);
                break;
        }
    }

    @Override
    void loadTexture() {
        texture = new Texture(Gdx.files.internal("mine/bonus.png"));
    }

    @Override
    public void collect() {
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/collect_bad.mp3"));
        long id = sound.play();
        sound.setVolume(id, AppPreferences.instance.getSoundVolume());
        generateBonus();
        gameField.bonus = null;
    }
}
