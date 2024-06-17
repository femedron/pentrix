package com.pentrix.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pentrix.game.screens.MainMenuScreen;


public class Pentrix extends Game {

    public SpriteBatch batch;
    public BitmapFont font;
    private Music music;

    public Pentrix(){
    }

    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont(); // use libGDX's default Arial font


        music = Gdx.audio.newMusic(Gdx.files.internal("music/funky.mp3"));

        // start the playback of the background music immediately
        music.setLooping(true);
        music.play();


        this.setScreen(new MainMenuScreen(this));
    }
    public void stopMusic(){
        if(music.isPlaying())
            music.stop();
    }
    public void resumeMusic(){
        if(!music.isPlaying()){
            music.play();
        }
    }

    public void render() {
        super.render(); // important!
        music.setVolume(AppPreferences.instance.getMusicVolume());
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
        music.dispose();
    }

}